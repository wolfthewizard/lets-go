package infrastructure;

import contract.ActionDTO;
import contract.Coordinates;
import contract.enums.ActionType;
import contract.enums.BoardSize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerSender {

    private static PrintWriter outputWriter;


    public ServerSender(PrintWriter outputWriter) {
        this.outputWriter = outputWriter;

    }

    public void sendAction(String action){
        outputWriter.println(action);
    }
}
