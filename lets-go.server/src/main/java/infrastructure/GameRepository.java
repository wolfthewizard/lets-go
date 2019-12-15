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

    @Override
    public void createGame(Game game) {
        games.add(game);
    }

    @Override
    public void removeGame(int gameId) {

        Game game = getGame(gameId);

        if (game != null) {
            games.remove(game);
        }
    }

    @Override
    public Game getGame(int gameId) {
        for (Game game : games) {
            if (game.getId() == gameId) {
                return game;
            }
        }

        return null;
    }

    @Override
    public int fetchId() {
        return notYetAssignedId++;
    }
}