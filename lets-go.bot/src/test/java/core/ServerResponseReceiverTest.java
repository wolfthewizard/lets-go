package core;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import contract.Change;
import contract.Coordinates;
import contract.ResponseDTO;
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


public class ServerResponseReceiverTest {

    IMovesParser movesParser;
    IEndOfGameHandler endOfGameHandler;
    ITurnExecutor turnExecutor;
    ServerResponseReceiver serverResponseReceiver;

    @BeforeEach
    public void setUp(){

        movesParser = Mockito.mock(IMovesParser.class);
        endOfGameHandler = Mockito.mock(IEndOfGameHandler.class);
        turnExecutor = Mockito.mock(ITurnExecutor.class);

        serverResponseReceiver = new ServerResponseReceiver(movesParser, endOfGameHandler, turnExecutor);
    }

    @Test
    public void responseReceived_SendEndGame_ForEndOfGame() {

        serverResponseReceiver.responseReceived(new ResponseDTO(ResponseType.CANT_CREATE_GAME,1));
        verify(endOfGameHandler, times(1)).handleGameEnd(ResponseType.CANT_CREATE_GAME,1);

        serverResponseReceiver.responseReceived(new ResponseDTO(ResponseType.SERVER_ERROR,1));
        verify(endOfGameHandler, times(1)).handleGameEnd(ResponseType.SERVER_ERROR,1);

        serverResponseReceiver.responseReceived(new ResponseDTO(ResponseType.GAMELOST,1));
        verify(endOfGameHandler, times(1)).handleGameEnd(ResponseType.GAMELOST,1);

        serverResponseReceiver.responseReceived(new ResponseDTO(ResponseType.GAMEWON,1));
        verify(endOfGameHandler, times(1)).handleGameEnd(ResponseType.GAMEWON,1);

        serverResponseReceiver.responseReceived(new ResponseDTO(ResponseType.PLAYER_LEFT,1));
        verify(endOfGameHandler, times(1)).handleGameEnd(ResponseType.PLAYER_LEFT,1);

        serverResponseReceiver.responseReceived(new ResponseDTO(ResponseType.TIE,1));
        verify(endOfGameHandler, times(1)).handleGameEnd(ResponseType.TIE,1);
    }

    @Test
    public void responseReceived_SendExecuteTurn_ForInvalidMove() {

        serverResponseReceiver.responseReceived(new ResponseDTO(ResponseType.INVALID_MOVE));
        verify(turnExecutor, times(1)).executeTurn(true);

    }

    @Test
    public void responseReceived_SendParseMoves_ForMoveExecuted() {

        serverResponseReceiver.responseReceived(new ResponseDTO(ResponseType.MOVE_EXECUTED));
        verify(movesParser, times(1)).parseMoves(null);

    }
}
