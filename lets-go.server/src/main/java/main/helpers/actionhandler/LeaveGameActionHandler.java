package main.helpers.actionhandler;

import contract.ResponseDTO;
import contract.enums.ResponseType;
import core.ICommandDirector;
import main.ClientConnectionThread;
import main.helpers.IJsonParser;
import main.helpers.IPlayerValidator;
import main.model.GameInfo;

public class LeaveGameActionHandler extends AbstractActionHandler {

    private ClientConnectionThread waitingClient;
    private IPlayerValidator playerValidator;

    public LeaveGameActionHandler(GameInfo gameInfo, ClientConnectionThread currentClient, IJsonParser jsonParser,
                                  ICommandDirector commandDirector, ClientConnectionThread waitingClient,
                                  IPlayerValidator playerValidator) {
        super(gameInfo, currentClient, jsonParser, commandDirector);

        this.waitingClient = waitingClient;
        this.playerValidator = playerValidator;
    }

    @Override
    protected void handleNullGameInfo() {
        if (gameInfo.getSecondPlayerId() != 0) {
            waitingClient.completeAction(
                    jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.PLAYER_LEFT)));
        }

        playerValidator.removeGame(gameInfo.getMoveIdentity().getGameId());

        commandDirector.CancelGame(gameInfo.getMoveIdentity());

        currentClient.closeConnection();
    }

    @Override
    protected void handleValidAction() {
        currentClient.closeConnection();
    }
}
