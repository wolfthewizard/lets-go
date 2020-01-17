package infrastructure.services;

import core.model.entities.GameEntity;

public interface IDBQueryExecutionService {

    void addGameToDB(GameEntity gameEntity);
    void updateGameInDB(GameEntity gameEntity);
    GameEntity getGameFromDB(int gameId);
}
