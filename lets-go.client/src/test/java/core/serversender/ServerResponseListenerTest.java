package core.serversender;

import contract.ResponseDTO;
import contract.enums.ResponseType;
import core.interfaces.IFrontendManager;
import core.interfaces.IJsonParser;
import core.interfaces.IResponseNumberCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServerResponseListenerTest {

    private ServerResponseListener serverResponseListener;

    private IFrontendManager frontendManagerMock;
    private IJsonParser jsonParserMock;
    private IResponseNumberCounter responseNumberCounterMock;

    @BeforeEach
    void setup() {

        frontendManagerMock = Mockito.mock(IFrontendManager.class);

        jsonParserMock = Mockito.mock(IJsonParser.class);

        responseNumberCounterMock = Mockito.mock(IResponseNumberCounter.class);

        serverResponseListener = new ServerResponseListener(frontendManagerMock, jsonParserMock);
        serverResponseListener.setResponseNumberCounter(responseNumberCounterMock);
    }

    @Test
    public void responseReceived_doesNotThrow() {

        when(jsonParserMock.parseJsonToResponse("")).thenReturn(new ResponseDTO(ResponseType.TIE));
         assertDoesNotThrow(() -> serverResponseListener.responseReceived(""));
    }

    @Test
    public void passResponseDTO_callsMoveExecuted() {

        ResponseDTO responseDTO = new ResponseDTO(null, null);

        serverResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).moveExecuted(any(), any());
    }

    @Test
    public void passResponseDTO_callsInvalidMove() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.INVALID_MOVE);

        serverResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).invalidMove();
    }

    @Test
    public void passResponseDTO_callsCantCreateGame() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.CANT_CREATE_GAME);

        serverResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).cantCreateGame();
    }

    @Test
    public void passResponseDTO_callsSuccess() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.SUCCESS);

        serverResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).success();
    }

    @Test
    public void passResponseDTO_callsServerError() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.SERVER_ERROR);

        serverResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).serverError();
    }

    @Test
    public void passResponseDTO_callsGameWon() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.GAMEWON);

        serverResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).gameWon();
    }

    @Test
    public void passResponseDTO_callsGameLost() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.GAMELOST);

        serverResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).gameLost();
    }

    @Test
    public void passResponseDTO_callsTie() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.TIE);

        serverResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).tie();
    }

    @Test
    public void passResponseDTO_callsPlayerLeft() {

        ResponseDTO responseDTO = new ResponseDTO(ResponseType.PLAYER_LEFT);

        serverResponseListener.passResponseDTO(responseDTO);

        verify(frontendManagerMock, times(1)).playerLeft();
    }
}
