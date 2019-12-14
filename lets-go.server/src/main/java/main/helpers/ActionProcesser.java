package main.helpers;

import core.interfaces.ICommandDirector;
import main.ClientConnectionThread;
import main.IClientsManager;
import contract.ActionDTO;
import contract.enums.BoardSize;
import main.helpers.actionhandler.*;
import main.model.GameInfo;

import java.util.HashMap;
import java.util.Random;

public class ActionProcesser implements IActionProcesser {

    private final IJsonParser jsonParser;
    private final IPlayerValidator playerValidator;
    private final ICommandDirector commandDirector;
    private final IClientsManager clientsManager;
    private AbstractActionHandler actionHandler;

    public ActionProcesser(IJsonParser jsonParser, IPlayerValidator playerValidator,
                           ICommandDirector commandDirector, IClientsManager clientsManager) {

        this.jsonParser = jsonParser;
        this.playerValidator = playerValidator;
        this.commandDirector = commandDirector;
        this.clientsManager = clientsManager;
    }

    public void ProcessAction(String message, int threadId) {

        ActionDTO action = jsonParser.parseJsonToAction(message);

        GameInfo gameInfo = playerValidator.getGameInfo(threadId);
        ClientConnectionThread currentClient = clientsManager.getClientWithId(threadId);

        switch (action.getActionType()) {
            case STARTGAME:

                actionHandler = new StartGameActionHandler(gameInfo, currentClient, jsonParser, commandDirector, threadId,
                        action.getBoardSize(), playerValidator, clientsManager);
                break;

            case PASSMOVE:
            case DOMOVE:

                actionHandler = new MoveExecuteActionHandler(gameInfo, currentClient, jsonParser,
                        clientsManager, commandDirector, action.getCoordinates());
                break;

            case LEAVEGAME:

                actionHandler = new LeaveGameActionHandler(gameInfo, currentClient, jsonParser, commandDirector,
                        clientsManager, playerValidator);
                break;
        }

        actionHandler.HandleAction();
    }
}
