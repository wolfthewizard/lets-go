package infrastructure;

import java.io.BufferedReader;

public class ServerListener extends Thread{

    private final BufferedReader inputReader;

    public ServerListener(BufferedReader inputReader){
        this.inputReader = inputReader;
    }

    public void run() {

    }
}
