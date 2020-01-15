package core.interfaces;

import contract.ResponseDTO;
import contract.enums.BoardSize;
import contract.gamerecord.GameRecord;

public interface IRewindManager {

    void rewind(GameRecord gameRecord, BoardSize boardSize);
    void handleInvalidId();
}
