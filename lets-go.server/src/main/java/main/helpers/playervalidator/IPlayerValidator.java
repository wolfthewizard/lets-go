package main.helpers.playervalidator;

import contract.enums.BoardSize;
import core.model.Color;
import core.model.MoveIdentity;
import main.model.GameInfo;
import main.model.GameWithPlayers;

import java.util.ArrayList;
import java.util.HashMap;

public interface IPlayerValidator {
    void addNewGame(int whiteId, int blackId, int gameId);

    void removeGame(int gameId);

    void playerLeft(int playerId);

    GameInfo getGameInfo(int playerId);

    Integer getWaitingPlayerId(BoardSize boardSize);

    void addWaitingPlayer(BoardSize boardSize, int playerId);
}
