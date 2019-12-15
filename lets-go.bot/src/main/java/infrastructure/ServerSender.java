package infrastructure;

import java.io.PrintWriter;

public class ServerSender {

    private static PrintWriter outputWriter;


    public ServerSender(PrintWriter outputWriter) {
        this.outputWriter = outputWriter;

    }

    public void sendAction(String action){
        outputWriter.println(action);
    }

    public void closeConnection(){
        outputWriter.close();
    }
}
