package main;

import main.helpers.actionprocesser.IActionProcesser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread{

    private ServerSocket server;
    private IClientsManager clientsManager;
    private IActionProcesser actionProcesser;


    public ServerListenerThread(IActionProcesser actionProcesser, IClientsManager clientsManager) throws IOException {

        this.clientsManager = clientsManager;
        this.actionProcesser = actionProcesser;
        server = new ServerSocket(1337);
    }

    @Override
    public void run(){
        while (true) {

            Socket client;
            try {
                client = server.accept();
            } catch (IOException e) {
                return;
            }

            ClientConnectionThread clientConnectionThread = new ClientConnectionThread(client,
                    actionProcesser, clientsManager.getAllClients().size() + 1);
            clientsManager.addClient(clientConnectionThread);

            clientConnectionThread.start();
        }
    }

    public void closeConnection() throws IOException {

        interrupt();
        actionProcesser.closeAllConnections();
        server.close();
    }
}

