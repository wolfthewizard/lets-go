package core;

import contract.enums.BoardSize;
import core.interfaces.ICommandDirector;
import core.interfaces.IGameManagerService;
import core.interfaces.IMoveExecutorService;
import core.model.Move;
import core.model.MoveIdentity;
import core.model.MoveResponse;
import infrastructure.GameRepository;

public class CommandDirector implements ICommandDirector {

    private IGameManagerService gameManagerService;
    private IMoveExecutorService moveExecutorService;

    public CommandDirector(IGameManagerService gameManagerService, IMoveExecutorService moveExecutorService) {

        this.gameManagerService = gameManagerService;
        this.moveExecutorService = moveExecutorService;
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
}
