package core;

import contract.enums.BoardSize;
import core.interfaces.ICommandDirector;
import core.model.Move;
import core.model.MoveIdentity;
import core.model.MoveResponse;

public class CommandDirector implements ICommandDirector {

    @Override
    public int CreateNewGame() {
        return 0;
    }

    @Override
    public MoveResponse TryToMove(Move move) {
        return null;
    }

    @Override
    public void CancelGame(MoveIdentity leftIdentity) {

    }
}
