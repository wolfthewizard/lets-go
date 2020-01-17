package infrastructure;

import contract.Change;
import contract.gamerecord.GameRecord;
import contract.gamerecord.TurnRecord;
import core.model.Game;
import core.model.entities.ChangeEntity;
import core.model.entities.GameEntity;
import core.model.entities.TurnEntity;
import infrastructure.services.IEntityMapper;

import java.util.ArrayList;
import java.util.List;

public class EntityMapper implements IEntityMapper {

    @Override
    public GameEntity gameToGameEntity(Game game) {
        GameEntity gameEntity = new GameEntity();

        gameEntity.setId(game.getId());
        gameEntity.setBoardSize(game.getBoardSize());
        gameEntity.setGameDate(new java.sql.Date(System.currentTimeMillis()));
        gameEntity.setTurnEntities(new ArrayList<>());

        return gameEntity;
    }

    @Override
    public TurnEntity createTurnEntity(int turnNumber, List<ChangeEntity> changeEntityList) {
        TurnEntity turnEntity = new TurnEntity();

        turnEntity.setTurnNumber(turnNumber);
        turnEntity.setChangeEntities(changeEntityList);

        for (ChangeEntity changeEntity : changeEntityList) {
            changeEntity.setTurnEntity(turnEntity);
        }

        return turnEntity;
    }

    @Override
    public ChangeEntity changeToChangeEntity(Change change) {
        ChangeEntity changeEntity = new ChangeEntity();

        changeEntity.setCoordinates(change.getCoordinates());
        changeEntity.setOccupancy(change.getOccupancy());

        return changeEntity;
    }

    @Override
    public GameRecord gameEntityToGameRecord(GameEntity gameEntity) {
        List<TurnRecord> turnRecords = new ArrayList<>();
        for (TurnEntity turnEntity : gameEntity.getTurnEntities()) {
            turnRecords.add(turnEntityToTurnRecord(turnEntity));
        }

        return new GameRecord(
                gameEntity.getBoardSize(),
                gameEntity.getWinner(),
                gameEntity.getGameDate(),
                turnRecords
        );
    }

    @Override
    public TurnRecord turnEntityToTurnRecord(TurnEntity turnEntity) {
        return null;
    }

    @Override
    public Change changeEntityToChange(ChangeEntity changeEntity) {
        return null;
    }

    @Override
    public void addTurnToGame(GameEntity gameEntity, TurnEntity turnEntity) {

        gameEntity.getTurnEntities().add(turnEntity);
        turnEntity.setGameEntity(gameEntity);
    }
}