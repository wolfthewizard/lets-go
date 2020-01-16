package core.serversender;

import contract.ResponseDTO;
import core.interfaces.IJsonParser;
import core.interfaces.IResponseNumberCounter;
import core.interfaces.IRewindManager;
import core.interfaces.IServerResponseListener;

public class RewindServerResponseListener implements IServerResponseListener {

    private final IRewindManager rewindManager;
    private final IJsonParser jsonParser;

    public RewindServerResponseListener(IRewindManager rewindManager, IJsonParser jsonParser) {
        this.rewindManager = rewindManager;
        this.jsonParser = jsonParser;
    }

    @Override
    public void responseReceived(String response) {
        passResponseDTO(jsonParser.parseJsonToResponse(response));
    }

    @Override
    public void setResponseNumberCounter(IResponseNumberCounter responseNumberCounter) {

    }

    @Override
    public void passResponseDTO(ResponseDTO responseDTO) {

        switch (responseDTO.getResponseType()) {

            case GAME_GOT:
                rewindManager.rewind(responseDTO.getGameRecord());
                break;

            case CANT_GET_GAME:
                rewindManager.handleInvalidId();
                break;
        }
    }
}
