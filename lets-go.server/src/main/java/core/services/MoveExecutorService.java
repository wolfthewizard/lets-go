package core.services;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;
import core.interfaces.*;
import core.model.*;
import core.model.enums.Color;
import core.model.enums.MoveResponseType;
import contract.enums.Winner;
import infrastructure.services.IDBMediationService;

import java.util.ArrayList;
import java.util.List;

public class MoveExecutorService implements IMoveExecutorService {

    private IGameRepository gameRepository;
    private IMoveValidator moveValidator;
    private IGameArbitrator gameArbitrator;
    private IMovePerformer movePerformer;
    private IDBMediationService dbMediationService;

    public MoveExecutorService(IGameRepository gameRepository, IMoveValidator moveValidator,
                               IGameArbitrator gameArbitrator, IMovePerformer movePerformer,
                               IDBMediationService dbMediationService) {

        this.gameRepository = gameRepository;
        this.moveValidator = moveValidator;
        this.gameArbitrator = gameArbitrator;
        this.movePerformer = movePerformer;
        this.dbMediationService = dbMediationService;
    }

    @Override
    public MoveResponse executeMove(Move move) {

        Game game = gameRepository.getGame(move.getGameId());
        Board board = game.getBoard();

        Coordinates moveCoordinates = move.getCoordinates();
        Color playerColor = move.getPlayerColor();
        int boardSizeValue = game.getBoardSize().getValue();
        Prisoners prisoners = board.getCurrentPrisoners();

        List<Change> changes = new ArrayList<>();

        Occupancy[][] potentialState = new Occupancy[boardSizeValue][];
        for (int i = 0; i < boardSizeValue; i++) {
            potentialState[i] = board.getCurrentState()[i].clone();
        }

        if (moveCoordinates == null) {

            dbMediationService.insertTurn(game.getId(), game.getTurnCount(), changes);
            game.setTurnCount(game.getTurnCount() + 1);

            if (game.isLastTurnPassed()) {

                Winner winner = gameArbitrator.determineWinner(potentialState, boardSizeValue);
                dbMediationService.setWinner(game.getId(), winner);
                return new MoveResponse(gameArbitrator.toMoveResponseType(winner, playerColor));

            } else {

                game.setLastTurnPassed(true);
                return new MoveResponse(MoveResponseType.GAME_GOES_ON,
                        new MoveExecution(prisoners.toResponsePrisoners(playerColor)));
            }
        }

        if (!preMoveValidation(board.getCurrentState(), moveCoordinates)) {
            return new MoveResponse(MoveResponseType.INVALID_MOVE);
        }


        int newPrisoners = movePerformer.performMove(moveCoordinates, playerColor, potentialState, boardSizeValue, changes);

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
        dbMediationService.insertTurn(game.getId(), game.getTurnCount(), changes);
        game.setTurnCount(game.getTurnCount() + 1);

        return new MoveResponse(MoveResponseType.GAME_GOES_ON, new MoveExecution
                (changes, prisoners.toResponsePrisoners(playerColor)));
    }

    private boolean preMoveValidation(Occupancy[][] board, Coordinates move) {
        return moveValidator.validateVacancy(board, move);
    }

    private boolean postMoveValidation(int boardSizeValue, Occupancy[][] potentialState,
                                       Occupancy[][] previousTurnState, List<Change> changes) {
        return moveValidator.validateKO(boardSizeValue, potentialState, previousTurnState)
                && moveValidator.validateSuicide(changes);
    }
}
