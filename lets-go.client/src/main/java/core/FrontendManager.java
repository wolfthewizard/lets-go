package core;

import contract.Change;
import contract.Prisoners;
import frontend.Game;
import frontend.GameBoardWindow;
import frontend.PassButtonActionListener;
import frontend.TileButtonActionListener;

import java.util.ArrayList;


public class FrontendManager implements IFrontendManager {

    private Game gameBoardWindow;
    private boolean playersTurn;

    public FrontendManager(GameBoardWindow gameBoardWindow) {
        this.gameBoardWindow = gameBoardWindow;
        playersTurn = false;
    }

    public void moveExecuted(ArrayList<Change> changes, Prisoners prisoners) {

        gameBoardWindow.clearServerResponse();
        gameBoardWindow.enforceChanges(changes);
        gameBoardWindow.setPlayersCaptives(prisoners.getYourPrisoners());
        gameBoardWindow.setOpponentsCaptives(prisoners.getEnemyPrisoners());

        switchWhoseMove();
    }

    public void invalidMove() {
        gameBoardWindow.signalInvalidMove();
    }

    public void waitingForPlayer() {

    }

    public void cantCreateGame() {
        gameBoardWindow.failedToCreateGame();
    }

    public void success() {

        gameBoardWindow.clearServerResponse();
        gameBoardWindow.signalOpponentsMove();
        gameBoardWindow.setOpponentsCaptives(0);
        gameBoardWindow.setPlayersCaptives(0);
    }

    public void serverError() {
        gameBoardWindow.serverFailed();
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
