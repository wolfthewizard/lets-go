package main;

import main.helpers.IActionProcesser;
import main.helpers.IJsonParser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerListener {

    private ServerSocket server;
    ArrayList<ClientConnectionThread> clients = new ArrayList<>();

    public ServerListener(IJsonParser parser, IActionProcesser actionProcesser)
    {
        try
        {
            server = new ServerSocket(1337);

            while (true) {
                Socket client = server.accept();

                ClientConnectionThread clientConnectionThread = new ClientConnectionThread(client, actionProcesser);
                clients.add(clientConnectionThread);

                clientConnectionThread.start();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            closeConnection();
            System.exit(-1);
        }
    }

    private void closeConnection()
    {
        try
        {
            for (ClientConnectionThread thread :clients) {
                thread.closeConnection();
            }
            server.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }
}

