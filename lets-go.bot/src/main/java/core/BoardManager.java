package core;

import contract.Change;
import contract.enums.BoardSize;
import contract.enums.Occupancy;

import java.util.ArrayList;

public class BoardManager {

    private Occupancy[][] board;

    public BoardManager(BoardSize boardSize) {

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
}
