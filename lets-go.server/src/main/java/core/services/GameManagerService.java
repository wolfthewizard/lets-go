package core.services;

import contract.enums.BoardSize;
import contract.gamerecord.GameRecord;
import core.interfaces.IGameManagerService;
import core.interfaces.IGameRepository;
import core.model.Board;
import core.model.Game;
import infrastructure.services.IDBMediationService;

public class GameManagerService implements IGameManagerService {

    private final IGameRepository gameRepository;
    private final IDBMediationService dbMediationService;

    public GameManagerService(IGameRepository gameRepository, IDBMediationService dbMediationService) {
        this.gameRepository = gameRepository;
        this.dbMediationService = dbMediationService;
    }

    @Override
    public int createNewGame(BoardSize boardSize) {

        int id = gameRepository.fetchId();
        Game newGame = new Game(id, new Board(boardSize), boardSize);
        gameRepository.createGame(newGame);
        dbMediationService.addGame(newGame);
        return id;
    }

    @Override
    public void cancelGame(int gameId) {
        gameRepository.removeGame(gameId);
    }

    @Override
    public GameRecord getGame(int gameId){
        return dbMediationService.getGame(gameId);
    }
}
