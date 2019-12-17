package core.serversender;

import contract.ActionDTO;
import contract.Coordinates;
import contract.enums.BoardSize;
import core.interfaces.IJsonParser;
import core.interfaces.IResponseNumberCounter;
import core.interfaces.IServerConnector;
import core.interfaces.IServerResponseListener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class ServerCommunicatorTest {

    private ServerCommunicator serverCommunicator;

    private static IJsonParser jsonParser;
    private static IServerResponseListener serverResponseListener;
    private static IResponseNumberCounter responseNumberCounter;
    private static IServerConnector serverConnector;

    @BeforeAll
    static void init(){
        jsonParser = Mockito.mock(IJsonParser.class);

        serverConnector = Mockito.mock(IServerConnector.class);
        responseNumberCounter = Mockito.mock(IResponseNumberCounter.class);
        serverResponseListener = Mockito.mock(IServerResponseListener.class);

        ServerCommunicator.initialize(jsonParser, responseNumberCounter, serverConnector);
    }

    @BeforeEach
    public void setUp() {
        serverCommunicator = ServerCommunicator.getInstance();
        serverCommunicator.setServerResponseListener(serverResponseListener);
    }

    @Test
    public void sendStartGameMessage_SendRightAction() {

        when(jsonParser.parseActionToJson(any(ActionDTO.class))).thenReturn("Bo");
        when(responseNumberCounter.getNumberOfResponsesToRead()).thenReturn(0);

        serverCommunicator.sendStartGameMessage(BoardSize.NINE);

        verify(responseNumberCounter, atLeastOnce()).addNumberOfResponsesToRead(2);
        verify(jsonParser, atLeastOnce()).parseActionToJson(any(ActionDTO.class));
    }

    @Test
    public void sendMoveMessage_SendRightAction() {

        when(jsonParser.parseActionToJson(any(ActionDTO.class))).thenReturn("Mo");
        when(responseNumberCounter.getNumberOfResponsesToRead()).thenReturn(0);

        serverCommunicator.sendMoveMessage(new Coordinates(1,2));

        verify(responseNumberCounter, atLeastOnce()).addNumberOfResponsesToRead(2);
        verify(jsonParser, atLeastOnce()).parseActionToJson(any(ActionDTO.class));
    }

    @Test
    public void sendMovePassMessage_SendRightAction() {

        when(jsonParser.parseActionToJson(any(ActionDTO.class))).thenReturn("Pa");
        when(responseNumberCounter.getNumberOfResponsesToRead()).thenReturn(0);

        serverCommunicator.sendMovePassMessage();

        verify(responseNumberCounter, atLeastOnce()).addNumberOfResponsesToRead(2);
        verify(jsonParser, atLeastOnce()).parseActionToJson(any(ActionDTO.class));
    }

    @Test
    public void sendLeaveGameMessage_SendRightAction() {

        when(jsonParser.parseActionToJson(any(ActionDTO.class))).thenReturn("Le");

        serverCommunicator.sendLeaveGameMessage();

        verify(jsonParser, atLeastOnce()).parseActionToJson(any(ActionDTO.class));
    }
}
