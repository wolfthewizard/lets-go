package main.helpers;

import core.model.MoveIdentity;
import main.Color;
import main.model.GameWithPlayers;

import java.util.ArrayList;

public class PlayerValidator implements IPlayerValidator {

    private ArrayList<GameWithPlayers> games = new ArrayList<>();

    public void addNewGame(int whiteId, int blackId, int gameId) {

        games.add(new GameWithPlayers(whiteId, blackId, gameId));
    }

    public void removeGame(int gameId) {

        games.removeIf(gameWithPlayers -> gameWithPlayers.gameId == gameId);
    }

    public MoveIdentity getMoveIdentity(int playerId) {

        for (GameWithPlayers game: games) {
            if(game.blackId==playerId) {
                return new MoveIdentity(Color.BLACK, game.gameId);
            }
            if(game.whiteId==playerId) {
                return new MoveIdentity(Color.WHITE, game.gameId);
            }
        }

        return null;
    }
}
