package infrastructure;

import contract.Change;
import core.model.Game;
import core.model.entities.GameEntity;
import core.model.entities.TurnEntity;

import java.util.List;

public interface IEntityMapper {

    GameEntity gameToGameEntity(Game game);
    TurnEntity changesToTurnEntity(List<Change> changes);
}
