package main;

import contract.Change;
import contract.enums.Winner;

import java.util.List;

public interface Rewind {

    void signalPass();
    void signalWinner(Winner winner);
    void clearMoveNote();
    void enforceChanges(List<Change> changes);
}
