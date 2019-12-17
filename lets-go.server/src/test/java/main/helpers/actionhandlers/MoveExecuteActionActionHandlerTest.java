package main.helpers.actionhandlers;

import contract.Change;
import contract.Coordinates;
import contract.ResponseDTO;
import contract.ResponsePrisoners;
import contract.enums.ResponseType;
import core.interfaces.ICommandDirector;
import core.model.Move;
import core.model.MoveExecution;
import core.model.MoveIdentity;
import core.model.MoveResponse;
import core.model.enums.Color;
import core.model.enums.MoveResponseType;
import main.ClientConnectionThread;
import main.IClientsManager;
import main.helpers.jsonparser.IJsonParser;
import main.helpers.playervalidator.IPlayerValidator;
import main.model.GameInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class MoveExecuteActionActionHandlerTest {

    private MoveExecuteActionHandler moveExecuteActionHandler;

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
    public void handleAction_sendsError_whenNullGameInfo() {

        GameInfo gameInfo = null;
        Coordinates coordinates = new Coordinates(1,2);

        moveExecuteActionHandler = new MoveExecuteActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, clientsManagerMock, commandDirectorMock , coordinates);

        moveExecuteActionHandler.handleAction();

        verify(clientConnectionThreadMock, times(1)).beginAction(null);
        verify(jsonParserMock, times(1)).parseResponseToJson(any(ResponseDTO.class));
    }

    @Test
    public void handleAction_passesInvalidMove_withNotNullGameInfoAndInvalidMove() {

        MoveIdentity moveIdentity = new MoveIdentity(Color.BLACK, 0);
        GameInfo gameInfo = new GameInfo(moveIdentity, 42);
        Coordinates coordinates = new Coordinates(1,2);
        MoveResponse moveResponse = new MoveResponse(MoveResponseType.INVALID_MOVE);

        when(commandDirectorMock.tryToMove(any(Move.class))).thenReturn(moveResponse);

        moveExecuteActionHandler = new MoveExecuteActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, clientsManagerMock, commandDirectorMock , coordinates);

        moveExecuteActionHandler.handleAction();

        verify(commandDirectorMock, times(1)).tryToMove(any(Move.class));
        verify(clientsManagerMock, times(1)).getClientWithId(42);
        verify(clientConnectionThreadMock, times(1)).beginAction(null);
        verify(jsonParserMock, times(1)).parseResponseToJson(any(ResponseDTO.class));
    }

    @Test
    public void handleAction_passesInvalidMove_withNotNullGameInfoAndGameGoesOn() {

        ResponsePrisoners responsePrisoners =  Mockito.mock(ResponsePrisoners.class);
        MoveExecution moveExecution = new MoveExecution(new ArrayList<>(),responsePrisoners);
        MoveIdentity moveIdentity = new MoveIdentity(Color.BLACK, 0);
        GameInfo gameInfo = new GameInfo(moveIdentity, 42);
        Coordinates coordinates = new Coordinates(1,2);
        MoveResponse moveResponse = new MoveResponse(MoveResponseType.GAME_GOES_ON, moveExecution);

        ClientConnectionThread waitingClientConnectionThreadMock = Mockito.mock(ClientConnectionThread.class);
        when(commandDirectorMock.tryToMove(any(Move.class))).thenReturn(moveResponse);
        when(clientsManagerMock.getClientWithId(42)).thenReturn(waitingClientConnectionThreadMock);

        moveExecuteActionHandler = new MoveExecuteActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, clientsManagerMock, commandDirectorMock , coordinates);

        moveExecuteActionHandler.handleAction();

        verify(commandDirectorMock, times(1)).tryToMove(any(Move.class));
        verify(clientsManagerMock, times(1)).getClientWithId(42);
        verify(clientConnectionThreadMock, times(1)).beginAction(null);
        verify(jsonParserMock, times(2)).parseResponseToJson(any(ResponseDTO.class));
        verify(responsePrisoners, times(1)).swapPrisoners();
        verify(waitingClientConnectionThreadMock, times(1)).completeAction(null);
    }

    @Test
    public void handleAction_informsClients_withNotNullGameInfoAndCurrentPlayerWon() {

        MoveIdentity moveIdentity = new MoveIdentity(Color.BLACK, 0);
        GameInfo gameInfo = new GameInfo(moveIdentity, 42);
        Coordinates coordinates = new Coordinates(1,2);
        MoveResponse moveResponse = new MoveResponse(MoveResponseType.CURRENT_PLAYER_WON);

        ClientConnectionThread waitingClientConnectionThreadMock = Mockito.mock(ClientConnectionThread.class);
        when(commandDirectorMock.tryToMove(any(Move.class))).thenReturn(moveResponse);
        when(clientsManagerMock.getClientWithId(42)).thenReturn(waitingClientConnectionThreadMock);

        moveExecuteActionHandler = new MoveExecuteActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, clientsManagerMock, commandDirectorMock , coordinates);

        moveExecuteActionHandler.handleAction();

        verify(commandDirectorMock, times(1)).tryToMove(any(Move.class));
        verify(clientsManagerMock, times(1)).getClientWithId(42);
        verify(clientConnectionThreadMock, times(1)).completeAction(null);
        verify(jsonParserMock, times(2)).parseResponseToJson(any(ResponseDTO.class));
        verify(waitingClientConnectionThreadMock, times(1)).completeAction(null);
    }

    @Test
    public void handleAction_informsClients_withNotNullGameInfoAndGameTied() {

        MoveIdentity moveIdentity = new MoveIdentity(Color.BLACK, 0);
        GameInfo gameInfo = new GameInfo(moveIdentity, 42);
        Coordinates coordinates = new Coordinates(1,2);
        MoveResponse moveResponse = new MoveResponse(MoveResponseType.TIE);

        ClientConnectionThread waitingClientConnectionThreadMock = Mockito.mock(ClientConnectionThread.class);
        when(commandDirectorMock.tryToMove(any(Move.class))).thenReturn(moveResponse);
        when(clientsManagerMock.getClientWithId(42)).thenReturn(waitingClientConnectionThreadMock);

        moveExecuteActionHandler = new MoveExecuteActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, clientsManagerMock, commandDirectorMock , coordinates);

        moveExecuteActionHandler.handleAction();

        verify(commandDirectorMock, times(1)).tryToMove(any(Move.class));
        verify(clientsManagerMock, times(1)).getClientWithId(42);
        verify(clientConnectionThreadMock, times(1)).completeAction(null);
        verify(jsonParserMock, times(2)).parseResponseToJson(any(ResponseDTO.class));
        verify(waitingClientConnectionThreadMock, times(1)).completeAction(null);
    }

    @Test
    public void handleAction_informsClients_withNotNullGameInfoAndOtherPlayerWon() {

        MoveIdentity moveIdentity = new MoveIdentity(Color.BLACK, 0);
        GameInfo gameInfo = new GameInfo(moveIdentity, 42);
        Coordinates coordinates = new Coordinates(1,2);
        MoveResponse moveResponse = new MoveResponse(MoveResponseType.OTHER_PLAYER_WON);

        ClientConnectionThread waitingClientConnectionThreadMock = Mockito.mock(ClientConnectionThread.class);
        when(commandDirectorMock.tryToMove(any(Move.class))).thenReturn(moveResponse);
        when(clientsManagerMock.getClientWithId(42)).thenReturn(waitingClientConnectionThreadMock);

        moveExecuteActionHandler = new MoveExecuteActionHandler(gameInfo, clientConnectionThreadMock,
                jsonParserMock, clientsManagerMock, commandDirectorMock , coordinates);

        moveExecuteActionHandler.handleAction();

        verify(commandDirectorMock, times(1)).tryToMove(any(Move.class));
        verify(clientsManagerMock, times(1)).getClientWithId(42);
        verify(clientConnectionThreadMock, times(1)).completeAction(null);
        verify(jsonParserMock, times(2)).parseResponseToJson(any(ResponseDTO.class));
        verify(waitingClientConnectionThreadMock, times(1)).completeAction(null);
    }
}
