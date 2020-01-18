package core;

import contract.ResponseDTO;
import core.interfaces.*;

public class ServerResponseReceiver implements IServerResponseReceiver {

    private final IMovesParser movesParser;
    private final IEndOfGameHandler endOfGameHandler;
    private final ITurnExecutor turnExecutor;

    public ServerResponseReceiver(IMovesParser movesParser, IEndOfGameHandler endOfGameHandler, ITurnExecutor turnExecutor) {
        this.movesParser = movesParser;
        this.endOfGameHandler = endOfGameHandler;
        this.turnExecutor = turnExecutor;
    }

    @Override
    public void responseReceived(ResponseDTO response) {
        switch (response.getResponseType()){
            case CANT_CREATE_GAME:
            case SERVER_ERROR:
            case GAMELOST:
            case GAMEWON:
            case PLAYER_LEFT:
            case TIE:
                endOfGameHandler.handleGameEnd(response.getResponseType(), response.getGameId());
                break;
            case INVALID_MOVE:
                turnExecutor.executeTurn(true);
                break;
            case MOVE_EXECUTED:
                movesParser.parseMoves(response.getChanges());
                break;
        }
    }
}
