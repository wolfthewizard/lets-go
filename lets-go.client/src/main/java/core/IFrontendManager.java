package core;


import contract.Change;
import contract.Prisoners;

import java.util.ArrayList;

public interface IFrontendManager {

    void moveExecuted(ArrayList<Change> changes, Prisoners prisoners);
    void invalidMove();
    void waitingForPlayer();
    void cantCreateGame();
    void success();
    void serverError();
}
