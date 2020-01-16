package core.serversender;

import contract.ResponseDTO;
import contract.enums.ResponseType;
import core.interfaces.IGameManager;
import core.interfaces.IJsonParser;
import core.interfaces.IResponseNumberCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameServerResponseListenerTest {

    private GameServerResponseListener gameServerResponseListener;

    private IGameManager frontendManagerMock;
    private IJsonParser jsonParserMock;
    private IResponseNumberCounter responseNumberCounterMock;

    @BeforeEach
    void setup() {

        frontendManagerMock = Mockito.mock(IGameManager.class);

        jsonParserMock = Mockito.mock(IJsonParser.class);

        responseNumberCounterMock = Mockito.mock(IResponseNumberCounter.class);

        gameServerResponseListener = new GameServerResponseListener(frontendManagerMock, jsonParserMock);
        gameServerResponseListener.setResponseNumberCounter(responseNumberCounterMock);
    }

    @Test
    public void responseReceived_doesNotThrow() {

        when(jsonParserMock.parseJsonToResponse("")).thenReturn(new ResponseDTO(ResponseType.TIE));
         assertDoesNotThrow(() -> gameServerResponseListener.responseReceived(""));
    }

    @Test
    public void passResponseDTO_callsMoveExecuted() {

        ResponseDTO responseDTO = new ResponseDTO(null, null);

        gameServerResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).moveExecuted(any(), any());
    }

    @Test
    public void passResponseDTO_callsInvalidMove() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.INVALID_MOVE);

        gameServerResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).invalidMove();
    }

    @Test
    public void passResponseDTO_callsCantCreateGame() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.CANT_CREATE_GAME);

        gameServerResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).cantCreateGame();
    }

    @Test
    public void passResponseDTO_callsSuccess() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.SUCCESS);

        gameServerResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).success();
    }

    @Test
    public void passResponseDTO_callsServerError() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.SERVER_ERROR);

        gameServerResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).serverError();
    }

    @Test
    public void passResponseDTO_callsGameWon() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.GAMEWON);

        gameServerResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).gameWon(anyInt());
    }

    @Test
    public void passResponseDTO_callsGameLost() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.GAMELOST);

        gameServerResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).gameLost(anyInt());
    }

    @Test
    public void passResponseDTO_callsTie() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.TIE);

        gameServerResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).tie(anyInt());
    }

    @Test
    public void passResponseDTO_callsPlayerLeft() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.PLAYER_LEFT);

        gameServerResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).playerLeft();
    }
}
