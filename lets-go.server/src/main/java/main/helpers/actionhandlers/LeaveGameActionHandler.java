package main.helpers.actionhandlers;

import contract.ResponseDTO;
import contract.enums.ResponseType;
import core.interfaces.ICommandDirector;
import main.ClientConnectionThread;
import main.IClientsManager;
import main.helpers.jsonparser.IJsonParser;
import main.helpers.playervalidator.IPlayerValidator;
import main.model.GameInfo;

public class LeaveGameActionHandler extends AbstractActionHandler {

    private IClientsManager clientsManager;
    private IPlayerValidator playerValidator;

    public LeaveGameActionHandler(GameInfo gameInfo, ClientConnectionThread currentClient, IJsonParser jsonParser,
                                  ICommandDirector commandDirector, IClientsManager clientsManager,
                                  IPlayerValidator playerValidator) {
        super(gameInfo, currentClient, jsonParser, commandDirector);

        this.clientsManager = clientsManager;
        this.playerValidator = playerValidator;
    }

    @Override
    protected void handleNullGameInfo() {

        playerValidator.playerLeft(currentClient.getThreadId());
        currentClient.closeConnection();
    }

    @Override
    protected void handleNotNullGameInfo() {

        clientsManager.getClientWithId(gameInfo.getSecondPlayerId()).completeAction(
                jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.PLAYER_LEFT, gameInfo.getMoveIdentity().getGameId())));

        playerValidator.removeGame(gameInfo.getMoveIdentity().getGameId());

        commandDirector.cancelGame(gameInfo.getMoveIdentity());

        currentClient.closeConnection();
    }
}
