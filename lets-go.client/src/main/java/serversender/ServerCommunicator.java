package serversender;

import model.*;
import model.enums.ActionType;
import model.enums.BoardSize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerCommunicator {

    private IJsonParser jsonParser;
    private Socket socket;
    private PrintWriter outputWriter;
    private BufferedReader inputReader;
    private int playerId;

    public ServerCommunicator(IJsonParser jsonParser) {
        this.jsonParser = jsonParser;

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
        ActionDTO action = new ActionDTO(isMultiplayerGame, boardSize);

        sendMessage(action);

        try
        {
            playerId = Integer.parseInt(inputReader.readLine());
        }
        catch(IOException ex)
        {
            //server error
        }
    }
    public ResponseDTO sendMoveMessage(Coordinates coordinates) {
        ActionDTO action = new ActionDTO(playerId, coordinates);

        sendMessage(action);

        try
        {
            String responseJson;
            responseJson = inputReader.readLine();

            return jsonParser.parseJsonToResponse(responseJson);
        }
        catch(IOException ex)
        {
            return null;
        }
    }

    public void sendMovePassMessage() {
        ActionDTO action = new ActionDTO(playerId, ActionType.PASSMOVE);

        sendMessage(action);
    }

    public void sendLeaveGameMessage() {
        ActionDTO action = new ActionDTO(playerId, ActionType.LEAVEGAME);

        sendMessage(action);
    }

    private void sendMessage(ActionDTO actionDTO) {
        String json = jsonParser.parseActionToJson(actionDTO);

        outputWriter.print(json);
    }
}
