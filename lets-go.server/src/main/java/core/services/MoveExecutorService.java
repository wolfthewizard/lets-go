package core.services;

import core.interfaces.IGameRepository;
import core.interfaces.IMoveExecutorService;
import core.interfaces.IMoveValidator;
import core.model.Move;
import core.model.MoveResponse;

public class MoveExecutorService implements IMoveExecutorService {

    private IGameRepository gameRepository;
    private IMoveValidator moveValidator;

    public MoveExecutorService(IGameRepository gameRepository, IMoveValidator moveValidator) {

        this.gameRepository = gameRepository;
        this.moveValidator = moveValidator;
    }

    @Override
    public MoveResponse executeMove(Move move) {
        return null;
    }
}
