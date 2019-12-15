package core.interfaces;

import core.model.Game;

public interface IGameRepository {

    void createGame(Game game);
    void removeGame(int gameId);

    Game getGame(int gameId);

    int fetchId();
}