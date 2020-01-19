package core;

import contract.enums.BoardSize;
import contract.gamerecord.GameRecord;
import core.interfaces.ICommandDirector;
import core.interfaces.IGameManagerService;
import core.interfaces.IMoveExecutorService;
import core.model.Move;
import core.model.MoveIdentity;
import core.model.MoveResponse;
import infrastructure.services.IDBMediationService;

public class CommandDirector implements ICommandDirector {

    private IGameManagerService gameManagerService;
    private IMoveExecutorService moveExecutorService;
    private IDBMediationService mediationService;

    public CommandDirector(IGameManagerService gameManagerService,
                           IMoveExecutorService moveExecutorService,
                           IDBMediationService mediationService) {

        this.gameManagerService = gameManagerService;
        this.moveExecutorService = moveExecutorService;
        this.mediationService = mediationService;
    }

    @Override
    public int createNewGame(BoardSize boardSize) {
        return gameManagerService.createNewGame(boardSize);
    }

    @Override
    public MoveResponse tryToMove(Move move) {
        return moveExecutorService.executeMove(move);
    }

    @Override
    public void cancelGame(MoveIdentity leftIdentity) {
        gameManagerService.cancelGame(leftIdentity.getGameId());
    }

    @Override
    public GameRecord getGameData(int gameId) {
        return mediationService.getGame(gameId);
    }
}
