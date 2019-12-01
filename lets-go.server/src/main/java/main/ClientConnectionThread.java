package main;

import main.helpers.IActionProcesser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnectionThread extends Thread {

    private int id;
    private Socket client;
    private BufferedReader inputReader;
    private PrintWriter outputWriter;
    private IActionProcesser actionProcesser;

    public ClientConnectionThread(Socket client, IActionProcesser actionProcesser, int id) {

        this.id = id;
        this.client = client;
        this.actionProcesser = actionProcesser;
        try {
            inputReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputWriter = new PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        while(true)
        {
            String message;
            try {
                message = inputReader.readLine();
            } catch (IOException e) {
                return;
            }

            System.out.println("received:"+message);
            if(message == null) {
                closeConnection();
                return;
            }
            String response = actionProcesser.ProcessAction(message, id);
            if(response == null)
            {
                closeConnection();
            }
            outputWriter.println(response);
        }
    }

    public void closeConnection() {

        try {
            outputWriter.close();
            inputReader.close();
            client.close();
            System.out.println("connection closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
