package main.helpers.actionhandlers;

import contract.Coordinates;
import contract.ResponseDTO;
import contract.enums.ResponseType;
import core.interfaces.ICommandDirector;
import core.model.Move;
import core.model.MoveResponse;
import main.ClientConnectionThread;
import main.IClientsManager;
import main.helpers.jsonparser.IJsonParser;
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
        MoveResponse moveResponse = commandDirector.tryToMove(
                new Move(gameInfo.getMoveIdentity(), coordinates));

        ClientConnectionThread waitingClient = clientsManager.getClientWithId(gameInfo.getSecondPlayerId());

        switch (moveResponse.getMoveResponseType()) {
            case INVALID_MOVE:
                currentClient.beginAction(
                        jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.INVALID_MOVE)));
                break;

            case GAME_GOES_ON:
                ResponseDTO responseDTO = new ResponseDTO(moveResponse.getMoveExecution().getChanges(),
                        moveResponse.getMoveExecution().getResponsePrisoners());
                String response = jsonParser.parseResponseToJson(responseDTO);

                currentClient.beginAction(response);
                responseDTO.getResponsePrisoners().swapPrisoners();
                waitingClient.completeAction(jsonParser.parseResponseToJson(responseDTO));
                break;
            case CURRENT_PLAYER_WON:
                currentClient.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.GAMEWON)));
                waitingClient.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.GAMELOST)));
                break;
            case OTHER_PLAYER_WON:
                currentClient.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.GAMELOST)));
                waitingClient.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.GAMEWON)));
                break;
        }
    }
}
