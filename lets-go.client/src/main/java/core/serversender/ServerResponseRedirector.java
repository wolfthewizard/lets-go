package core.serversender;

import contract.ActionDTO;
import contract.ResponseDTO;
import contract.enums.ResponseType;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerResponseRedirector extends Thread {

    private IJsonParser jsonParser;
    private BufferedReader inputReader;
    private OnServerResponseListener serverResponseListener;
    private int numberOfResponsesToRead = 0;

    public ServerResponseRedirector(BufferedReader bufferedReader) {

        inputReader = bufferedReader;
        this.jsonParser = new JsonParser();
    }

    public void setServerResponseListener(OnServerResponseListener serverResponseListener) {

        this.serverResponseListener = serverResponseListener;
    }

    public void run() {

        while (true) {
            String responseJson;
            try {
                responseJson = inputReader.readLine();
            } catch (IOException e) {
                return;
            }

            numberOfResponsesToRead--;

            if(serverResponseListener != null) {
                serverResponseListener.responseReceived(jsonParser.parseJsonToResponse(responseJson));
            }
        }
    }

    public void stopThread() {

        try {
            inputReader.close();
            interrupt();
        } catch (IOException e) {
            return;
        }
        interrupt();
    }

    public void addNumberOfResponsesToRead(int numberOfResponsesToRead) {
        this.numberOfResponsesToRead += numberOfResponsesToRead;
    }

    public int getNumberOfResponsesToRead() {
        return numberOfResponsesToRead;
    }
}