package main.helpers;

import core.ICommandDirector;
import core.model.Change;
import core.model.Move;
import javafx.util.Pair;
import main.ClientConnectionThread;
import main.IClientsManager;
import main.contract.ActionDTO;
import main.contract.ResponseDTO;
import main.contract.enums.BoardSize;
import main.contract.enums.ResponseType;
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

    public ActionProcesser(IJsonParser jsonParser, IPlayerValidator playerValidator,
                           ICommandDirector commandDirector, IClientsManager clientsManager) {

        this.jsonParser = jsonParser;
        this.playerValidator = playerValidator;
        this.commandDirector = commandDirector;
        this.clientsManager = clientsManager;
    }

    public void ProcessAction(String message, int threadId) {

        ActionDTO action = jsonParser.parseJsonToAction(message);

        GameInfo gameInfo;
        ArrayList<Change> changes;
        String response;
        ClientConnectionThread currentClient;

        switch (action.getActionType()) {
            case STARTBOTGAME:

                currentClient = clientsManager.getClientWithId(threadId);

                if (playerValidator.getGameInfo(threadId) != null) {
                    currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.CANTCREATEGAME)));
                }
                Pair<Integer, ArrayList<Change>> idWithMove;

                if (randomGenerator.nextBoolean()) {
                    idWithMove = commandDirector.CreateNewBotGame(true, action.getBoardSize());
                    playerValidator.addNewGame(threadId, 0, idWithMove.getKey());
                } else {
                    idWithMove = commandDirector.CreateNewBotGame(false, action.getBoardSize());
                    playerValidator.addNewGame(0, threadId, idWithMove.getKey());
                }
                System.out.println(threadId);
                clientsManager.getClientWithId(threadId).beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS)));
                clientsManager.getClientWithId(threadId).completeAction(jsonParser.parseResponseToJson(new ResponseDTO(idWithMove.getValue())));

            case STARTMULTIPLAYERGAME:

                currentClient = clientsManager.getClientWithId(threadId);

                if (playerValidator.getGameInfo(threadId) != null) {
                    currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.CANTCREATEGAME)));
                }
                currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.WAITINGFORPLAYER)));
                if(waitingThreads.containsKey(action.getBoardSize())) {
                    int waitingThreadId = waitingThreads.get(action.getBoardSize());
                    int gameId =commandDirector.CreateNewMultiplayerGame();
                    if(randomGenerator.nextBoolean()) {
                        playerValidator.addNewGame(threadId, waitingThreadId, gameId);
                    }
                    else {
                        playerValidator.addNewGame(waitingThreadId, threadId, gameId);
                    }
                    currentClient.completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS)));
                    clientsManager.getClientWithId(waitingThreadId).completeAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS)));
                }
                else {
                    waitingThreads.put(action.getBoardSize(), threadId);
                }

                break;

            case PASSMOVE:

                gameInfo = playerValidator.getGameInfo(threadId);

                changes = commandDirector.TryToMove(new Move(gameInfo.getMoveIdentity(), null));

                response = jsonParser.parseResponseToJson(new ResponseDTO(changes));
                clientsManager.getClientWithId(threadId).beginAction(response);
                clientsManager.getClientWithId(gameInfo.getSecondPlayerId()).completeAction(response);

                break;

            case DOMOVE:

                gameInfo = playerValidator.getGameInfo(threadId);

                changes = commandDirector.TryToMove(new Move(gameInfo.getMoveIdentity(), action.getCoordinates()));

                response = jsonParser.parseResponseToJson(new ResponseDTO(changes));
                clientsManager.getClientWithId(threadId).beginAction(response);
                clientsManager.getClientWithId(gameInfo.getSecondPlayerId()).completeAction(response);

                break;

            case LEAVEGAME:

                gameInfo = playerValidator.getGameInfo(threadId);

                playerValidator.removeGame(gameInfo.getMoveIdentity().getGameId());

                commandDirector.CancelGame(gameInfo.getMoveIdentity());

                clientsManager.getClientWithId(threadId).closeConnection();

            default:
                break;
        }
    }
}
