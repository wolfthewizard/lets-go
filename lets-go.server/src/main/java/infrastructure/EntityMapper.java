package infrastructure;

import contract.Change;
import contract.Coordinates;
import contract.enums.Winner;
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

        changeEntity.setX(change.getCoordinates().getX());
        changeEntity.setY(change.getCoordinates().getY());
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
        List<Change> changes = new ArrayList<>();
        for (ChangeEntity changeEntity : turnEntity.getChangeEntities()) {
            changes.add(changeEntityToChange(changeEntity));
        }

        return new TurnRecord(
                turnEntity.getTurnNumber(),
                changes
        );
    }

    @Override
    public Change changeEntityToChange(ChangeEntity changeEntity) {
        return new Change(changeEntity.getOccupancy(), new Coordinates(changeEntity.getX(), changeEntity.getY()));
    }

    @Override
    public void addTurnToGame(GameEntity gameEntity, TurnEntity turnEntity) {

        gameEntity.getTurnEntities().add(turnEntity);
        turnEntity.setGameEntity(gameEntity);
    }

    @Override
    public void setWinnerInGame(GameEntity gameEntity, Winner winner) {
        gameEntity.setWinner(winner);
    }
}
