package core.interfaces;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;

import java.util.List;

public interface IMoveValidator {

    boolean validateVacancy(Occupancy[][] board, Coordinates coordinates);
    boolean validateKO(int boardSizeValue, Occupancy[][] currentBoard, Occupancy[][] boardTwoTurnsAgo);
    boolean validateSuicide(List<Change> changes);
}
