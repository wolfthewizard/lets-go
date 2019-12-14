package core.interfaces;

import contract.Coordinates;
import contract.enums.BoardSize;

public interface ICommunicatorSender {

    void sendStartGameMessage(BoardSize boardSize);

    void sendMoveMessage(Coordinates coordinates);

    void sendMovePassMessage();

    void sendLeaveGameMessage();
}
