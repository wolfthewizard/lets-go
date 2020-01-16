package infrastructure;

import contract.Change;
import contract.gamerecord.GameRecord;
import core.model.Game;
import core.model.entities.GameEntity;

import java.util.List;

public class DBMediatorService implements IDBMediatorService {

    private IEntityMapper entityMapper;

    public DBMediatorService(IEntityMapper entityMapper) {
        this.entityMapper = entityMapper;
    }

    @Override
    public void addGame(Game game) {

        GameEntity gameEntity = entityMapper.gameToGameEntity(game);
        /* todo : syntax for insertion into db*/
    }

    @Override
    public void insertTurn(int gameId, List<Change> changes) {

        /* todo : syntax for insertion into db*/
    }

    @Override
    public GameRecord getGame(int gameId) {
        /* todo : syntax for retrieval from db*/

        return null;
    }
}
