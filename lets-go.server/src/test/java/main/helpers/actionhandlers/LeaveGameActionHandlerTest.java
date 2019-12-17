package main.helpers.actionhandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import contract.ResponseDTO;
import contract.enums.ResponseType;
import core.interfaces.ICommandDirector;
import core.model.MoveIdentity;
import core.model.enums.Color;
import main.ClientConnectionThread;
import main.IClientsManager;
import main.helpers.jsonparser.IJsonParser;
import main.helpers.playervalidator.IPlayerValidator;
import main.model.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LeaveGameActionHandlerTest {

    private LeaveGameActionHandler leaveGameActionHandler;

    private IClientsManager clientsManagerMock;
    private IPlayerValidator playerValidatorMock;

    private ClientConnectionThread clientConnectionThreadMock;
    private IJsonParser jsonParserMock;
    private ICommandDirector commandDirectorMock;

    @BeforeEach
    void setup() {

        clientsManagerMock = Mockito.mock(IClientsManager.class);

        playerValidatorMock = Mockito.mock(IPlayerValidator.class);

        clientConnectionThreadMock = Mockito.mock(ClientConnectionThread.class);

        jsonParserMock = Mockito.mock(IJsonParser.class);

        commandDirectorMock = Mockito.mock(ICommandDirector.class);

    }

    @Test
    public void handleAction_withNullGameInfo() {

        GameInfo gameInfo = null;

        leaveGameActionHandler = new LeaveGameActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, commandDirectorMock, clientsManagerMock, playerValidatorMock);

        leaveGameActionHandler.handleAction();

        verify(playerValidatorMock, times(1)).playerLeft(anyInt());
        verify(clientConnectionThreadMock, times(1)).closeConnection();
    }

    @Test
    public void handleAction_withNotNullGameInfo() {

        MoveIdentity moveIdentity = new MoveIdentity(Color.BLACK, 0);
        GameInfo gameInfo = new GameInfo(moveIdentity, 42);
        ClientConnectionThread clientConnectionThreadMock = Mockito.mock(ClientConnectionThread.class);
        when(clientsManagerMock.getClientWithId(42)).thenReturn(clientConnectionThreadMock);

        leaveGameActionHandler = new LeaveGameActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, commandDirectorMock, clientsManagerMock, playerValidatorMock);

        leaveGameActionHandler.handleAction();

        verify(clientsManagerMock, times(1)).getClientWithId(42);
        verify(clientConnectionThreadMock, times(1)).completeAction(anyString());
        verify(jsonParserMock, times(1)).parseResponseToJson(any(ResponseDTO.class));
        verify(playerValidatorMock, times(1)).removeGame(anyInt());
        verify(commandDirectorMock, times(1)).cancelGame(moveIdentity);
        verify(clientConnectionThreadMock, times(1)).closeConnection();
    }
}
