package infrastructure;

import core.interfaces.ICommunicatorListener;
import core.interfaces.IServerResponseReceiver;

import javax.imageio.spi.IIOServiceProvider;
import java.io.BufferedReader;

public class ServerListener extends Thread{

    private final BufferedReader inputReader;
    private ICommunicatorListener communicatorListener;

    public ServerListener(BufferedReader inputReader){
        this.inputReader = inputReader;
    }

    public void setCommunicatorListener(ICommunicatorListener communicatorListener) {
        this.communicatorListener = communicatorListener;
    }

    public void run() {

    }
}