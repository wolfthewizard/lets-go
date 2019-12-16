package core;

import contract.enums.BoardSize;
import core.helpers.BoardManager;
import core.helpers.MovePerformer;
import core.interfaces.IBoardManager;
import core.interfaces.ICommunicatorSender;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class GameInitializerTest {

    GameInitializer gameInitializer;
    ICommunicatorSender communicatorSender;
    IBoardManager boardManager;

    @BeforeEach
    public void setUp() {

         communicatorSender = Mockito.mock(ICommunicatorSender.class);
         boardManager = Mockito.mock(IBoardManager.class);

        gameInitializer = new GameInitializer(communicatorSender, boardManager);
    }

    @Test
    public void startSmallBoardGame_StartsCorrectGame() {

        gameInitializer.startSmallBoardGame();

        verify(communicatorSender, times(1)).sendStartGameMessage(BoardSize.NINE);
        verify(communicatorSender, times(0)).sendStartGameMessage(BoardSize.THIRTEEN);
    }

    @Test
    public void startMediumBoardGame_StartsCorrectGame() {

        gameInitializer.startMediumBoardGame();

        verify(communicatorSender, times(1)).sendStartGameMessage(BoardSize.THIRTEEN);
        verify(communicatorSender, times(0)).sendStartGameMessage(BoardSize.NINETEEN);
    }

    @Test
    public void startLargeBoardGame_StartsCorrectGame() {

        gameInitializer.startLargeBoardGame();

        verify(communicatorSender, times(1)).sendStartGameMessage(BoardSize.NINETEEN);
        verify(communicatorSender, times(0)).sendStartGameMessage(BoardSize.NINE);
    }
}
