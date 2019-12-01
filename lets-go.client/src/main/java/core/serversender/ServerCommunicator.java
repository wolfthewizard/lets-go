package core.serversender;

import core.contract.ActionDTO;
import core.contract.Coordinates;
import core.contract.ResponseDTO;
import core.contract.enums.ActionType;
import core.contract.enums.BoardSize;
import core.contract.enums.ResponseType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerCommunicator implements IServerCommunicator {

    private IJsonParser jsonParser;
    private Socket socket;
    private PrintWriter outputWriter;
    private BufferedReader inputReader;
    private OnServerResponseListener serverResponseListener;
    private Thread serverResponseAwaiter;

    public ServerCommunicator(IJsonParser jsonParser, OnServerResponseListener serverResponseListener) {

        this.jsonParser = jsonParser;
        this.serverResponseListener = serverResponseListener;

        try
        {
            socket = new Socket("localhost", 1337);
            outputWriter = new PrintWriter(socket.getOutputStream(), true);
            inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch(UnknownHostException e)
        {
            System.out.println("Unknown host: localhost"); System.exit(1);
        }
        catch(IOException e)
        {
            System.out.println("No I/O"); System.exit(1);
        }
    }

    public void sendStartGameMessage(boolean isMultiplayerGame, BoardSize boardSize) {

        final ActionDTO action = new ActionDTO(isMultiplayerGame, boardSize);

        if(serverResponseAwaiter == null || !serverResponseAwaiter.isAlive()) {

            serverResponseAwaiter = new Thread(() -> {

                sendMessage(action);

                waitAndPassResponse();
            });

            serverResponseAwaiter.start();
        }
    }

    public void sendMoveMessage(Coordinates coordinates) {

        final ActionDTO action = new ActionDTO(coordinates);

        if(serverResponseAwaiter == null || !serverResponseAwaiter.isAlive())
        {
            serverResponseAwaiter = new Thread(() -> {

                sendMessage(action);

                waitAndPassResponse();
            });

            serverResponseAwaiter.start();
        }
    }

    public void sendMovePassMessage() {

        final ActionDTO action = new ActionDTO(ActionType.PASSMOVE);

        if(serverResponseAwaiter == null || !serverResponseAwaiter.isAlive())
        {
            serverResponseAwaiter = new Thread(() -> {

                sendMessage(action);

                waitAndPassResponse();
            });

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

    private void waitAndPassResponse() {
        try
        {
            String responseJson= inputReader.readLine();

            ResponseDTO responseDTO = jsonParser.parseJsonToResponse(responseJson);
            serverResponseListener.responseReceived(responseDTO);

            if(responseDTO.getResponseType()!=ResponseType.SERVERERROR &&
                    responseDTO.getResponseType() != ResponseType.INVALIDMOVE) {

                responseJson = inputReader.readLine();
                serverResponseListener.responseReceived(jsonParser.parseJsonToResponse(responseJson));
            }
        }
        catch(IOException ex)
        {
            serverResponseListener.responseReceived(new ResponseDTO(ResponseType.SERVERERROR));
        }
    }

    public void shutDownConnection() {

        outputWriter.close();
        try {
            inputReader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
