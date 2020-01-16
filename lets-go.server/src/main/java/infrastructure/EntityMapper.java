package infrastructure;

import contract.Change;
import core.model.Game;
import core.model.entities.GameEntity;
import core.model.entities.TurnEntity;

import java.util.List;

public class EntityMapper implements IEntityMapper {

    @Override
    public GameEntity gameToGameEntity(Game game) {
        GameEntity gameEntity = new GameEntity();

        gameEntity.setId(game.getId());
        gameEntity.setBoardSize(game.getBoardSize());
        gameEntity.setGameDate(new java.sql.Date(System.currentTimeMillis()));

        return gameEntity;
    }

    @Override
    public TurnEntity changesToTurnEntity(List<Change> changes) {
        /* todo : implement when structure of entity is decided*/
        return null;
    }
}
