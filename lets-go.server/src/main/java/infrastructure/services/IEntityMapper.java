package infrastructure.services;

import contract.Change;
import contract.enums.Winner;
import contract.gamerecord.GameRecord;
import contract.gamerecord.TurnRecord;
import core.model.Game;
import core.model.entities.ChangeEntity;
import core.model.entities.GameEntity;
import core.model.entities.TurnEntity;

import java.util.List;

public interface IEntityMapper {

    GameEntity gameToGameEntity(Game game);
    TurnEntity createTurnEntity(int turnNumber, List<ChangeEntity> changeEntityList);
    ChangeEntity changeToChangeEntity(Change change);

    GameRecord gameEntityToGameRecord(GameEntity gameEntity);
    TurnRecord turnEntityToTurnRecord(TurnEntity turnEntity);
    Change changeEntityToChange(ChangeEntity changeEntity);

    void addTurnToGame(GameEntity gameEntity, TurnEntity turnEntity);
    void setWinnerInGame(GameEntity gameEntity, Winner winner);
}
