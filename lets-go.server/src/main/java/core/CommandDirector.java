package core;

import contract.enums.BoardSize;
import core.interfaces.ICommandDirector;
import core.model.CreateNewBotGameResult;
import core.model.Move;
import core.model.MoveIdentity;
import core.model.MoveResponse;

public class CommandDirector implements ICommandDirector {
    @Override
    public CreateNewBotGameResult CreateNewBotGame(boolean letBotStart, BoardSize boardSize) {
        return null;
    }

    @Override
    public int CreateNewMultiplayerGame() {
        return 0;
    }

    @Override
    public MoveResponse TryToMove(Move move) {
        return null;
    }

    @Override
    public MoveResponse GetBotMove(int gameId) {
        return null;
    }

    @Override
    public void CancelGame(MoveIdentity leftIdentity) {

    }
}
