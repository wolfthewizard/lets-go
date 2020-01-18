package core.helpers;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import contract.enums.ResponseType;
import core.interfaces.ICommunicatorSender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EndOfGameHandlerTest {


    EndOfGameHandler endOfGameHandler;
    ICommunicatorSender communicatorSender;

    @BeforeEach
    public void setUp() {

        endOfGameHandler= new EndOfGameHandler(null);
    }

    @Test
    public void handleGameEnd_CallsSendLeaveMessage_ThrowsNullExceptionForNullSender() {

        assertThrows(NullPointerException.class,() -> endOfGameHandler.handleGameEnd(ResponseType.TIE,1));
        assertThrows(NullPointerException.class,() -> endOfGameHandler.handleGameEnd(ResponseType.PLAYER_LEFT,1));
        assertThrows(NullPointerException.class,() -> endOfGameHandler.handleGameEnd(ResponseType.GAMELOST,1));
        assertThrows(NullPointerException.class,() -> endOfGameHandler.handleGameEnd(ResponseType.CANT_CREATE_GAME,1));
        assertThrows(NullPointerException.class,() -> endOfGameHandler.handleGameEnd(ResponseType.GAMEWON,1));
        assertThrows(NullPointerException.class,() -> endOfGameHandler.handleGameEnd(ResponseType.SERVER_ERROR,1));
    }
}
