package core.serversender;

import contract.ActionDTO;
import contract.ResponseDTO;
import contract.enums.ResponseType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerResponseAwaiterThread extends Thread {
    private PrintWriter outputWriter;
    private IJsonParser jsonParser;
    private BufferedReader inputReader;
    private ActionDTO action;
    private OnServerResponseListener serverResponseListener;
    private boolean isRunning = false;

    public ServerResponseAwaiterThread(PrintWriter printWriter, BufferedReader bufferedReader, IJsonParser jsonParser,
                                       ActionDTO action, OnServerResponseListener serverResponseListener) {
        outputWriter = printWriter;
        inputReader = bufferedReader;
        this.jsonParser = jsonParser;
        this.action = action;
        this.serverResponseListener = serverResponseListener;
    }

    public void run() {

        isRunning = true;

        sendMessage(action);

        waitAndPassResponse();
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

            if(responseDTO.getResponseType()!= ResponseType.SERVER_ERROR &&
                    responseDTO.getResponseType() != ResponseType.INVALID_MOVE) {

                responseJson = inputReader.readLine();
                serverResponseListener.responseReceived(jsonParser.parseJsonToResponse(responseJson));
            }

            isRunning=false;

            responseJson = inputReader.readLine();
            serverResponseListener.responseReceived(jsonParser.parseJsonToResponse(responseJson));
        }
        catch(IOException ex)
        {
            serverResponseListener.responseReceived(new ResponseDTO(ResponseType.SERVER_ERROR));
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
