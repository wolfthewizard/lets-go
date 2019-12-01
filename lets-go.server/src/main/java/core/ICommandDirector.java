package core;

import core.model.Move;
import core.model.MoveExecution;
import core.model.MoveIdentity;
import javafx.util.Pair;
import main.contract.BoardSize;

public interface ICommandDirector {
        Pair<Integer, MoveExecution> CreateNewBotGame(boolean letBotStart, BoardSize boardSize);
        int CreateNewMultiplayerGame();
        MoveExecution TryToMove(Move move);
        void CancelGame(MoveIdentity leftIdentity);
}
