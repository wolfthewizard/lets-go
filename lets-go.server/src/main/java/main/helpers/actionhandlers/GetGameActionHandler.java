package main.helpers.actionhandlers;

import contract.ResponseDTO;
import contract.enums.ResponseType;
import contract.gamerecord.GameRecord;
import core.interfaces.ICommandDirector;
import main.ClientConnectionThread;
import main.helpers.jsonparser.IJsonParser;
import main.model.GameInfo;

public class GetGameActionHandler extends AbstractActionHandler {

    private int gameId;
    public GetGameActionHandler(GameInfo gameInfo, ClientConnectionThread currentClient,
                                   IJsonParser jsonParser, ICommandDirector commandDirector, int gameId) {
        super(gameInfo, currentClient, jsonParser, commandDirector);

        this.gameId = gameId;
    }

    @Override
    protected void handleNullGameInfo() {
        GameRecord game = commandDirector.getGameData(gameId);
        ResponseDTO responseDTO;

        if(game==null){
            responseDTO = new ResponseDTO(ResponseType.CANT_GET_GAME);
        }
        else{
            responseDTO = new ResponseDTO(game);
        }

        currentClient.completeAction(jsonParser.parseResponseToJson(responseDTO));
    }

    @Override
    protected void handleNotNullGameInfo() {
        currentClient.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SERVER_ERROR)));
    }
}
