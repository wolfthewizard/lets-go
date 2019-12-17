package infrastrusture;


import contract.ActionDTO;
import contract.Coordinates;
import contract.enums.BoardSize;
import core.interfaces.IJsonParser;
import core.interfaces.IResponseNumberCounter;
import core.interfaces.IServerConnector;
import core.interfaces.IServerResponseListener;
import core.serversender.ServerResponseListener;
import infrastructure.ServerConnector;
import infrastructure.ServerResponseRedirector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import contract.enums.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import contract.enums.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ServerConnectorTest {

    ServerConnector serverConnector;

    @BeforeEach
    public void setUp(){
        serverConnector = new ServerConnector();
    }

    @Test
    public void resetConnection_throwsExceptionWithoutServer() throws IOException {

        assertThrows(ConnectException.class, () -> serverConnector.resetConnection());
    }

    @Test
    public void setServerResponseListener_throwsNullPointerForNoConnection() {
        assertThrows(NullPointerException.class, () -> serverConnector.setServerResponseListener(null));
    }

    @Test
    public void sendMessage_throwsNullPointerForNoConnection(){
        assertThrows(NullPointerException.class, () -> serverConnector.sendMessage("mess"));
    }

    @Test
    public void shutDown_throwsNullPointerForNoConnection(){
        assertThrows(NullPointerException.class, () -> serverConnector.shutDown());
    }
}
