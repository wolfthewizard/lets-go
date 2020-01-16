package core.interfaces;


import contract.Change;
import contract.ResponsePrisoners;

import java.util.List;

public interface IGameManager {

    void moveExecuted(List<Change> changes, ResponsePrisoners prisoners);
    void invalidMove();
    void cantCreateGame();
    void success();
    void serverError();
    void playerLeft();
    void gameWon();
    void gameLost();
    void tie();
}
