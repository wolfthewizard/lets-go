package main.helpers.actionhandler;

import contract.ResponseDTO;
import contract.enums.ResponseType;
import core.interfaces.ICommandDirector;
import main.ClientConnectionThread;
import main.IClientsManager;
import main.helpers.IJsonParser;
import main.helpers.IPlayerValidator;
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

        currentClient.closeConnection();
    }

    @Override
    protected void handleNotNullGameInfo() {
        if (gameInfo.getSecondPlayerId() != 0) {
            clientsManager.getClientWithId(gameInfo.getSecondPlayerId()).completeAction(
                    jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.PLAYER_LEFT)));
        }

        playerValidator.removeGame(gameInfo.getMoveIdentity().getGameId());

        commandDirector.CancelGame(gameInfo.getMoveIdentity());

        currentClient.closeConnection();
    }
}
