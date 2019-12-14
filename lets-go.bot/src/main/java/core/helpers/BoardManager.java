package core.helpers;

import contract.Change;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.interfaces.IBoardManager;

import java.util.ArrayList;

public class BoardManager implements IBoardManager {

    private Occupancy[][] board;
    private BoardSize boardSize;

    public void initializeBoard(BoardSize boardSize){
        this.boardSize = boardSize;

        board = new Occupancy[boardSize.getValue()][];
        for (int i = 0; i < boardSize.getValue(); i++) {
            board[i] = new Occupancy[boardSize.getValue()];
            for (int j = 0; j < boardSize.getValue(); j++) {
                board[i][j] = Occupancy.EMPTY;
            }
        }
    }

    public void saveMoves(ArrayList<Change> changes) {
        for (Change change : changes) {
            board[change.getCoordinates().getX()][change.getCoordinates().getY()] = change.getOccupancy();
        }
    }

    public Occupancy[][] getBoard() {
        return board;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }
}
