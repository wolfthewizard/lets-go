package core;

import contract.ActionDTO;
import contract.Coordinates;
import contract.enums.ActionType;
import contract.enums.BoardSize;
import core.interfaces.*;

public class ServerCommunicator implements ICommunicatorListener, ICommunicatorSender {

    private final IServerConnector serverConnector;
    private final IJsonParser jsonParser;
    private IServerResponseReceiver serverResponseReceiver;

    public ServerCommunicator(IServerConnector serverConnector, IJsonParser jsonParser){
        this.serverConnector = serverConnector;
        this.jsonParser = jsonParser;
    }

    public void setServerResponseReceiver(IServerResponseReceiver serverResponseReceiver) {
        this.serverResponseReceiver = serverResponseReceiver;
        serverConnector.startListening(this);
    }

    @Override
    public void sendStartGameMessage(BoardSize boardSize) {

        sendMessage(new ActionDTO(boardSize));
    }

    @Override
    public void sendMoveMessage(Coordinates coordinates) {

        sendMessage(new ActionDTO(coordinates));
    }

    @Override
    public void sendMovePassMessage() {

        sendMessage(new ActionDTO(ActionType.PASSMOVE));
    }

    @Override
    public void sendLeaveGameMessage() {

        sendMessage(new ActionDTO(ActionType.LEAVEGAME));
        serverConnector.shutDown();
    }

    private void sendMessage(ActionDTO actionDTO) {

        serverConnector.sendAction(jsonParser.parseActionToJson(actionDTO));
    }

    @Override
    public void responseFetched(String response) {
        serverResponseReceiver.responseReceived(jsonParser.parseJsonToResponse(response));
    }
}
