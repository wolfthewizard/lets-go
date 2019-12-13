package core.interfaces;

import contract.Change;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.model.Color;
import core.model.Game;

import java.util.ArrayList;

public interface IGameRepository {

    void CreateBotGame(int id, BoardSize boardSize, Color startingPlayer, Color botPlayer);

    void CreateMultiplayerGame(int id, BoardSize boardSize, Color startingPlayer);

    Occupancy[][] getGameBoard(int gameId);

    void performMove(int gameId, ArrayList<Change> changes);

    Game getGame(int gameId);
}