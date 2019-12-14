package infrastructure;

import core.interfaces.ICommunicatorListener;
import core.interfaces.IServerConnector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnector implements IServerConnector {

    private static Socket socket;
    private ServerListener serverListener;
    private ServerSender serverSender;

    public ServerConnector(){
        try {
            socket = new Socket("localhost", 1337);
            serverSender=new ServerSender(new PrintWriter(socket.getOutputStream(), true));
            serverListener = new ServerListener(
                    new BufferedReader(new InputStreamReader(socket.getInputStream())));
        } catch (IOException e) {
            System.out.println("Critical server error !");
        }
    }

    public void StartListening(ICommunicatorListener communicatorListener){
        serverListener.setCommunicatorListener(communicatorListener);
        serverListener.start();
    }


    public void sendAction(String action){
        serverSender.sendAction(action);
    }
}
