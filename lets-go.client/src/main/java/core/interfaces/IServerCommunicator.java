package core.interfaces;


import contract.Coordinates;
import contract.enums.BoardSize;

public interface IServerCommunicator {

    void sendStartGameMessage(BoardSize boardSize);

    void sendMoveMessage(Coordinates coordinates);

    void sendMovePassMessage();

    void sendLeaveGameMessage();
}
