package core.serversender;

import core.contract.Coordinates;
import core.contract.BoardSize;

public interface IServerCommunicator {

    void sendStartGameMessage(boolean isMultiplayerGame, BoardSize boardSize);

    void sendMoveMessage(Coordinates coordinates);

    void sendMovePassMessage();

    void sendLeaveGameMessage();
}
