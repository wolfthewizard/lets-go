package core.serversender;

import contract.ResponseDTO;
import core.FrontendManager;
import core.IFrontendManager;
import frontend.GameBoardWindow;

public class ServerResponseListener implements OnServerResponseListener {

    private IFrontendManager frontendManager;

    public ServerResponseListener(GameBoardWindow gameBoardWindow) {
        this.frontendManager = new FrontendManager(gameBoardWindow);
    }

    @Override
    public void responseReceived(ResponseDTO responseDTO) {
        switch (responseDTO.getResponseType()) {

            case MOVE_EXECUTED:
                frontendManager.moveExecuted(responseDTO.getChanges());
                break;

            case INVALID_MOVE:
                frontendManager.invalidMove();
                break;

            case WAITING_FOR_PLAYER:
                frontendManager.waitingForPlayer();
                break;

            case CANT_CREATE_GAME:
                frontendManager.cantCreateGame();
                break;

            case SUCCESS:
                frontendManager.success();
                break;

            case SERVER_ERROR:
                frontendManager.serverError();
        }
    }
}
