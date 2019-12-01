package main.helpers;

import core.model.MoveIdentity;
import main.model.Color;
import main.model.GameInfo;
import main.model.GameWithPlayers;

import java.util.ArrayList;

public class PlayerValidator implements IPlayerValidator {

    private ArrayList<GameWithPlayers> games = new ArrayList<>();

    public void addNewGame(int whiteId, int blackId, int gameId) {

        games.add(new GameWithPlayers(whiteId, blackId, gameId));
    }

    public void removeGame(int gameId) {

        games.removeIf(gameWithPlayers -> gameWithPlayers.getGameId() == gameId);
    }

    public GameInfo getGameInfo(int playerId) {

        for (GameWithPlayers game: games) {
            if(game.getBlackid()==playerId) {
                return new GameInfo(new MoveIdentity(Color.BLACK, game.getGameId()), game.getWhiteId());
            }
            if(game.getWhiteId()==playerId) {
                return new GameInfo(new MoveIdentity(Color.WHITE, game.getGameId()), game.getBlackid());
            }
        }

        return null;
    }
}
