package core.interfaces;


import contract.Change;
import contract.ResponsePrisoners;

import java.util.ArrayList;

public interface IFrontendManager {

    void moveExecuted(ArrayList<Change> changes, ResponsePrisoners prisoners);
    void invalidMove();
    void cantCreateGame();
    void success();
    void serverError();
    void playerLeft();
    void gameWon();
    void gameLost();
}
