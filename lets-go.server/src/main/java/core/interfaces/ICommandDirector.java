package core.interfaces;

import core.model.*;
import contract.enums.BoardSize;

public interface ICommandDirector {
        int CreateNewGame();
        MoveResponse TryToMove(Move move);
        void CancelGame(MoveIdentity leftIdentity);
}
