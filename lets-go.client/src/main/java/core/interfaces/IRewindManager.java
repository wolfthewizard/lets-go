package core.interfaces;

import contract.gamerecord.GameRecord;

public interface IRewindManager {

    void rewind(GameRecord gameRecord);
    void handleInvalidId();
}
