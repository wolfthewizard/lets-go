package infrastructure.services;

import contract.Change;
import contract.enums.Winner;
import contract.gamerecord.GameRecord;
import core.model.Game;

import java.util.List;

public interface IDBMediationService {

    void addGame(Game game);
    void insertTurn(int gameId, int turnNumber, List<Change> changes);
    void setWinner(int gameId, Winner winner);
    GameRecord getGame(int gameId);
}
