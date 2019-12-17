package core.interfaces;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;
import core.model.enums.Color;

import java.util.List;

public interface IMovePerformer {

    int performMove(Coordinates moveCoordinates, Color playerColor, Occupancy[][] potentialState, int boardSizeValue, List<Change> changes);
}
