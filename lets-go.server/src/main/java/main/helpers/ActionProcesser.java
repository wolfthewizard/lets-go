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
        String response;
        ClientConnectionThread currentClient;
        ResponseDTO responseDTO;

        switch (action.getActionType()) {
            case STARTBOTGAME:

                currentClient = clientsManager.getClientWithId(threadId);

                if (playerValidator.getGameInfo(threadId) != null) {
                    currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.CANT_CREATE_GAME)));
                }
                CreateNewBotGameResult createNewBotGameResult;

                if (randomGenerator.nextBoolean()) {
                    createNewBotGameResult = commandDirector.CreateNewBotGame(true, action.getBoardSize());
                    playerValidator.addNewGame(threadId, 0, createNewBotGameResult.getGameId());
                } else {
                    createNewBotGameResult = commandDirector.CreateNewBotGame(false, action.getBoardSize());
                    playerValidator.addNewGame(0, threadId, createNewBotGameResult.getGameId());
                }

                currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS)));
                currentClient.completeAction(jsonParser.parseResponseToJson(
                        new ResponseDTO(createNewBotGameResult.getChanges(), createNewBotGameResult.getPrisoners())));

                break;

            case STARTMULTIPLAYERGAME:

                currentClient = clientsManager.getClientWithId(threadId);

                if (playerValidator.getGameInfo(threadId) != null) {
                    currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.CANT_CREATE_GAME)));
                }

                if(waitingThreads.containsKey(action.getBoardSize())) {
                    int waitingThreadId = waitingThreads.get(action.getBoardSize());
                    waitingThreads.remove(action.getBoardSize());
                    int gameId =commandDirector.CreateNewMultiplayerGame();

                    currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS)));

                    ClientConnectionThread waitingClient = clientsManager.getClientWithId(waitingThreadId);
                    waitingClient.beginAction(jsonParser
                            .parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS)));

                    if(randomGenerator.nextBoolean()) {
                        playerValidator.addNewGame(threadId, waitingThreadId, gameId);

                        waitingClient.completeAction(jsonParser
                                .parseResponseToJson(new ResponseDTO(new ArrayList<>(), new Prisoners(0,0))));
                    }
                    else {
                        playerValidator.addNewGame(waitingThreadId, threadId, gameId);

                        currentClient.completeAction(jsonParser
                                .parseResponseToJson(new ResponseDTO(new ArrayList<>(), new Prisoners(0,0))));
                    }
                }
                else {
                    waitingThreads.put(action.getBoardSize(), threadId);
                }

                break;

            case PASSMOVE:
            case DOMOVE:

                currentClient = clientsManager.getClientWithId(threadId);
                gameInfo = playerValidator.getGameInfo(threadId);

                if(gameInfo == null) {
                    currentClient.beginAction(
                            jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SERVER_ERROR)));
                }

                MoveResponse moveResponse = commandDirector.TryToMove(
                        new Move(gameInfo.getMoveIdentity(), action.getCoordinates()));

                switch (moveResponse.getMoveResponseType()) {
                    case INVALID_MOVE:
                        currentClient.beginAction(
                                jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.INVALID_MOVE)));
                        break;

                    case GAME_GOES_ON:
                        responseDTO= new ResponseDTO(moveResponse.getChanges(), moveResponse.getPrisoners());
                        response = jsonParser.parseResponseToJson(responseDTO);

                        if(gameInfo.getSecondPlayerId() == 0) {
                            currentClient.beginAction(response);
                            currentClient.completeAction(response);
                        } else {
                            currentClient.beginAction(response);
                            responseDTO.getPrisoners().swapPrisoners();
                            clientsManager.getClientWithId(gameInfo.getSecondPlayerId()).completeAction(jsonParser.parseResponseToJson(responseDTO));
                        }
                        break;
                }
                if (moveResponse == null) {

                }



                break;

            case LEAVEGAME:

                gameInfo = playerValidator.getGameInfo(threadId);

                if(gameInfo != null) {

                    if (gameInfo.getSecondPlayerId() != 0) {
                        clientsManager.getClientWithId(gameInfo.getSecondPlayerId()).completeAction(
                                jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.PLAYER_LEFT)));
                    }

                    playerValidator.removeGame(gameInfo.getMoveIdentity().getGameId());

                    commandDirector.CancelGame(gameInfo.getMoveIdentity());
                }

                clientsManager.getClientWithId(threadId).closeConnection();

            default:
                break;
        }
    }
}
