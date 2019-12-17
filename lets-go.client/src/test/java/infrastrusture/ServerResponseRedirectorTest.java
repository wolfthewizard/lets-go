package infrastrusture;

import contract.ActionDTO;
import contract.Coordinates;
import contract.enums.BoardSize;
import core.interfaces.IJsonParser;
import core.interfaces.IResponseNumberCounter;
import core.interfaces.IServerConnector;
import core.interfaces.IServerResponseListener;
import core.serversender.ServerResponseListener;
import infrastructure.ServerResponseRedirector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import contract.enums.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServerResponseRedirectorTest {

    ServerResponseRedirector serverResponseRedirector;
    BufferedReader bufferedReader;

    @BeforeEach
    public void setUp(){

        bufferedReader = Mockito.mock(BufferedReader.class);

        serverResponseRedirector = new ServerResponseRedirector(bufferedReader);
    }

    @Test
    public void setServerResponseListener_doesNotThrow() {

         assertDoesNotThrow(() -> serverResponseRedirector.setServerResponseListener(null));
    }

    @Test
    public void start_stopsThread_ForNullRead() throws IOException, InterruptedException {

        serverResponseRedirector.start();

        Thread.sleep(20);
        verify(bufferedReader, times(1)).readLine();
    }

    @Test
    public void stopThread_stopsThread() throws IOException, InterruptedException {
        when(bufferedReader.readLine()).thenReturn("a");

        serverResponseRedirector.start();

        Thread.sleep(10);

        serverResponseRedirector.stopThread();

        verify(bufferedReader, times(1)).close();
    }

}
