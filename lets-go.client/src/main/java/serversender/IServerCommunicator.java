package serversender;

import model.Coordinates;
import model.ResponseDTO;
import model.enums.BoardSize;

public interface IServerCommunicator {

    void sendStartGameMessage(boolean isMultiplayerGame, BoardSize boardSize);

    ResponseDTO sendMoveMessage(Coordinates coordinates);

    void sendMovePassMessage();

    void sendLeaveGameMessage();
}
