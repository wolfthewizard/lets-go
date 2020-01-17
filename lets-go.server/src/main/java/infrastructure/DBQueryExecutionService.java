package infrastructure;

import core.model.entities.GameEntity;
import infrastructure.services.IDBQueryExecutionService;

public class DBQueryExecutionService implements IDBQueryExecutionService {

    @Override
    public void addGameToDB(GameEntity gameEntity) {
        /* todo : write communication*/
    }

    @Override
    public void updateGameInDB(GameEntity gameEntity) {
        /* todo : write communication*/
    }

    @Override
    public GameEntity getGameFromDB(int gameId) {
        /* todo : write communication*/
        return null;
    }
}
