package core.interfaces;

import core.model.*;
import contract.enums.BoardSize;

public interface ICommandDirector {

        int createNewGame(BoardSize boardSize);
        MoveResponse tryToMove(Move move);
        void cancelGame(MoveIdentity leftIdentity);
}
