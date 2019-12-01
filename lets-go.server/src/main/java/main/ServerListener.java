package main;

import main.helpers.IActionProcesser;
import main.helpers.IJsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    private ServerSocket server;
    private Socket client;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;
    private IActionProcesser actionProcesser;

    public ServerListener(IJsonParser parser, IActionProcesser actionProcesser)
    {
        this.actionProcesser = actionProcesser;

        try
        {
            server = new ServerSocket(1337);

            client = server.accept();
            inputReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputWriter = new PrintWriter(client.getOutputStream(), true);

            listen();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            closeConnection();
            System.exit(-1);
        }
    }

    private void listen() throws IOException {
        while(true)
        {
            String message = inputReader.readLine();
            System.out.println("received:"+message);
            String response = actionProcesser.ProcessAction(message);

            outputWriter.println(response);
        }
    }

    private void closeConnection()
    {
        try
        {
            inputReader.close();
            outputWriter.close();
            client.close();
            server.close();
        }
        catch (IOException e)
        {
            System.out.println("Could not close.");
            System.exit(-1);
        }
    }
}

