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

    private static boolean initialized;
    private static ServerCommunicator instance;
    private IJsonParser jsonParser;
    private boolean connectionClosed = true;
    private IServerConnector serverConnector;
    private IResponseNumberCounter responseNumberCounter;

    private ServerCommunicator(IJsonParser jsonParser, IResponseNumberCounter responseNumberCounter,
                               IServerConnector serverConnector) {

        this.responseNumberCounter = responseNumberCounter;
        this.jsonParser = jsonParser;
        this.serverConnector = serverConnector;
    }

    public static void initialize(IJsonParser jsonParser, IResponseNumberCounter responseNumberCounter,
                                  IServerConnector serverConnector) {

        if (!initialized) {
            instance = new ServerCommunicator(jsonParser, responseNumberCounter, serverConnector);
            initialized = true;
        }
    }

    public static ServerCommunicator getInstance() {

        if (initialized) {
            return instance;
        }

        throw new IllegalCallerException("ServerConnector need to be initialized before accessing");
    }

    public void setServerResponseListener(IServerResponseListener serverResponseListener) {

        if (connectionClosed) {
            connectionClosed = false;
            try {
                serverConnector.resetConnection();
            } catch (IOException e) {
                serverResponseListener.passResponseDTO(new ResponseDTO(ResponseType.SERVER_ERROR));
                return;
            }
        }

        responseNumberCounter.resetCounter();
        serverResponseListener.setResponseNumberCounter(responseNumberCounter);

        serverConnector.setServerResponseListener(serverResponseListener);
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

    private void shutDownConnection() {

        connectionClosed = true;
        serverConnector.shutDown();
    }
}
