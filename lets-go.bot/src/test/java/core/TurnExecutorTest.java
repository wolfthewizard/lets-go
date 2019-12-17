package core;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import contract.Change;
import contract.Coordinates;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import contract.enums.ResponseType;
import core.interfaces.IBoardManager;
import core.interfaces.ICommunicatorSender;
import core.interfaces.IMovePerformer;
import core.interfaces.ITurnExecutor;
import core.model.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.verification.Timeout;

import java.util.ArrayList;

public class TurnExecutorTest {

    TurnExecutor turnExecutor;
    ICommunicatorSender communicatorSender;
    IMovePerformer movePerformer;
    IBoardManager boardManager;

    @BeforeEach
    public void setUp(){

        communicatorSender = Mockito.mock(ICommunicatorSender.class);
        movePerformer = Mockito.mock(IMovePerformer.class);
        boardManager = Mockito.mock(IBoardManager.class);


        turnExecutor = new TurnExecutor(communicatorSender, movePerformer, boardManager);
    }


    @Test
    public void setMyColor_DoesNotThrow() {

        assertDoesNotThrow(() -> turnExecutor.setMyColor(Color.WHITE));
        assertDoesNotThrow(() -> turnExecutor.setMyColor(Color.BLACK));
    }

    @Test
    public void executeTurn_CallsRightMethodsWithRightParameters() {

        when(movePerformer.performMove(any(), any(BoardSize.class), any(Color.class), anyBoolean())).thenReturn(new Coordinates(2,1));
        turnExecutor.executeTurn(false);

        verify(boardManager, times(1)).getBoard();
        verify(boardManager, times(1)).getBoardSize();
        verify(movePerformer, times(1)).performMove(eq(null), eq(null), eq(null), eq(false));
        verify(communicatorSender, times(1)).sendMoveMessage(any(Coordinates.class));
    }
}
