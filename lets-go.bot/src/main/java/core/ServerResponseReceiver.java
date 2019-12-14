package core;

import contract.ResponseDTO;
import core.interfaces.IEndOfGameHandler;
import core.interfaces.IJsonParser;
import core.interfaces.IMovesParser;
import core.interfaces.IServerResponseReceiver;

public class ServerResponseReceiver implements IServerResponseReceiver {

    private final IMovesParser movesParser;
    private final IEndOfGameHandler endOfGameHandler;

    public ServerResponseReceiver(IMovesParser movesParser, IEndOfGameHandler endOfGameHandler) {
        this.movesParser = movesParser;
        this.endOfGameHandler = endOfGameHandler;
    }


    @Override
    public void responseReceived(ResponseDTO response) {

    }
}
