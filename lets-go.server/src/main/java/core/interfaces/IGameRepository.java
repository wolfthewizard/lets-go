package core.interfaces;

import contract.Change;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.model.Board;
import core.model.Color;
import core.model.Game;

import java.util.ArrayList;

public interface IGameRepository {

    void createGame(Game game);

    Board getGameBoard(int gameId);

    int fetchId();
}