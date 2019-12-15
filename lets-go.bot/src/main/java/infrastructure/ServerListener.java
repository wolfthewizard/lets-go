package infrastructure;

import core.interfaces.ICommunicatorListener;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerListener extends Thread {

    private final BufferedReader inputReader;
    private ICommunicatorListener communicatorListener;

    public ServerListener(BufferedReader inputReader) {
        this.inputReader = inputReader;
    }

    public void setCommunicatorListener(ICommunicatorListener communicatorListener) {
        this.communicatorListener = communicatorListener;
    }

    @Override
    public void run() {
        while (true) {
            String response;

            try {
                response = inputReader.readLine();
                if (response == null) {
                    return;
                } else {
                    communicatorListener.responseFetched(response);
                }
            } catch (IOException e) {
                System.exit(0);
                return;
            }

        }
    }

    public void stopThread() throws IOException {
        interrupt();
        inputReader.close();
    }
}
