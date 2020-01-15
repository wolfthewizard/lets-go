package core.interfaces;

import contract.enums.BoardSize;

public interface IRewindManager {

    void rewind(BoardSize boardSize);
    void handleInvalidId();
}
