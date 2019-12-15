package core;

import core.interfaces.IGameManagerService;
import core.interfaces.IMoveExecutorService;
import core.model.MoveIdentity;
import core.model.MoveResponse;
import core.model.enums.Color;
import core.model.enums.MoveResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommandDirectorTest {

    private CommandDirector commandDirector;

    private IGameManagerService gameManagerService;
    private IMoveExecutorService moveExecutorService;

    @BeforeEach
    void setup() {

        gameManagerService = Mockito.mock(IGameManagerService.class);
        when(gameManagerService.createNewGame(any())).thenReturn(0);

        moveExecutorService = Mockito.mock(IMoveExecutorService.class);
        when(moveExecutorService.executeMove(any())).thenReturn(new MoveResponse(MoveResponseType.GAME_GOES_ON));

        commandDirector = new CommandDirector(gameManagerService, moveExecutorService);
    }

    @Test
    public void createNewGame_returnId() {

        int id = commandDirector.createNewGame(null);

        assertEquals(0, id);
    }

    @Test
    public void tryToMove_returnsMoveResponse() {

        assertEquals(MoveResponseType.GAME_GOES_ON, moveExecutorService.executeMove(null).getMoveResponseType());
    }

    @Test
    public void cancelGame() {

        commandDirector.cancelGame(new MoveIdentity(Color.BLACK, 0));

        verify(gameManagerService, times(1)).cancelGame(0);
    }
}
