package infrastructure;

import contract.ActionDTO;
import contract.Coordinates;
import contract.enums.ActionType;
import contract.enums.BoardSize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSender {

    private static PrintWriter outputWriter;
    private static Socket socket;
    private ServerListener serverListener;

    public ServerSender() {
        try {
            socket = new Socket("localhost", 1337);
            outputWriter = new PrintWriter(socket.getOutputStream(), true);
            serverListener = new ServerListener(
                    new BufferedReader(new InputStreamReader(socket.getInputStream())));
        } catch (IOException e) {
            System.out.println("Critical server error !");
        }

        serverListener.start();
    }

    public void sendAction(String action){
        outputWriter.println(action);
    }
}
