package main;

import main.helpers.actionprocesser.IActionProcesser;

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

    @Override
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
                System.out.println("there shouldnt be this null");
                closeConnection();
                return;
            }
            actionProcesser.processAction(message, id);
        }
    }

    public void beginAction(String firstResponse) {
        outputWriter.println(firstResponse);
    }

    public void completeAction(String secondResponse) {
        outputWriter.println(secondResponse);
    }

    public int getThreadId() {
        return id;
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
        interrupt();
    }
}
