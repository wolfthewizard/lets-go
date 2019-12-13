package infrastructure;

import contract.Change;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.interfaces.IGameRepository;
import core.model.Color;
import core.model.Game;

import java.util.ArrayList;

public class GameRepository implements IGameRepository {
    private ArrayList<Game> games;

    public void CreateGame(int id, BoardSize boardSize, Color startingPlayer){
        games.add(new Game(id, boardSize, startingPlayer));
    }

    public Occupancy[][] getGameBoard(int gameId){
        return getGame(gameId).getBoard();
    }

    public void performMove(int gameId, ArrayList<Change> changes){
        Game game = getGame(gameId);

        for (Change change: changes) {
            game.setTile(change.getCoordinates().getX(), change.getCoordinates().getY(), change.getOccupancy());
        }
    }

    private Game getGame(int gameId){
        for (Game game: games) {
            if(game.getId() == gameId){
                return game;
            }
        }

        return null;
    }
}