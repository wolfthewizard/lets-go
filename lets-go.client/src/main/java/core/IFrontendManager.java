package core;

import core.contract.Change;

import java.util.List;

public interface IFrontendManager {

    void moveExecuted(List<Change> changes);
    void invalidMove();
    void waitingForPlayer();
    void cantCreateGame();
    void success();
    void serverError();
}
