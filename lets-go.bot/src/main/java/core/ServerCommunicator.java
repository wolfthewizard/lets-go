package core;

import contract.ActionDTO;
import contract.Coordinates;
import contract.enums.ActionType;
import contract.enums.BoardSize;
import core.interfaces.*;

public class ServerCommunicator implements ICommunicatorListener, ICommunicatorSender {

    private final IServerConnector serverConnector;
    private final IJsonParser jsonParser;
    private final IServerResponseReceiver serverResponseReceiver;

    public ServerCommunicator(IServerConnector serverConnector, IJsonParser jsonParser, IServerResponseReceiver serverResponseReceiver){
        this.serverConnector = serverConnector;
        this.jsonParser = jsonParser;
        this.serverResponseReceiver = serverResponseReceiver;

        serverConnector.StartListening(this);
    }

    public void sendStartGameMessage(BoardSize boardSize) {

        sendMessage(new ActionDTO(boardSize));
    }

    public void sendMoveMessage(Coordinates coordinates) {

        sendMessage(new ActionDTO(coordinates));
    }

    public void sendMovePassMessage() {

        sendMessage(new ActionDTO(ActionType.PASSMOVE));
    }

    public void sendLeaveGameMessage() {

        sendMessage(new ActionDTO(ActionType.LEAVEGAME));
    }

    private void sendMessage(ActionDTO actionDTO) {

        serverConnector.sendAction(jsonParser.parseActionToJson(actionDTO));
    }

    @Override
    public void responseFetched(String response) {
        serverResponseReceiver.responseReceived(jsonParser.parseJsonToResponse(response));
    }

    //public void shutDownConnection() {

      //  serverResponseRedirector.stopThread();
        //connectionClosed = true;
        //outputWriter.close();
        //try {
          //  socket.close();
        //} catch (IOException e) {
          //  e.printStackTrace();
        //}
    //}
}
