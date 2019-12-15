package main.helpers.actionprocesser;

import contract.ResponseDTO;
import contract.enums.ResponseType;
import core.interfaces.ICommandDirector;
import main.ClientConnectionThread;
import main.IClientsManager;
import contract.ActionDTO;
import main.helpers.jsonparser.IJsonParser;
import main.helpers.playervalidator.IPlayerValidator;
import main.helpers.actionhandlers.*;
import main.model.GameInfo;

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

    @Override
    public void processAction(String message, int threadId) {

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

        actionHandler.handleAction();
    }

    @Override
    public void closeAllConnections(){
        for (ClientConnectionThread thread : clientsManager.getAllClients()) {
            thread.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SERVER_ERROR)));
            thread.closeConnection();
        }
    }
}
