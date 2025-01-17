package main;

import contract.Change;
import contract.ResponsePrisoners;
import core.interfaces.IGameManager;

import java.util.List;


public class GameManager implements IGameManager {

    private Game gameBoardWindow;
    private boolean playersTurn;

    public GameManager(Game gameBoardWindow) {
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

    public void gameWon(int gameId) {

        gameBoardWindow.signalWin(gameId);
        gameBoardWindow.openNewGameCreation();
        gameBoardWindow.closeGame();
    }

    public void gameLost(int gameId) {

        gameBoardWindow.signalLose(gameId);
        gameBoardWindow.openNewGameCreation();
        gameBoardWindow.closeGame();
    }

    public void tie(int gameId) {

        gameBoardWindow.signalTie(gameId);
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
