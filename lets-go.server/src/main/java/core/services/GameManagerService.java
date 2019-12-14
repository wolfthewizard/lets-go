package core.services;

import contract.enums.BoardSize;
import core.interfaces.IGameManagerService;
import core.interfaces.IGameRepository;
import core.model.Board;
import core.model.Game;

public class GameManagerService implements IGameManagerService {

    private IGameRepository gameRepository;

    public GameManagerService(IGameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public int createNewGame(BoardSize boardSize) {

        int id = gameRepository.fetchId();
        gameRepository.createGame(new Game(id, new Board(boardSize), boardSize));
        return id;
    }

    @Override
    public void cancelGame(int leftPlayerId) {

    }
}
