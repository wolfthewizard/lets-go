package main;

import main.helpers.IActionProcesser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnectionThread extends Thread {

    private Socket client;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;
    private IActionProcesser actionProcesser;

    public ClientConnectionThread(Socket client, IActionProcesser actionProcesser) {
        this.client = client;
        this.actionProcesser = actionProcesser;
        try {
            inputReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputWriter = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run()
    {
        while(true)
        {
            String message = null;
            try {
                message = inputReader.readLine();
            } catch (IOException e) {
                return;
            }

            System.out.println("received:"+message);
            if(message == null) {
                try {
                    closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
            String response = actionProcesser.ProcessAction(message);
            if(response == null)
            {
                try {
                    closeConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            outputWriter.println(response);
        }
    }

    public void closeConnection() throws IOException {

        outputWriter.close();
        inputReader.close();
        client.close();
        System.out.println("connection closed");
    }
}
