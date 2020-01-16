package infrastructure;

import contract.Change;
import contract.gamerecord.GameRecord;
import core.model.Game;

import java.util.List;

public interface IDBMediatorService {

    void addGame(Game game);
    void insertTurn(int gameId, List<Change> changes);
    GameRecord getGame(int gameId);
}
