package infrastructure;

import contract.ActionDTO;
import contract.ResponseDTO;
import contract.enums.ResponseType;
import core.serversender.IJsonParser;
import core.serversender.JsonParser;
import core.serversender.OnServerResponseListener;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerResponseRedirector extends Thread {

    private BufferedReader inputReader;
    private OnServerResponseListener serverResponseListener;

    public ServerResponseRedirector(BufferedReader bufferedReader) {

        inputReader = bufferedReader;
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

            if(serverResponseListener != null) {
                serverResponseListener.responseReceived(responseJson);
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
}