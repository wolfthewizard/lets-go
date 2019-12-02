package core;

import core.contract.Change;
import frontend.Game;
import frontend.GameBoardWindow;
import frontend.PassButtonActionListener;
import frontend.TileButtonActionListener;

import java.util.List;

public class FrontendManager implements IFrontendManager {

    private Game gameBoardWindow;
    private boolean playersTurn;

    public FrontendManager(GameBoardWindow gameBoardWindow) {
        this.gameBoardWindow = gameBoardWindow;
        playersTurn = false;
    }

    public void moveExecuted(List<Change> changes) {

        gameBoardWindow.clearServerResponse();
        gameBoardWindow.enforceChanges(changes);

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
    }

    public void serverError() {
        gameBoardWindow.serverFailed();
    }

    private void switchWhoseMove() {

        if(playersTurn) {
            playersTurn = false;
            gameBoardWindow.signalOpponentsMove();
            TileButtonActionListener.getInstance().setProcessingAllowed(false);
            PassButtonActionListener.getInstance().setProcessingAllowed(false);
        } else {
            playersTurn = true;
            gameBoardWindow.signalPlayersMove();
            TileButtonActionListener.getInstance().setProcessingAllowed(true);
            PassButtonActionListener.getInstance().setProcessingAllowed(true);
        }
    }
}
