package core.interfaces;

import contract.Change;
import contract.enums.BoardSize;
import contract.enums.Occupancy;

import java.util.ArrayList;
import java.util.List;

public interface IBoardManager {

    void initializeBoard(BoardSize boardSize);

    void saveMoves(List<Change> changes);

    Occupancy[][] getBoard();

    BoardSize getBoardSize();
}
