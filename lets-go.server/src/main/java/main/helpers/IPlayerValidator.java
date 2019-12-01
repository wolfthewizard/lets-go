package main.helpers;

import core.model.MoveIdentity;

public interface IPlayerValidator {

    void addNewGame(int whiteId, int blackId, int gameId);

    void removeGame(int gameId);

    MoveIdentity getMoveIdentity(int playerId);
}
