package core;

import core.model.Change;
import core.model.Move;
import core.model.MoveIdentity;
import javafx.util.Pair;
import main.contract.enums.BoardSize;

import java.util.ArrayList;

public interface ICommandDirector {
        Pair<Integer, ArrayList<Change>> CreateNewBotGame(boolean letBotStart, BoardSize boardSize);
        int CreateNewMultiplayerGame();
        ArrayList<Change> TryToMove(Move move);
        void CancelGame(MoveIdentity leftIdentity);
}
