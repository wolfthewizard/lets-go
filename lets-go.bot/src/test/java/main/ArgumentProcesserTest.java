package main;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import contract.Change;
import contract.Coordinates;
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



public class ArgumentProcesserTest {

    IGameInitializer gameInitializer;
    ArgumentsProcesser argumentsProcesser;

    @BeforeEach
    public void setUp(){
        gameInitializer = Mockito.mock(IGameInitializer.class);

        argumentsProcesser = new ArgumentsProcesser(gameInitializer);
    }

    @Test
    public void processArgument_BeginSmallGame(){

        argumentsProcesser.processArgument("-small");

        verify(gameInitializer, times(1)).startSmallBoardGame();
    }

    @Test
    public void processArgument_BeginMediumGame(){

        argumentsProcesser.processArgument("-medium");

        verify(gameInitializer, times(1)).startMediumBoardGame();
    }

    @Test
    public void processArgument_BeginLargeGame(){

        argumentsProcesser.processArgument("-large");

        verify(gameInitializer, times(1)).startLargeBoardGame();
    }

    @Test
    public void processArgument_DoesNotBeginGame_ForOtherArg(){

        argumentsProcesser.processArgument("argg");

        verify(gameInitializer, times(0)).startSmallBoardGame();
        verify(gameInitializer, times(0)).startMediumBoardGame();
        verify(gameInitializer, times(0)).startLargeBoardGame();
    }

    @Test
    public void processArgument_DoesNotStartGame_FromHelpArg(){

        argumentsProcesser.processArgument("-help");

        verify(gameInitializer, times(0)).startSmallBoardGame();
        verify(gameInitializer, times(0)).startMediumBoardGame();
        verify(gameInitializer, times(0)).startLargeBoardGame();
    }
}
