package main.helpers.actionhandler;

import contract.Prisoners;
import contract.ResponseDTO;
import contract.enums.BoardSize;
import contract.enums.ResponseType;
import core.ICommandDirector;
import main.ClientConnectionThread;
import main.helpers.IJsonParser;
import main.helpers.IPlayerValidator;
import main.model.GameInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class StartMultiplayerGameActionHandler extends AbstractActionHandler {

    private int threadId;
    private BoardSize boardSize;
    private IPlayerValidator playerValidator;
    private ClientConnectionThread waitingClient;
    private Random randomGenerator;
    private HashMap<BoardSize, Integer> waitingThreads;

    public StartMultiplayerGameActionHandler(GameInfo gameInfo, ClientConnectionThread currentClient,
                                             IJsonParser jsonParser, ICommandDirector commandDirector, int threadId,
                                             BoardSize boardSize, IPlayerValidator playerValidator, ClientConnectionThread waitingClient,
                                             HashMap<BoardSize, Integer> waitingThreads) {
        super(gameInfo, currentClient, jsonParser, commandDirector);

        this.playerValidator = playerValidator;
        this.threadId = threadId;
        this.boardSize = boardSize;
        this.waitingClient = waitingClient;
        this.randomGenerator = new Random();
        this.waitingThreads = waitingThreads;
    }

    @Override
    protected void handleNullGameInfo() {
        currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.CANT_CREATE_GAME)));
    }

    @Override
    protected void handleValidAction() {
        if(waitingThreads.containsKey(boardSize)) {
            int waitingThreadId = waitingThreads.get(boardSize);
            waitingThreads.remove(boardSize);
            int gameId =commandDirector.CreateNewMultiplayerGame();

            currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS)));

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
            waitingThreads.put(boardSize, threadId);
        }
    }
}
