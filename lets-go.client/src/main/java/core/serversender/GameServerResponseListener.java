package core.serversender;

import contract.ResponseDTO;
import core.interfaces.IGameManager;
import core.interfaces.IJsonParser;
import core.interfaces.IResponseNumberCounter;
import core.interfaces.IServerResponseListener;

public class GameServerResponseListener implements IServerResponseListener {

    private final IGameManager gameManager;
    private final IJsonParser jsonParser;
    private IResponseNumberCounter responseNumberCounter;

    public void setResponseNumberCounter(IResponseNumberCounter responseNumberCounter) {
        this.responseNumberCounter = responseNumberCounter;
    }

    public GameServerResponseListener(IGameManager gameManager, IJsonParser jsonParser) {
        this.gameManager = gameManager;
        this.jsonParser = jsonParser;
    }

    @Override
    public void responseReceived(String response) {

        passResponseDTO(jsonParser.parseJsonToResponse(response));
    }

    public void passResponseDTO(ResponseDTO responseDTO) {
        if (responseNumberCounter != null) {
            responseNumberCounter.countResponse(responseDTO.getResponseType());
        }

        switch (responseDTO.getResponseType()) {

            case MOVE_EXECUTED:
                gameManager.moveExecuted(responseDTO.getChanges(), responseDTO.getResponsePrisoners());
                break;

            case INVALID_MOVE:
                gameManager.invalidMove();
                break;

            case CANT_CREATE_GAME:
                gameManager.cantCreateGame();
                break;

            case SUCCESS:
                gameManager.success();
                break;

            case SERVER_ERROR:
                gameManager.serverError();
                break;

            case GAMEWON:
                gameManager.gameWon(responseDTO.getGameId());
                break;

            case GAMELOST:
                gameManager.gameLost(responseDTO.getGameId());
                break;

            case TIE:
                gameManager.tie(responseDTO.getGameId());
                break;

            case PLAYER_LEFT:
                gameManager.playerLeft();
                break;
        }
    }
}
