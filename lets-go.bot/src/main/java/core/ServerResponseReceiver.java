package core;

import contract.ResponseDTO;
import core.interfaces.IJsonParser;
import core.interfaces.IServerResponseReceiver;

public class ServerResponseReceiver implements IServerResponseReceiver {

    private final IJsonParser jsonParser;

    public ServerResponseReceiver(IJsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }


    @Override
    public void responseReceived(String response) {
        ResponseDTO responseDTO = jsonParser.parseJsonToResponse(response);

    }
}
