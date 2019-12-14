package main;

import main.helpers.IActionProcesser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    private ServerSocket server;
    private IClientsManager clientsManager;


    public ServerListener(IActionProcesser actionProcesser, IClientsManager clientsManager) throws IOException {

        this.clientsManager = clientsManager;
        server = new ServerSocket(1337);

        while (true) {

            Socket client = server.accept();

            ClientConnectionThread clientConnectionThread = new ClientConnectionThread(client,
                    actionProcesser, clientsManager.getAllClients().size()+1);
            clientsManager.addClient(clientConnectionThread);

            clientConnectionThread.start();
        }
    }

    private void closeConnection() throws IOException {

        for (ClientConnectionThread thread : clientsManager.getAllClients()) {
            thread.closeConnection();
        }
        server.close();
    }
}

