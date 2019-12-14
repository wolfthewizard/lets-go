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

        if (move.getCoordinates() == null) {
            if (game.isLastTurnPassed()) {

                if (new Random().nextBoolean()) {
                    return new MoveResponse(MoveResponseType.CURRENT_PLAYER_WON, null);
                } else {
                    return new MoveResponse(MoveResponseType.OTHER_PLAYER_WON, null);
                }
                //todo : handle basically the whole point counting mechanism
            } else {

                game.setLastTurnPassed(true);
                return new MoveResponse(MoveResponseType.GAME_GOES_ON, new MoveExecution
                        (new ArrayList<>(), board.getCurrentPrisoners().toResponsePrisoners(move.getPlayerColor())));
            }
        }

        if (!moveValidator.validateVacancy(board.getCurrentState(), move.getCoordinates())) {
            return new MoveResponse(MoveResponseType.INVALID_MOVE, null);
        }

        changes = new ArrayList<>();

        potentialState = new Occupancy[game.getBoardSize().getValue()][];
        for (int i = 0; i < game.getBoardSize().getValue(); i++) {
            potentialState[i] = board.getCurrentState()[i].clone();
        }

        boardSizeValue = game.getBoardSize().getValue();

        int newPrisoners = doMove(move);

        if (!moveValidator.validateKO(game.getBoardSize(), potentialState, board.getPreviousTurnState())) {
            return new MoveResponse(MoveResponseType.INVALID_MOVE, null);
        }

        if (!moveValidator.validateSuicide(changes)) {
            return new MoveResponse(MoveResponseType.INVALID_MOVE, null);
        }

        game.setLastTurnPassed(false);

        Prisoners prisoners = board.getCurrentPrisoners();
        if (move.getPlayerColor() == Color.BLACK) {
            prisoners.addBlacksPrisoners(newPrisoners);
        } else {
            prisoners.addWhitesPrisoners(newPrisoners);
        }

        board.insertState(potentialState);

        return new MoveResponse(MoveResponseType.GAME_GOES_ON, new MoveExecution
                (changes, board.getCurrentPrisoners().toResponsePrisoners(move.getPlayerColor())));
    }

    private int doMove(Move move) {

        int killedEnemies = 0;

        //1st phase
        changeBoard(move.getCoordinates(), move.getPlayerColor().toOccupancy());

        //2nd phase
        List<Coordinates> enemiesSurrounding = getNeighbouringCords(move.getCoordinates(), move.getPlayerColor().reverse().toOccupancy());

        for (Coordinates enemy : enemiesSurrounding) {

            List<Coordinates> chain = getChainStartingWithCords(enemy, move.getPlayerColor().reverse().toOccupancy());

            if (isChainWithoutBreaths(chain)) {

                killedEnemies = removeChainFromBoard(chain);
            }
        }

        if (killedEnemies != 0) {

            return killedEnemies;
        }

        //3rd phase
        List<Coordinates> chain = getChainStartingWithCords(move.getCoordinates(), move.getPlayerColor().toOccupancy());

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
