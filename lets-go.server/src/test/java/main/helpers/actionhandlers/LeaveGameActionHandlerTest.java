package main.helpers.actionhandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    private GameInfo gameInfo;
    private ClientConnectionThread clientConnectionThreadMock;
    private IJsonParser jsonParserMock;
    private ICommandDirector commandDirectorMock;

    @BeforeEach
    void setup() {

        clientsManagerMock = Mockito.mock(IClientsManager.class);

        playerValidatorMock = Mockito.mock(IPlayerValidator.class);

        gameInfo = null;

        clientConnectionThreadMock = Mockito.mock(ClientConnectionThread.class);

        jsonParserMock = Mockito.mock(IJsonParser.class);

        commandDirectorMock = Mockito.mock(ICommandDirector.class);

    }

    @Test
    public void handleAction_withNullGameInfo() {

        leaveGameActionHandler = new LeaveGameActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, commandDirectorMock, clientsManagerMock, playerValidatorMock);

        leaveGameActionHandler.handleAction();

        verify(playerValidatorMock, times(1)).playerLeft(anyInt());
    }

//    @Test
//    public void handleAction_withNotNullGameInfo() {
//
//        gameInfo = new GameInfo(new MoveIdentity(Color.BLACK, 0), 42);
//
//        leaveGameActionHandler = new LeaveGameActionHandler(gameInfo, clientConnectionThreadMock,
//                jsonParserMock, commandDirectorMock, clientsManagerMock, playerValidatorMock);
//
//        leaveGameActionHandler.handleAction();
//
//        verify(playerValidatorMock, times(1)).removeGame(anyInt());
//    }
}
