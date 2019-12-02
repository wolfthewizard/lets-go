package core;

import core.model.*;
import contract.enums.BoardSize;

public interface ICommandDirector {
        CreateNewBotGameResult CreateNewBotGame(boolean letBotStart, BoardSize boardSize);
        int CreateNewMultiplayerGame();
        MoveExecution TryToMove(Move move);
        void CancelGame(MoveIdentity leftIdentity);
}
