package infrastructure;

import core.interfaces.IServerConnector;
import core.interfaces.IServerResponseListener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnector implements IServerConnector {

    private PrintWriter outputWriter;
    private Socket socket;
    private ServerResponseRedirector serverResponseRedirector;

    public ServerConnector(){

    }

    public void resetConnection() throws IOException {
        socket = new Socket("localhost", 1337);
        outputWriter = new PrintWriter(socket.getOutputStream(), true);
        serverResponseRedirector = new ServerResponseRedirector(
                new BufferedReader(new InputStreamReader(socket.getInputStream())));
        serverResponseRedirector.start();
    }

    public void resetConnection(String connectionAddress) throws IOException {
        socket = new Socket(connectionAddress, 1337);
        outputWriter = new PrintWriter(socket.getOutputStream(), true);
        serverResponseRedirector = new ServerResponseRedirector(
                new BufferedReader(new InputStreamReader(socket.getInputStream())));
        serverResponseRedirector.start();
    }

    public void setServerResponseListener(IServerResponseListener serverResponseListener) {
        serverResponseRedirector.setServerResponseListener(serverResponseListener);
    }

    public void sendMessage(String message){
        outputWriter.println(message);
    }

    public void shutDown(){
        outputWriter.close();
        try {
            serverResponseRedirector.stopThread();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
