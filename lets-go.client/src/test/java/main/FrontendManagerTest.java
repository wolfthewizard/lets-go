package main;

import contract.Change;
import contract.ResponsePrisoners;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class FrontendManagerTest {

    private GameManager frontendManager;

    private Game game;

    @BeforeEach
    void setup() {

        game = Mockito.mock(Game.class);

        frontendManager = new GameManager(game);

    }

    @Test
    public void moveExecuted_callsFunctions() {

        List<Change> listOfChanges = new ArrayList<>();
        ResponsePrisoners responsePrisoners = new ResponsePrisoners(0 ,0);

        frontendManager.moveExecuted(listOfChanges, responsePrisoners);

        verify(game, times(1)).clearServerResponse();
        verify(game, times(1)).enforceChanges(listOfChanges);
        verify(game, times(1)).setPlayersCaptives(responsePrisoners.getYourPrisoners());
        verify(game, times(1)).setOpponentsCaptives(responsePrisoners.getEnemyPrisoners());
    }

    @Test
    public void invalidMove_callsFunctions() {

        frontendManager.invalidMove();

        verify(game, times(1)).signalInvalidMove();
    }

    @Test
    public void cantCreateGame_callsFunctions() {

        frontendManager.cantCreateGame();

        verify(game, times(1)).signalFailedToCreateGame();
        verify(game, times(1)).exitApp();
    }

    @Test
    public void success_callsFunctions() {

        frontendManager.success();

        verify(game, times(1)).clearServerResponse();
        verify(game, times(1)).signalOpponentsMove();
        verify(game, times(1)).setOpponentsCaptives(0);
        verify(game, times(1)).setPlayersCaptives(0);

    }

    @Test
    public void serverError_callsFunctions() {

        frontendManager.serverError();

        verify(game, times(1)).signalServerFailed();
        verify(game, times(1)).exitApp();
    }

    @Test
    public void playerLeft_callsFunctions() {

        frontendManager.playerLeft();

        verify(game, times(1)).signalOpponentsLeave();
        verify(game, times(1)).openNewGameCreation();
        verify(game, times(1)).closeGame();
    }

    @Test
    public void gameWon_callsFunctions() {

        frontendManager.gameWon();

        verify(game, times(1)).signalWin();
        verify(game, times(1)).openNewGameCreation();
        verify(game, times(1)).closeGame();
    }

    @Test
    public void gameLost_callsFunctions() {

        frontendManager.gameLost();

        verify(game, times(1)).signalLose();
        verify(game, times(1)).openNewGameCreation();
        verify(game, times(1)).closeGame();
    }

    @Test
    public void tie_callsFunctions() {

        frontendManager.tie();

        verify(game, times(1)).signalTie();
        verify(game, times(1)).openNewGameCreation();
        verify(game, times(1)).closeGame();
    }
}
