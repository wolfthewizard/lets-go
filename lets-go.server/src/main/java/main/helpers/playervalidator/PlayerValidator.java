package main.helpers.playervalidator;

import contract.enums.BoardSize;
import core.model.MoveIdentity;
import core.model.enums.Color;
import main.model.GameInfo;
import main.model.GameWithPlayers;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerValidator implements IPlayerValidator {

    private ArrayList<GameWithPlayers> games = new ArrayList<>();
    private final HashMap<BoardSize, Integer> waitingPlayers = new HashMap<>();

    public void addNewGame(int whiteId, int blackId, int gameId) {

        games.add(new GameWithPlayers(whiteId, blackId, gameId));

        for (BoardSize boardSize : waitingPlayers.keySet()) {
            if (waitingPlayers.get(boardSize) == whiteId || waitingPlayers.get(boardSize) == blackId) {
                waitingPlayers.remove(boardSize);
                return;
            }
        }
    }

    public void removeGame(int gameId) {

        games.removeIf(gameWithPlayers -> gameWithPlayers.getGameId() == gameId);
    }

    public void playerLeft(int playerId) {
        for (BoardSize boardSize : waitingPlayers.keySet()) {
            if (waitingPlayers.get(boardSize) == playerId) {
                waitingPlayers.remove(boardSize);
                return;
            }
        }
    }

    public GameInfo getGameInfo(int playerId) {

        for (GameWithPlayers game : games) {
            if (game.getBlackId() == playerId) {
                return new GameInfo(new MoveIdentity(Color.BLACK, game.getGameId()), game.getWhiteId());
            }
            if (game.getWhiteId() == playerId) {
                return new GameInfo(new MoveIdentity(Color.WHITE, game.getGameId()), game.getBlackId());
            }
        }

        return null;
    }

    public Integer getWaitingPlayerId(BoardSize boardSize) {
        return waitingPlayers.get(boardSize);
    }

    public void addWaitingPlayer(BoardSize boardSize, int playerId) {
        waitingPlayers.put(boardSize, playerId);
    }
}
