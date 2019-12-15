package core.interfaces;

import contract.enums.BoardSize;

public interface IGameManagerService {

    int createNewGame(BoardSize boardSize);
    void cancelGame(int gameId);
}
