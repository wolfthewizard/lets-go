package infrastructure.services;

import contract.Change;
import contract.gamerecord.GameRecord;
import core.model.Game;

import java.util.List;

public interface IDBMediationService {

    void addGame(Game game);
    void insertTurn(int gameId, int turnNumber, List<Change> changes);
    GameRecord getGame(int gameId);
}
