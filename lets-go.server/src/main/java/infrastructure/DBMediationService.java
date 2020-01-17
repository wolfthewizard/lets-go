package infrastructure;

import contract.Change;
import contract.gamerecord.GameRecord;
import core.model.Game;
import core.model.entities.ChangeEntity;
import core.model.entities.GameEntity;
import core.model.entities.TurnEntity;
import infrastructure.services.IDBMediationService;
import infrastructure.services.IDBQueryExecutionService;
import infrastructure.services.IEntityMapper;

import java.util.ArrayList;
import java.util.List;

public class DBMediationService implements IDBMediationService {

    private IEntityMapper entityMapper;
    private IDBQueryExecutionService queryExecutionService;

    public DBMediationService(IEntityMapper entityMapper, IDBQueryExecutionService queryExecutionService) {
        this.entityMapper = entityMapper;
        this.queryExecutionService = queryExecutionService;
    }

    @Override
    public void addGame(Game game) {

        GameEntity gameEntity = entityMapper.gameToGameEntity(game);
        queryExecutionService.addGameToDB(gameEntity);
    }

    @Override
    public void insertTurn(int gameId, int turnNumber, List<Change> changes) {

        List<ChangeEntity> changeEntityList = new ArrayList<>();
        for (Change change : changes) {
            changeEntityList.add(entityMapper.changeToChangeEntity(change));
        }

        TurnEntity turnEntity = entityMapper.createTurnEntity(turnNumber, changeEntityList);

        GameEntity gameEntity = queryExecutionService.getGameFromDB(gameId);
        entityMapper.addTurnToGame(gameEntity, turnEntity);

        queryExecutionService.updateGameInDB(gameEntity);
    }

    @Override
    public GameRecord getGame(int gameId) {
        GameEntity gameEntity = queryExecutionService.getGameFromDB(gameId);

        return null;
        // todo : finish
    }
}
