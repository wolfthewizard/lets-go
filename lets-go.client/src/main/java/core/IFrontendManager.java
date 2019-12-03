package core;


import contract.Change;

import java.util.ArrayList;

public interface IFrontendManager {

    void moveExecuted(ArrayList<Change> changes);
    void invalidMove();
    void waitingForPlayer();
    void cantCreateGame();
    void success();
    void serverError();
}
