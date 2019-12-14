package infrastructure;

import contract.Change;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.interfaces.IGameRepository;
import core.model.Board;
import core.model.Color;
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

    public Board getGameBoard(int gameId) {
        return getGame(gameId).getBoard();
    }

    private Game getGame(int gameId) {
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