package core;

import core.contract.Change;
import core.contract.ResponseDTO;
import core.serversender.OnServerResponseListener;
import frontend.GameBoardWindow;
import frontend.PassButtonActionListener;
import frontend.TileButtonActionListener;

import java.util.List;

public class FrontendManager implements OnServerResponseListener {

    private GameBoardWindow gameBoardWindow;
    private boolean playersTurn;

    public FrontendManager(GameBoardWindow gameBoardWindow) {
        this.gameBoardWindow = gameBoardWindow;
        playersTurn = false;
    }

    @Override
    public void responseReceived(ResponseDTO responseDTO) {
        switch (responseDTO.getResponseType()) {

            case MOVE_EXECUTED:
                moveExecuted(responseDTO.getChanges());
                break;

            case INVALID_MOVE:
                invalidMove();
                break;

            case WAITING_FOR_PLAYER:
                waitingForPlayer();
                break;

            case CANT_CREATE_GAME:
                cantCreateGame();
                break;

            case SUCCESS:
                success();
                break;

            case SERVER_ERROR:
                serverError();
        }
    }

    private void moveExecuted(List<Change> changes) {

        gameBoardWindow.clearServerResponse();
        gameBoardWindow.enforceChanges(changes);

        switchWhoseMove();
    }

    private void invalidMove() {
        gameBoardWindow.signalInvalidMove();
    }

    private void waitingForPlayer() {

    }

    private void cantCreateGame() {
        gameBoardWindow.failedToCreateGame();
    }

    private void success() {

        gameBoardWindow.clearServerResponse();
        gameBoardWindow.signalOpponentsMove();
    }

    private void serverError() {
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
