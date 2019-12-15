package core.helpers;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;
import core.interfaces.IMoveValidator;

import java.util.List;

public class MoveValidator implements IMoveValidator {

    @Override
    public boolean validateVacancy(Occupancy[][] board, Coordinates coordinates) {
        return board[coordinates.getX()][coordinates.getY()] == Occupancy.EMPTY;
    }

    @Override
    public boolean validateKO(int boardSizeValue, Occupancy[][] currentBoard, Occupancy[][] boardTwoTurnsAgo) {

        for (int y = 0; y < boardSizeValue; y++) {
            for (int x = 0; x < boardSizeValue; x++) {
                if (currentBoard[x][y] != boardTwoTurnsAgo[x][y]) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean validateSuicide(List<Change> changes) {

        Coordinates insertedStoneCords = null;

        for (Change change : changes) {
            if (change.getOccupancy() != Occupancy.EMPTY) {
                insertedStoneCords = change.getCoordinates();
                break;
            }
        }

        for (Change change : changes) {
            if (change.getCoordinates() == insertedStoneCords) {
                if (change.getOccupancy() == Occupancy.EMPTY) {
                    return false;
                }
            }
        }

        return true;
    }
}
