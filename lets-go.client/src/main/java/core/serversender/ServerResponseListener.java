package core.serversender;

import contract.ResponseDTO;
import core.IFrontendManager;
import core.interfaces.IResponseNumberCounter;

public class ServerResponseListener implements OnServerResponseListener {

    private final IFrontendManager frontendManager;
    private final IJsonParser jsonParser;
    private IResponseNumberCounter responseNumberCounter;

    public void setResponseNumberCounter(IResponseNumberCounter responseNumberCounter) {
        this.responseNumberCounter = responseNumberCounter;
    }

    public ServerResponseListener(IFrontendManager frontendManager, IJsonParser jsonParser) {
        this.frontendManager = frontendManager;
        this.jsonParser = jsonParser;
    }

    @Override
    public void responseReceived(String response) {

        passResponseDTO(jsonParser.parseJsonToResponse(response));
    }

    public void passResponseDTO(ResponseDTO responseDTO){
        if(responseNumberCounter != null){
            responseNumberCounter.countResponse(responseDTO.getResponseType());
        }

        switch (responseDTO.getResponseType()) {

            case MOVE_EXECUTED:
                frontendManager.moveExecuted(responseDTO.getChanges(), responseDTO.getResponsePrisoners());
                break;

            case INVALID_MOVE:
                frontendManager.invalidMove();
                break;

            case CANT_CREATE_GAME:
                frontendManager.cantCreateGame();
                break;

            case SUCCESS:
                frontendManager.success();
                break;

            case SERVER_ERROR:
                frontendManager.serverError();
                break;

            case GAMEWON:
                frontendManager.gameWon();
                break;

            case GAMELOST:
                frontendManager.gameLost();
                break;

            case PLAYER_LEFT:
                frontendManager.playerLeft();
        }
    }
}
