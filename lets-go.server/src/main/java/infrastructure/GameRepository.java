package infrastructure;

import core.interfaces.IGameRepository;
import core.model.Game;

import java.util.ArrayList;

public class GameRepository implements IGameRepository {

    private ArrayList<Game> games;
    private int notYetAssignedId;

    public GameRepository() {

        games = new ArrayList<>();
        notYetAssignedId = 0;
    }

    public void createGame(Game game) {
        games.add(game);
    }

    public void removeGame(int gameId) {

        Game game = getGame(gameId);

        if (game != null) {
            games.remove(game);
        }
    }

    public Game getGame(int gameId) {
        for (Game game : games) {
            if (game.getId() == gameId) {
                return game;
            }
        }

        return null;
    }

    public int fetchId() {
        return notYetAssignedId++;
    }
}