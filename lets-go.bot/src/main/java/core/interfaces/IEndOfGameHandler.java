package core.interfaces;

import contract.enums.ResponseType;

public interface IEndOfGameHandler {
    void handleGameEnd(ResponseType endType, int gameId);
}
