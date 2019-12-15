package core.interfaces;

import contract.enums.Occupancy;
import core.model.Color;
import core.model.enums.MoveResponseType;
import core.model.enums.Winner;

public interface IGameArbitrator {

    Winner determineWinner(Occupancy[][] board, int boardSizeValue);
    MoveResponseType toMoveResponseType(Winner winner, Color playerColor);
}
