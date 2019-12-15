package core.services;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;
import core.interfaces.*;
import core.model.*;
import core.model.enums.MoveResponseType;
import core.model.enums.Winner;

import java.util.ArrayList;
import java.util.List;

public class MoveExecutorService implements IMoveExecutorService {

    private IGameRepository gameRepository;
    private IMoveValidator moveValidator;
    private IGameArbitrator gameArbitrator;
    private IMoveHelper moveHelper;

    private Occupancy[][] potentialState;
    private int boardSizeValue;
    private List<Change> changes;

    public MoveExecutorService(IGameRepository gameRepository, IMoveValidator moveValidator, IGameArbitrator gameArbitrator, IMoveHelper moveHelper) {

        this.gameRepository = gameRepository;
        this.moveValidator = moveValidator;
        this.gameArbitrator = gameArbitrator;
        this.moveHelper = moveHelper;
    }

    @Override
    public MoveResponse executeMove(Move move) {

        Game game = gameRepository.getGame(move.getGameId());
        Board board = game.getBoard();

        Coordinates moveCoordinates = move.getCoordinates();
        Color playerColor = move.getPlayerColor();
        boardSizeValue = game.getBoardSize().getValue();
        Prisoners prisoners = board.getCurrentPrisoners();

        initializePotentialData(board);

        if (moveCoordinates == null) {

            if (game.isLastTurnPassed()) {

                Winner winner = gameArbitrator.determineWinner(potentialState, boardSizeValue);

                if (winner == Winner.TIE) {
                    return new MoveResponse(MoveResponseType.TIE);
                } else if (winner == Winner.BLACK) {
                    if (playerColor == Color.BLACK) {
                        return new MoveResponse(MoveResponseType.CURRENT_PLAYER_WON);
                    } else {
                        return new MoveResponse(MoveResponseType.OTHER_PLAYER_WON);
                    }
                } else {
                    if (playerColor == Color.BLACK) {
                        return new MoveResponse(MoveResponseType.OTHER_PLAYER_WON);
                    } else {
                        return new MoveResponse(MoveResponseType.CURRENT_PLAYER_WON);
                    }
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
        List<Coordinates> enemiesSurrounding = moveHelper.getNeighbouringCords(potentialState, boardSizeValue, moveCoordinates, playerColor.reverse().toOccupancy());

        for (Coordinates enemy : enemiesSurrounding) {

            List<Coordinates> chain = moveHelper.getChainStartingWithCords(potentialState, boardSizeValue, enemy, playerColor.reverse().toOccupancy());

            if (moveHelper.isChainWithoutBreaths(potentialState, boardSizeValue, chain)) {

                killedEnemies = removeChainFromBoard(chain);
            }
        }

        if (killedEnemies != 0) {

            return killedEnemies;
        }

        //3rd phase
        List<Coordinates> chain = moveHelper.getChainStartingWithCords(potentialState, boardSizeValue, moveCoordinates, playerColor.toOccupancy());

        if (moveHelper.isChainWithoutBreaths(potentialState, boardSizeValue, chain)) {

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

    private void changeBoard(Coordinates coordinates, Occupancy occupancy) {

        potentialState[coordinates.getX()][coordinates.getY()] = occupancy;
        changes.add(new Change(occupancy, coordinates));
    }
}
