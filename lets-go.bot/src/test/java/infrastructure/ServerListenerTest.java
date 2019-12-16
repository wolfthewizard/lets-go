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
import java.util.ArrayList;


public class ServerListenerTest {

    BufferedReader inputReader;
    ServerListener serverListener;

    @BeforeEach
    public void setUp() {

        inputReader = Mockito.mock(BufferedReader.class);

        serverListener = new ServerListener(inputReader);
    }

    @Test
    public void setCommunicatorListener_DoesNotThrow() {
        assertDoesNotThrow(() -> serverListener.setCommunicatorListener(null));
    }

    @Test
    public void start_StartsThreadThatCallInputReader() throws IOException, InterruptedException {

        serverListener.start();
        Thread.sleep(20);
        verify(inputReader, atLeastOnce()).readLine();
    }

    @Test
    public void start_StartsThreadAndFetchesResponse() throws IOException, InterruptedException {
        ICommunicatorListener communicatorListener = Mockito.mock(ICommunicatorListener.class);
        serverListener.setCommunicatorListener(communicatorListener);
        when(inputReader.readLine()).thenReturn("notnull");

        serverListener.start();
        Thread.sleep(20);

        verify(inputReader, atLeastOnce()).readLine();
        verify(communicatorListener, atLeastOnce()).responseFetched("notnull");
    }

    @Test
    public void stopThread_ClosesInputReader() throws IOException {

        serverListener.stopThread();
        verify(inputReader, times(1)).close();
    }
}
