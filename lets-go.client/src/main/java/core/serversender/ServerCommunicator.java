package core.serversender;

import contract.ActionDTO;
import contract.Coordinates;
import contract.ResponseDTO;
import contract.enums.ActionType;
import contract.enums.BoardSize;
import contract.enums.ResponseType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerCommunicator implements IServerCommunicator {

    private IJsonParser jsonParser;
    private static PrintWriter outputWriter;
    private static Socket socket;
    private static boolean connectionClosed;
    private static ServerResponseRedirector serverResponseRedirector;

    static {
        connectionClosed = false;

        try {
            restoreConnection();
        } catch (IOException e) {
            connectionClosed = true;
        }
    }

    private static void restoreConnection() throws IOException {

        socket = new Socket("localhost", 1337);
        outputWriter = new PrintWriter(socket.getOutputStream(), true);
        serverResponseRedirector = new ServerResponseRedirector(
                new BufferedReader(new InputStreamReader(socket.getInputStream())));
        serverResponseRedirector.start();
    }

    public ServerCommunicator(IJsonParser jsonParser, OnServerResponseListener serverResponseListener) {

        if (connectionClosed) {
            connectionClosed = false;
            try {
                restoreConnection();
            } catch (IOException e) {
                serverResponseListener.responseReceived(new ResponseDTO(ResponseType.SERVER_ERROR));
            }
        }
        serverResponseRedirector.setServerResponseListener(serverResponseListener);

        this.jsonParser = jsonParser;
    }

    public void sendStartGameMessage(boolean isMultiplayerGame, BoardSize boardSize) {

        sendMessage(new ActionDTO(isMultiplayerGame, boardSize), 2);
    }

    public void sendMoveMessage(Coordinates coordinates) {

        sendMessage(new ActionDTO(coordinates), 2);
    }

    public void sendMovePassMessage() {

        sendMessage(new ActionDTO(ActionType.PASSMOVE), 2);
    }

    public void sendLeaveGameMessage() {

        sendMessage(new ActionDTO(ActionType.LEAVEGAME), 0);
    }

    private void sendMessage(ActionDTO actionDTO, int numberOfResposnses) {

        if(serverResponseRedirector.getNumberOfResponsesToRead() == 0) {
            serverResponseRedirector.addNumberOfResponsesToRead(numberOfResposnses);
            outputWriter.println(jsonParser.parseActionToJson(actionDTO));
        }
    }

    public void shutDownConnection() {

        serverResponseRedirector.stopThread();
        connectionClosed = true;
        outputWriter.close();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
