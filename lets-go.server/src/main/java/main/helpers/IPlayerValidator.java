package main.helpers;

import core.model.MoveIdentity;
import main.model.GameInfo;

public interface IPlayerValidator {

    void addNewGame(int whiteId, int blackId, int gameId);

    void removeGame(int gameId);

    GameInfo getGameInfo (int playerId);
}
