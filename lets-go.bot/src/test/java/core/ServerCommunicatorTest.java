package core;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import contract.ActionDTO;
import contract.Change;
import contract.Coordinates;
import contract.enums.ActionType;
import contract.enums.BoardSize;
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

public class ServerCommunicatorTest {


    ServerCommunicator serverCommunicator;
    IServerConnector serverConnector;
    IJsonParser jsonParser;
    IServerResponseReceiver serverResponseReceiver;

    @BeforeEach
    public void setUp() {

        serverConnector = Mockito.mock(IServerConnector.class);
        jsonParser = Mockito.mock(IJsonParser.class);
        serverResponseReceiver = Mockito.mock(IServerResponseReceiver.class);

        serverCommunicator = new ServerCommunicator(serverConnector, jsonParser);
    }

    @Test
    public void setServerResponseReceiver_CallsStartListening() {

        serverCommunicator.setServerResponseReceiver(null);

        verify(serverConnector, times(1)).startListening(serverCommunicator);
    }

    @Test
    public void sendStartGameMessage_SendRightAction() {

        when(jsonParser.parseActionToJson(any(ActionDTO.class))).thenReturn("Bo");

        serverCommunicator.sendStartGameMessage(BoardSize.NINE);

        verify(jsonParser, times(1)).parseActionToJson(any(ActionDTO.class));
        verify(serverConnector, times(1)).sendAction("Bo");
    }

    @Test
    public void sendMoveMessage_SendRightAction() {

        when(jsonParser.parseActionToJson(any(ActionDTO.class))).thenReturn("Co");

        serverCommunicator.sendMoveMessage(new Coordinates(0, 0));

        verify(jsonParser, times(1)).parseActionToJson(any(ActionDTO.class));
        verify(serverConnector, times(1)).sendAction("Co");
    }

    @Test
    public void sendMovePassMessage_SendRightAction() {
        when(jsonParser.parseActionToJson(any(ActionDTO.class))).thenReturn("Pa");

        serverCommunicator.sendMovePassMessage();

        verify(jsonParser, times(1)).parseActionToJson(any(ActionDTO.class));
        verify(serverConnector, times(1)).sendAction("Pa");
    }

    @Test
    public void sendLeaveGameMessage_SendRightAction() {

        when(jsonParser.parseActionToJson(any(ActionDTO.class))).thenReturn("Le");

        serverCommunicator.sendLeaveGameMessage();

        verify(jsonParser, times(1)).parseActionToJson(any(ActionDTO.class));
        verify(serverConnector, times(1)).sendAction("Le");
        verify(serverConnector, times(1)).shutDown();
    }

    @Test
    public void responseFetched_FetchesResponseToServerResponseListener() {
        serverCommunicator.setServerResponseReceiver(serverResponseReceiver);

        serverCommunicator.responseFetched("response");
        verify(jsonParser, times(1)).parseJsonToResponse("response");
        verify(serverResponseReceiver, times(1)).responseReceived(null);
    }
}
