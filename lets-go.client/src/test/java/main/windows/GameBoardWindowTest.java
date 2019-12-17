package main.windows;

import static org.junit.jupiter.api.Assertions.*;

import contract.ActionDTO;
import contract.Coordinates;
import contract.enums.BoardSize;
import core.interfaces.IJsonParser;
import core.interfaces.IResponseNumberCounter;
import core.interfaces.IServerConnector;
import core.interfaces.IServerResponseListener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import contract.Change;
import core.serversender.JsonParser;
import core.serversender.ServerCommunicator;
import main.BoardPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class GameBoardWindowTest {

    /*private GameBoardWindow gameBoardWindow;

    @BeforeEach
    public void setUp(){
        gameBoardWindow = new GameBoardWindow(new BoardPanel(BoardSize.NINETEEN));
    }


    @Test
    public void clearServerResponse_doesNotThrow() {

        //gameBoardWindow = new GameBoardWindow(boardPanelMock);
        assertDoesNotThrow(() -> gameBoardWindow.clearServerResponse());
    }

    @Test
    public void setOpponentsCaptives_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.setOpponentsCaptives(0));
    }

    @Test
    public void setPlayersCaptives_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.setPlayersCaptives(0));
    }

    @Test
    public void signalPlayersMove_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.signalPlayersMove());
    }

    @Test
    public void signalOpponentsMove_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.signalOpponentsMove());
    }

    @Test
    public void signalInvalidMove_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.signalInvalidMove());
    }

    @Test
    public void signalOpponentsLeave_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.signalOpponentsLeave());
    }

    @Test
    public void signalWin_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.signalWin());
    }

    @Test
    public void signalLose_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.signalLose());
    }

    @Test
    public void signalTie_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.signalTie());
    }

    @Test
    public void openNewGameCreation_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.openNewGameCreation());
    }

    @Test
    public void closeGame_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.closeGame());
    }

    @Test
    public void exitApp_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.exitApp());
    }

    @Test
    public void signalFailedToCreateGame_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.signalFailedToCreateGame());
    }

    @Test
    public void signalServerFailed_doesNotThrow() {

        assertDoesNotThrow(() -> gameBoardWindow.signalServerFailed());
    }

    @Test
    public void enforceChanges_doesNotThrow() {

        List<Change> changes = new ArrayList<>();

        assertDoesNotThrow(() -> gameBoardWindow.enforceChanges(changes));

    }*/

}
