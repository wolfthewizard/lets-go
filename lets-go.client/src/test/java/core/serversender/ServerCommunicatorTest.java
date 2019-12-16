package core.serversender;

import core.interfaces.IJsonParser;
import core.interfaces.IServerResponseListener;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class ServerCommunicatorTest {

    private ServerCommunicator serverCommunicator;

    private IJsonParser jsonParserMock;
    private IServerResponseListener serverResponseListenerMock;

    @BeforeEach
    void setup() {

        jsonParserMock = Mockito.mock(IJsonParser.class);

        serverResponseListenerMock = Mockito.mock(IServerResponseListener.class);

        serverCommunicator = new ServerCommunicator(jsonParserMock, serverResponseListenerMock);
    }
}
