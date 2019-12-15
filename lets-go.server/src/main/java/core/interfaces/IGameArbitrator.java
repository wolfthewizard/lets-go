package core.interfaces;

import contract.enums.Occupancy;
import core.model.enums.Winner;

public interface IGameArbitrator {

    Winner determineWinner(Occupancy[][] board, int boardSizeValue);
}
