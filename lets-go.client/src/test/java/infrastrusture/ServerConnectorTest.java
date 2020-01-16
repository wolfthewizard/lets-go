package infrastrusture;


import infrastructure.ServerConnector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ConnectException;

import static org.mockito.Matchers.any;

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
