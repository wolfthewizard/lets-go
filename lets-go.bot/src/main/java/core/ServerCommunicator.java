package core;

import contract.ActionDTO;
import contract.Coordinates;
import contract.enums.ActionType;
import contract.enums.BoardSize;
import core.interfaces.IJsonParser;
import core.interfaces.IServerSender;

public class ServerCommunicator {

    private final IServerSender serverSender;
    private final IJsonParser jsonParser;

    public ServerCommunicator(IServerSender serverSender, IJsonParser jsonParser){
        this.serverSender = serverSender;
        this.jsonParser = jsonParser;
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

        serverSender.sendAction(jsonParser.parseActionToJson(actionDTO));
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
