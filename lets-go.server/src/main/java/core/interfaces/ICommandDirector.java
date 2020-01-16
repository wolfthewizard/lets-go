package core.interfaces;

import contract.gamerecord.GameRecord;
import core.model.*;
import contract.enums.BoardSize;

public interface ICommandDirector {

        int createNewGame(BoardSize boardSize);
        MoveResponse tryToMove(Move move);
        void cancelGame(MoveIdentity leftIdentity);
        GameRecord getGameData(int gameId);
}
