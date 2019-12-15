package infrastructure;

import core.interfaces.IServerResponseListener;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerResponseRedirector extends Thread {

    private BufferedReader inputReader;
    private IServerResponseListener serverResponseListener;

    public ServerResponseRedirector(BufferedReader bufferedReader) {

        inputReader = bufferedReader;
    }

    public void setServerResponseListener(IServerResponseListener serverResponseListener) {

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

            if (responseJson == null) {
                return;
            }

            if (serverResponseListener != null) {
                serverResponseListener.responseReceived(responseJson);
            }
        }
    }

    public void stopThread() {

        interrupt();
        try {
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}