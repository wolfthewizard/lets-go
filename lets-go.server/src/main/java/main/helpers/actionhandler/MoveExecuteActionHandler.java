package main.helpers.actionhandler;

import contract.Coordinates;
import contract.ResponseDTO;
import contract.enums.ResponseType;
import core.ICommandDirector;
import core.model.Move;
import core.model.MoveResponse;
import main.ClientConnectionThread;
import main.ClientsManager;
import main.IClientsManager;
import main.helpers.IJsonParser;
import main.model.GameInfo;

public class MoveExecuteActionHandler extends AbstractActionHandler {

    private IClientsManager clientsManager;
    private Coordinates coordinates;

    public MoveExecuteActionHandler(GameInfo gameInfo, ClientConnectionThread currentClient, IJsonParser jsonParser,
                                    IClientsManager clientsManager, ICommandDirector commandDirector, Coordinates coordinates) {
        super(gameInfo, currentClient, jsonParser, commandDirector);

        this.clientsManager = clientsManager;
        this.coordinates = coordinates;
    }

    @Override
    protected void handleNullGameInfo() {
        currentClient.beginAction(
                jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SERVER_ERROR)));
    }

    @Override
    protected void handleNotNullGameInfo() {
        MoveResponse moveResponse = commandDirector.TryToMove(
                new Move(gameInfo.getMoveIdentity(), coordinates));

        ClientConnectionThread waitingClient = null;

        if(gameInfo.getSecondPlayerId() != 0) {
            waitingClient = clientsManager.getClientWithId(gameInfo.getSecondPlayerId());
        }


        switch (moveResponse.getMoveResponseType()) {
            case INVALID_MOVE:
                currentClient.beginAction(
                        jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.INVALID_MOVE)));
                break;

            case GAME_GOES_ON:
                ResponseDTO responseDTO= new ResponseDTO(moveResponse.getMoveExecution().getChanges(),
                        moveResponse.getMoveExecution().getPrisoners());
                String response = jsonParser.parseResponseToJson(responseDTO);

                if(waitingClient == null) {
                    currentClient.beginAction(response);
                    currentClient.completeAction(response);
                } else {
                    currentClient.beginAction(response);
                    responseDTO.getPrisoners().swapPrisoners();
                    waitingClient.completeAction(jsonParser.parseResponseToJson(responseDTO));
                }
                break;
            case CURRENT_PLAYER_WON:
                currentClient.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.GAMEWON)));
                if(gameInfo.getSecondPlayerId() != 0) {
                    waitingClient.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.GAMELOST)));
                }
                break;
            case OTHER_PLAYER_WON:
                currentClient.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.GAMELOST)));
                if(waitingClient != null) {
                    waitingClient.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.GAMEWON)));
                }
                break;
        }
    }
}
