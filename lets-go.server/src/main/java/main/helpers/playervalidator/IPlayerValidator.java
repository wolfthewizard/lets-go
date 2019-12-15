package main.helpers.playervalidator;

import contract.enums.BoardSize;
import main.model.GameInfo;

public interface IPlayerValidator {
    void addNewGame(int whiteId, int blackId, int gameId);

    void removeGame(int gameId);

    void playerLeft(int playerId);

    GameInfo getGameInfo(int playerId);

    Integer getWaitingPlayerId(BoardSize boardSize);

    void addWaitingPlayer(BoardSize boardSize, int playerId);
}
