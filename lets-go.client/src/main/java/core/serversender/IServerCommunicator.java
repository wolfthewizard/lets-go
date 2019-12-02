package core.serversender;


import contract.Coordinates;
import contract.enums.BoardSize;

public interface IServerCommunicator {

    void sendStartGameMessage(boolean isMultiplayerGame, BoardSize boardSize);

    void sendMoveMessage(Coordinates coordinates);

    void sendMovePassMessage();

    void sendLeaveGameMessage();

    void shutDownConnection();
}
