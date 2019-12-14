package core.services;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;
import core.interfaces.IGameRepository;
import core.interfaces.IMoveExecutorService;
import core.interfaces.IMoveValidator;
import core.model.*;
import core.model.enums.MoveResponseType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoveExecutorService implements IMoveExecutorService {

    private IGameRepository gameRepository;
    private IMoveValidator moveValidator;

    private Occupancy[][] potentialState;
    private int boardSizeValue;
    private List<Change> changes;

    public MoveExecutorService(IGameRepository gameRepository, IMoveValidator moveValidator) {

        this.gameRepository = gameRepository;
        this.moveValidator = moveValidator;
    }

    @Override
    public MoveResponse executeMove(Move move) {

        Game game = gameRepository.getGame(move.getGameId());
        Board board = game.getBoard();

        Coordinates moveCoordinates = move.getCoordinates();
        Color playerColor = move.getPlayerColor();
        boardSizeValue = game.getBoardSize().getValue();
        Prisoners prisoners = board.getCurrentPrisoners();

        if (moveCoordinates == null) {

            if (game.isLastTurnPassed()) {

                //todo : handle basically the whole point counting mechanism
                if (new Random().nextBoolean()) {
                    return new MoveResponse(MoveResponseType.CURRENT_PLAYER_WON);
                } else {
                    return new MoveResponse(MoveResponseType.OTHER_PLAYER_WON);
                }
            } else {

                game.setLastTurnPassed(true);
                return new MoveResponse(MoveResponseType.GAME_GOES_ON,
                        new MoveExecution(prisoners.toResponsePrisoners(playerColor)));
            }
        }

        if (!preMoveValidation(board.getCurrentState(), moveCoordinates)) {
            return new MoveResponse(MoveResponseType.INVALID_MOVE);
        }

        initializePotentialData(board);

        int newPrisoners = doMove(moveCoordinates, playerColor);

        if (!postMoveValidation(boardSizeValue, potentialState, board.getPreviousTurnState(), changes)) {
            return new MoveResponse(MoveResponseType.INVALID_MOVE);
        }

        game.setLastTurnPassed(false);

        if (playerColor == Color.BLACK) {
            prisoners.addBlacksPrisoners(newPrisoners);
        } else {
            prisoners.addWhitesPrisoners(newPrisoners);
        }

        board.insertState(potentialState);

        return new MoveResponse(MoveResponseType.GAME_GOES_ON, new MoveExecution
                (changes, prisoners.toResponsePrisoners(playerColor)));
    }

    private void initializePotentialData(Board board) {
        changes = new ArrayList<>();

        potentialState = new Occupancy[boardSizeValue][];
        for (int i = 0; i < boardSizeValue; i++) {
            potentialState[i] = board.getCurrentState()[i].clone();
        }
    }

    private boolean preMoveValidation(Occupancy[][] board, Coordinates move) {
        return moveValidator.validateVacancy(board, move);
    }

    private boolean postMoveValidation(int boardSizeValue, Occupancy[][] potentialState,
                                       Occupancy[][] previousTurnState, List<Change> changes) {
        return moveValidator.validateKO(boardSizeValue, potentialState, previousTurnState)
                && moveValidator.validateSuicide(changes);
    }

    private int doMove(Coordinates moveCoordinates, Color playerColor) {

        int killedEnemies = 0;

        //1st phase
        changeBoard(moveCoordinates, playerColor.toOccupancy());

        //2nd phase
        List<Coordinates> enemiesSurrounding = getNeighbouringCords(moveCoordinates, playerColor.reverse().toOccupancy());

        for (Coordinates enemy : enemiesSurrounding) {

            List<Coordinates> chain = getChainStartingWithCords(enemy, playerColor.reverse().toOccupancy());

            if (isChainWithoutBreaths(chain)) {

                killedEnemies = removeChainFromBoard(chain);
            }
        }

        if (killedEnemies != 0) {

            return killedEnemies;
        }

        //3rd phase
        List<Coordinates> chain = getChainStartingWithCords(moveCoordinates, playerColor.toOccupancy());

        if (isChainWithoutBreaths(chain)) {

            killedEnemies = -removeChainFromBoard(chain);
        }

        return killedEnemies;
    }

    private int removeChainFromBoard(List<Coordinates> chain) {

        int amountOfRemovedStones = 0;

        for (Coordinates coordinates : chain) {

            changeBoard(coordinates, Occupancy.EMPTY);
            amountOfRemovedStones++;
        }

        return amountOfRemovedStones;
    }

    private boolean isChainWithoutBreaths(List<Coordinates> chain) {

        for (Coordinates coordinates : chain) {

            if (!getNeighbouringCords(coordinates, Occupancy.EMPTY).isEmpty()) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method clumps all tiles of a given occupancy to one chain.
     * It requires Occupancy instead of Color because it will be used to group empty tiles together in the future.
     * It requires this parameter because it is called on tiles with incompatible Occupancies.
     * @param startingCords Coordinates from which it should start grouping chain
     * @param occupancy Occupancy type it's gonna group together
     * @return List of Coordinates, which represents one chain
     */

    private List<Coordinates> getChainStartingWithCords(Coordinates startingCords, Occupancy occupancy) {

        List<Coordinates> chain = new ArrayList<>();
        chain.add(startingCords);

        for (Coordinates cords : getNeighbouringCords(startingCords, occupancy)) {
            chainBuildingRecursive(cords, occupancy, chain);
        }

        return chain;
    }

    private void chainBuildingRecursive(Coordinates coordinates, Occupancy occupancy, List<Coordinates> chain) {

        if (!chainContains(chain, coordinates)) {
            chain.add(coordinates);

            for (Coordinates cords : getNeighbouringCords(coordinates, occupancy)) {
                chainBuildingRecursive(cords, occupancy, chain);
            }
        }
    }

    private List<Coordinates> getNeighbouringCords(Coordinates coordinates, Occupancy occupancy) {

        List<Coordinates> neighbours = new ArrayList<>();

        int x = coordinates.getX();
        int y = coordinates.getY();

        if (x - 1 >= 0) {

            if (potentialState[x - 1][y] == occupancy) {
                neighbours.add(new Coordinates(x - 1, y));
            }
        }

        if (x + 1 < boardSizeValue) {

            if (potentialState[x + 1][y] == occupancy) {
                neighbours.add(new Coordinates(x + 1, y));
            }
        }

        if (y - 1 >= 0) {

            if (potentialState[x][y - 1] == occupancy) {
                neighbours.add(new Coordinates(x, y - 1));
            }
        }

        if (y + 1 < boardSizeValue) {

            if (potentialState[x][y + 1] == occupancy) {
                neighbours.add(new Coordinates(x, y + 1));
            }
        }

        return neighbours;
    }

    private void changeBoard(Coordinates coordinates, Occupancy occupancy) {

        potentialState[coordinates.getX()][coordinates.getY()] = occupancy;
        changes.add(new Change(occupancy, coordinates));
    }

    private boolean chainContains(List<Coordinates> chain, Coordinates coordinates) {

        for (Coordinates cords : chain) {

            if (cords.getX() == coordinates.getX() && cords.getY() == coordinates.getY()) {
                return true;
            }
        }

        return false;
    }
}
