package infrastructure;

import core.interfaces.ICommunicatorListener;
import core.interfaces.IServerResponseReceiver;

import javax.imageio.spi.IIOServiceProvider;
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

    public void run() {
        while (true) {
            String response;

            try {
                response = inputReader.readLine();
                if (response != null) {
                    communicatorListener.responseFetched(response);
                } else {
                    return;
                }
            } catch (IOException e) {
                System.out.println("server out");
                System.exit(0);
                return;
            }

        }
    }
}
