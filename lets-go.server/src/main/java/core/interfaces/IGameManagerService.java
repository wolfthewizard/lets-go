package core.interfaces;

import contract.enums.BoardSize;
import contract.gamerecord.GameRecord;

public interface IGameManagerService {

    int createNewGame(BoardSize boardSize);
    void cancelGame(int gameId);
    GameRecord getGame(int gameId);
}
