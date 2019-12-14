package core.interfaces;

import contract.Change;
import contract.enums.BoardSize;
import contract.enums.Occupancy;

import java.util.ArrayList;

public interface IBoardManager {

    void initializeBoard(BoardSize boardSize);

    void saveMoves(ArrayList<Change> changes);

    Occupancy[][] getBoard();

    BoardSize getBoardSize();
}
