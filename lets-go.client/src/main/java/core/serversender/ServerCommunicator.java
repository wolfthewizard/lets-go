package core.serversender;

import core.contract.ActionDTO;
import core.contract.Coordinates;
import core.contract.ResponseDTO;
import core.contract.ActionType;
import core.contract.BoardSize;

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
    private int playerId;
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


        serverResponseAwaiter = new Thread(new Runnable() {
            public void run()
            {

                sendMessage(action);

                try
                {
                    String response = inputReader.readLine();//0 if failed
                    System.out.println(response);
                    serverResponseListener.responseReceived(jsonParser.parseJsonToResponse(response));
                }
                catch(IOException ex)
                {
                    serverResponseListener.responseReceived(null);
                    //server error
                }
            }
        });

        serverResponseAwaiter.start();

    }
    public void sendMoveMessage(Coordinates coordinates) {
        final ActionDTO action = new ActionDTO(playerId, coordinates);

        if(serverResponseAwaiter == null || !serverResponseAwaiter.isAlive())
        {
            serverResponseAwaiter = new Thread(new Runnable() {
                public void run()
                {

                    sendMessage(action);

                    try
                    {
                        String responseJson;
                        responseJson = inputReader.readLine();

                        serverResponseListener.responseReceived(jsonParser.parseJsonToResponse(responseJson));
                    }
                    catch(IOException ex)
                    {
                        serverResponseListener.responseReceived(null);
                    }
                }
            });

            serverResponseAwaiter.start();
        }

    }

    public void sendMovePassMessage() {
        final ActionDTO action = new ActionDTO(playerId, ActionType.PASSMOVE);

        if(serverResponseAwaiter == null || !serverResponseAwaiter.isAlive())
        {
            serverResponseAwaiter = new Thread(new Runnable() {
                public void run()
                {

                    sendMessage(action);

                    try
                    {
                        String responseJson;
                        responseJson = inputReader.readLine();

                        serverResponseListener.responseReceived(jsonParser.parseJsonToResponse(responseJson));
                    }
                    catch(IOException ex)
                    {
                        serverResponseListener.responseReceived(null);
                    }
                }
            });

            serverResponseAwaiter.start();
        }
    }

    public void sendLeaveGameMessage() {
        ActionDTO action = new ActionDTO(playerId, ActionType.LEAVEGAME);

        sendMessage(action);
    }

    private void sendMessage(ActionDTO actionDTO) {
        String json = jsonParser.parseActionToJson(actionDTO);

        outputWriter.println(json);
    }
}
