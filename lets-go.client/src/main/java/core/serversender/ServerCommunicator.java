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
    private static Socket socket;
    private static PrintWriter outputWriter;
    private static BufferedReader inputReader;
    private boolean connectionClosed = false;
    private OnServerResponseListener serverResponseListener;
    private ServerResponseAwaiterThread serverResponseAwaiter;

    static  {
        restoreConnection();
    }

    private static void restoreConnection() {
        try
        {
            socket = new Socket("localhost", 1337);
            outputWriter = new PrintWriter(socket.getOutputStream(), true);
            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public ServerCommunicator(IJsonParser jsonParser, OnServerResponseListener serverResponseListener) {

        if(connectionClosed) {
            restoreConnection();
        }
        this.jsonParser = jsonParser;
        this.serverResponseListener = serverResponseListener;
    }

    public void sendStartGameMessage(boolean isMultiplayerGame, BoardSize boardSize) {

        if(serverResponseAwaiter == null || !serverResponseAwaiter.isRunning()) {

            serverResponseAwaiter.interrupt();

            serverResponseAwaiter = new ServerResponseAwaiterThread(outputWriter, inputReader, jsonParser,
                    new ActionDTO(isMultiplayerGame, boardSize), serverResponseListener);

            serverResponseAwaiter.start();
        }
    }

    public void sendMoveMessage(Coordinates coordinates) {

        if(serverResponseAwaiter == null || !serverResponseAwaiter.isRunning()) {

            serverResponseAwaiter.interrupt();

            serverResponseAwaiter = new ServerResponseAwaiterThread(outputWriter, inputReader, jsonParser,
                    new ActionDTO(coordinates), serverResponseListener);

            serverResponseAwaiter.start();
        }
    }

    public void sendMovePassMessage() {

        if(serverResponseAwaiter == null || !serverResponseAwaiter.isRunning()) {

            serverResponseAwaiter.interrupt();

            serverResponseAwaiter = new ServerResponseAwaiterThread(outputWriter, inputReader, jsonParser,
                    new ActionDTO(ActionType.PASSMOVE), serverResponseListener);

            serverResponseAwaiter.start();
        }
    }

    public void sendLeaveGameMessage() {

        ActionDTO action = new ActionDTO(ActionType.LEAVEGAME);

        sendMessage(action);
    }

    private void sendMessage(ActionDTO actionDTO) {

        String json = jsonParser.parseActionToJson(actionDTO);

        outputWriter.println(json);
    }

    public void shutDownConnection() {

        serverResponseAwaiter.interrupt();
        serverResponseAwaiter = null;
        connectionClosed = true;
        outputWriter.close();
        try {
            inputReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
