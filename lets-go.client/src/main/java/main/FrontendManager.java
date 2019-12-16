package main;

import contract.Change;
import contract.ResponsePrisoners;
import core.interfaces.IFrontendManager;

import java.util.List;


public class FrontendManager implements IFrontendManager {

    private Game gameBoardWindow;
    private boolean playersTurn;

    public FrontendManager(Game gameBoardWindow) {
        this.gameBoardWindow = gameBoardWindow;
        playersTurn = false;
    }

    public void moveExecuted(List<Change> changes, ResponsePrisoners prisoners) {

        gameBoardWindow.clearServerResponse();
        gameBoardWindow.enforceChanges(changes);
        gameBoardWindow.setPlayersCaptives(prisoners.getYourPrisoners());
        gameBoardWindow.setOpponentsCaptives(prisoners.getEnemyPrisoners());

        switchWhoseMove();
    }

    public void invalidMove() {
        gameBoardWindow.signalInvalidMove();
    }

    public void cantCreateGame() {

        gameBoardWindow.signalFailedToCreateGame();
        gameBoardWindow.exitApp();
    }

    public void success() {

        gameBoardWindow.clearServerResponse();
        gameBoardWindow.signalOpponentsMove();
        gameBoardWindow.setOpponentsCaptives(0);
        gameBoardWindow.setPlayersCaptives(0);
    }

    public void serverError() {

        gameBoardWindow.signalServerFailed();
        gameBoardWindow.exitApp();
    }

    public void playerLeft() {

        gameBoardWindow.signalOpponentsLeave();
        gameBoardWindow.openNewGameCreation();
        gameBoardWindow.closeGame();
    }

    public void gameWon() {

        gameBoardWindow.signalWin();
        gameBoardWindow.openNewGameCreation();
        gameBoardWindow.closeGame();
    }

    public void gameLost() {

        gameBoardWindow.signalLose();
        gameBoardWindow.openNewGameCreation();
        gameBoardWindow.closeGame();
    }

    public void tie() {

        gameBoardWindow.signalTie();
        gameBoardWindow.openNewGameCreation();
        gameBoardWindow.closeGame();
    }

    private void switchWhoseMove() {

        if(playersTurn) {
            playersTurn = false;
            gameBoardWindow.signalOpponentsMove();
        } else {
            playersTurn = true;
            gameBoardWindow.signalPlayersMove();
        }
    }
}
