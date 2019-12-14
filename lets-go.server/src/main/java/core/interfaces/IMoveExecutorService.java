package core.interfaces;

import contract.ResponseDTO;
import core.model.Move;
import core.model.MoveResponse;

public interface IMoveExecutorService {

    MoveResponse executeMove(Move move);
}
