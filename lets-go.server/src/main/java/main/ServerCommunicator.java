package main;

import main.model.ActionDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCommunicator {

    private ServerSocket server;
    private Socket client;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;
    private IActionProcesser actionProcesser;

    public ServerCommunicator(IJsonParser parser, IActionProcesser actionProcesser)
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
            closeConnection();
            System.exit(-1);
        }
    }

    private void listen() throws IOException {
        while(true)
        {
            String message = inputReader.readLine();

            String response = actionProcesser.ProcessAction(message);

            outputWriter.print(response);
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

