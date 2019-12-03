package main.helpers;

import contract.Prisoners;
import core.ICommandDirector;
import core.model.*;
import main.ClientConnectionThread;
import main.IClientsManager;
import contract.ActionDTO;
import contract.ResponseDTO;
import contract.enums.BoardSize;
import contract.enums.ResponseType;
import main.helpers.actionhandler.*;
import main.model.GameInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class ActionProcesser implements IActionProcesser {

    private final HashMap<BoardSize, Integer> waitingThreads = new HashMap<>();
    private final IJsonParser jsonParser;
    private final IPlayerValidator playerValidator;
    private final ICommandDirector commandDirector;
    private final IClientsManager clientsManager;
    private final Random randomGenerator = new Random();
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
        String response;
        ClientConnectionThread currentClient = clientsManager.getClientWithId(threadId);
        ResponseDTO responseDTO;

        switch (action.getActionType()) {
            case STARTBOTGAME:

                actionHandler = new StartBotGameActionHandler(gameInfo, currentClient, jsonParser, commandDirector, playerValidator
                , threadId, action.getBoardSize());
                break;

            case STARTMULTIPLAYERGAME:

                actionHandler = new StartMultiplayerGameActionHandler(gameInfo, currentClient, jsonParser, commandDirector, threadId,
                        action.getBoardSize(), playerValidator, clientsManager.getClientWithId(gameInfo.getSecondPlayerId()), waitingThreads);
                    break;

            case PASSMOVE:
            case DOMOVE:

                actionHandler = new MoveExecuteActionHandler(gameInfo, currentClient, jsonParser,
                        clientsManager.getClientWithId(gameInfo.getSecondPlayerId()), commandDirector, action.getCoordinates());


                break;

            case LEAVEGAME:

                actionHandler = new LeaveGameActionHandler(gameInfo, currentClient, jsonParser, commandDirector,
                        clientsManager.getClientWithId(gameInfo.getSecondPlayerId()),playerValidator);


                break;
        }

        actionHandler.HandleAction();
    }
}
