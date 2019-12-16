package main;



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

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class ExitListenerTest {

    IExitListener exitListener;
    ICommunicatorSender communicatorSender;

    @BeforeEach
    public void setUp(){
        communicatorSender = Mockito.mock(ICommunicatorSender.class);

        String input = "exit\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        exitListener = new ExitListener(communicatorSender, new Scanner(input));
    }

    @Test
    public void waitForExit_ClosesServerOnExit(){

        exitListener.waitForExit();

        verify(communicatorSender, times(1)).sendLeaveGameMessage();
    }
}
