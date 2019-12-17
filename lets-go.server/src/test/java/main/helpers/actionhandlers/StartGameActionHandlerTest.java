package main.helpers.actionhandlers;

import contract.ResponseDTO;
import contract.enums.BoardSize;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class StartGameActionHandlerTest {

    private StartGameActionHandler startGameActionHandler;

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
    public void handleAction_beginsGame_withNullGameInfoAndWaitingPlayers() {

        GameInfo gameInfo = null;

        ClientConnectionThread waitingClientConnectionThreadMock = Mockito.mock(ClientConnectionThread.class);
        when(clientsManagerMock.getClientWithId(10)).thenReturn(waitingClientConnectionThreadMock);
        when(playerValidatorMock.getWaitingPlayerId(BoardSize.NINE)).thenReturn(10);
        when(commandDirectorMock.createNewGame(BoardSize.NINE)).thenReturn(3);

        startGameActionHandler = new StartGameActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, commandDirectorMock, 14, BoardSize.NINE, playerValidatorMock, clientsManagerMock);

        startGameActionHandler.handleAction();

        verify(commandDirectorMock, times(1)).createNewGame(BoardSize.NINE);
        verify(playerValidatorMock, times(1)).getWaitingPlayerId(BoardSize.NINE);
        verify(clientsManagerMock, times(1)).getClientWithId(10);
        verify(clientConnectionThreadMock, times(1)).beginAction(anyString());
        verify(waitingClientConnectionThreadMock, times(1)).beginAction(anyString());
        verify(jsonParserMock, times(2)).parseResponseToJson(any(ResponseDTO.class));
        verify(playerValidatorMock, times(1)).addNewGame(anyInt(), anyInt(), eq(3));
    }

    @Test
    public void handleAction_addsPlayer_withNullGameInfoAndNoWaitingPlayer() {

        GameInfo gameInfo = null;

        when(playerValidatorMock.getWaitingPlayerId(BoardSize.NINE)).thenReturn(null);

        startGameActionHandler = new StartGameActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, commandDirectorMock, 14, BoardSize.NINE, playerValidatorMock, clientsManagerMock);

        startGameActionHandler.handleAction();

        verify(playerValidatorMock, times(1)).getWaitingPlayerId(BoardSize.NINE);
        verify(playerValidatorMock, times(1)).addWaitingPlayer(BoardSize.NINE, 14);
    }

    @Test
    public void handleAction_withNotNullGameInfo() {

        GameInfo gameInfo = new GameInfo(null, 42);

        startGameActionHandler = new StartGameActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, commandDirectorMock, 14, BoardSize.NINE, playerValidatorMock, clientsManagerMock);

        startGameActionHandler.handleAction();

        verify(clientConnectionThreadMock, times(1)).beginAction(anyString());
        verify(jsonParserMock, times(1)).parseResponseToJson(any(ResponseDTO.class));
    }
}
