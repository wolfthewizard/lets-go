package core.serversender;

import contract.ResponseDTO;
import core.IFrontendManager;

public class ServerResponseListener implements OnServerResponseListener {

    private IFrontendManager frontendManager;

    public ServerResponseListener(IFrontendManager frontendManager) {
        this.frontendManager = frontendManager;
    }

    @Override
    public void responseReceived(ResponseDTO responseDTO) {
        switch (responseDTO.getResponseType()) {

            case MOVE_EXECUTED:
                frontendManager.moveExecuted(responseDTO.getChanges(), responseDTO.getPrisoners());
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
