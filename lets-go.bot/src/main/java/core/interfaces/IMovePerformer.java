package core.interfaces;

import contract.Coordinates;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.model.Color;

public interface IMovePerformer {
    Coordinates performMove(Occupancy[][] board, BoardSize boardSize, Color colorOfMovingPlayer);
}
