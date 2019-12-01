package main;

import main.helpers.IActionProcesser;
import main.helpers.IIdGenerator;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    private ServerSocket server;
    private IClientsManager clientsManager;


    public ServerListener(IActionProcesser actionProcesser, IIdGenerator idGenerator, IClientsManager clientsManager) throws IOException {

        this.clientsManager = clientsManager;
        server = new ServerSocket(1337);

        while (true) {

            Socket client = server.accept();

            ClientConnectionThread clientConnectionThread = new ClientConnectionThread(client,
                    actionProcesser, idGenerator.generateId());
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

