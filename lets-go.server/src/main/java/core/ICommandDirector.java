package core;

import core.model.*;
import contract.enums.BoardSize;

public interface ICommandDirector {
        CreateNewBotGameResult CreateNewBotGame(boolean letBotStart, BoardSize boardSize);
        int CreateNewMultiplayerGame();
        MoveResponse TryToMove(Move move);
        MoveResponse GetBotMove(int gameId);
        void CancelGame(MoveIdentity leftIdentity);
}
