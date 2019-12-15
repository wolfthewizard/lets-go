package infrastructure;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;
import contract.enums.ResponseType;
import core.interfaces.*;
import core.model.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.verification.Timeout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ServerSenderTest {

    PrintWriter outputWriter;
    ServerSender serverSender;

    @BeforeEach
    public void setUp(){
        outputWriter = Mockito.mock(PrintWriter.class);

        serverSender = new ServerSender(outputWriter);
    }

    @Test
    public void sendAction_SendActionToOutputWriter(){

        serverSender.sendAction("act");

        verify(outputWriter, times(1)).println("act");
    }

    @Test
    public void closeConnection_ClosesOutputWriter(){
        serverSender.closeConnection();

        verify(outputWriter, times(1)).close();
    }
}
