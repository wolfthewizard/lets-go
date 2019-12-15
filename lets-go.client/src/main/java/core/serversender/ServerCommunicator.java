package core.serversender;

import contract.ActionDTO;
import contract.Coordinates;
import contract.ResponseDTO;
import contract.enums.ActionType;
import contract.enums.BoardSize;
import contract.enums.ResponseType;
import core.interfaces.*;
import infrastructure.ServerConnector;

import java.io.IOException;

public class ServerCommunicator implements IServerCommunicator {

    private IJsonParser jsonParser;
    private static boolean connectionClosed;
    private static IServerConnector serverConnector;
    private static IResponseNumberCounter responseNumberCounter;

    static {
        connectionClosed = false;

        try {
            restoreConnection();
        } catch (IOException e) {
            connectionClosed = true;
        }
    }

    private static void restoreConnection() throws IOException {

        serverConnector = new ServerConnector();
    }

    public ServerCommunicator(IJsonParser jsonParser, IServerResponseListener serverResponseListener) {

        if (connectionClosed) {
            connectionClosed = false;
            try {
                restoreConnection();
            } catch (IOException e) {
                serverResponseListener.passResponseDTO(new ResponseDTO(ResponseType.SERVER_ERROR));
            }
        }

        if (serverResponseListener != null) {
            responseNumberCounter = new ResponseNumberCounter();
            serverResponseListener.setResponseNumberCounter(responseNumberCounter);

            serverConnector.setServerResponseListener(serverResponseListener);
        }

        this.jsonParser = jsonParser;
    }

    public void sendStartGameMessage(BoardSize boardSize) {

        sendMessage(new ActionDTO(boardSize), 2);
    }

    public void sendMoveMessage(Coordinates coordinates) {

        sendMessage(new ActionDTO(coordinates), 2);
    }

    public void sendMovePassMessage() {

        sendMessage(new ActionDTO(ActionType.PASSMOVE), 2);
    }

    public void sendLeaveGameMessage() {

        serverConnector.sendMessage(jsonParser.parseActionToJson(new ActionDTO(ActionType.LEAVEGAME)));
        shutDownConnection();
    }

    private void sendMessage(ActionDTO actionDTO, int numberOfResposnses) {

        if (responseNumberCounter.getNumberOfResponsesToRead() == 0) {
            responseNumberCounter.addNumberOfResponsesToRead(numberOfResposnses);
            serverConnector.sendMessage(jsonParser.parseActionToJson(actionDTO));
        }
    }

    public void shutDownConnection() {

        connectionClosed = true;
        serverConnector.shutDown();
    }
}
