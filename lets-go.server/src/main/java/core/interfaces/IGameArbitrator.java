package core.interfaces;

import contract.enums.Occupancy;
import core.model.enums.Color;
import core.model.enums.MoveResponseType;
import contract.enums.Winner;

public interface IGameArbitrator {

    Winner determineWinner(Occupancy[][] board, int boardSizeValue);
    MoveResponseType toMoveResponseType(Winner winner, Color playerColor);
}
