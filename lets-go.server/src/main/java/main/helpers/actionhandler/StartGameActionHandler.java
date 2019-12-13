package main.helpers.actionhandler;

import contract.Prisoners;
import contract.ResponseDTO;
import contract.enums.BoardSize;
import contract.enums.ResponseType;
import core.interfaces.ICommandDirector;
import main.ClientConnectionThread;
import main.IClientsManager;
import main.helpers.IJsonParser;
import main.helpers.IPlayerValidator;
import main.model.GameInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class StartGameActionHandler extends AbstractActionHandler {

    private int threadId;
    private BoardSize boardSize;
    private IPlayerValidator playerValidator;
    private IClientsManager clientsManager;
    private Random randomGenerator;

    public StartGameActionHandler(GameInfo gameInfo, ClientConnectionThread currentClient,
                                  IJsonParser jsonParser, ICommandDirector commandDirector, int threadId,
                                  BoardSize boardSize, IPlayerValidator playerValidator, IClientsManager clientsManager) {
        super(gameInfo, currentClient, jsonParser, commandDirector);

        this.playerValidator = playerValidator;
        this.threadId = threadId;
        this.boardSize = boardSize;
        this.clientsManager = clientsManager;
        this.randomGenerator = new Random();
    }

    @Override
    protected void handleNullGameInfo() {

        Integer waitingThreadId = playerValidator.getWaitingPlayerId(boardSize);

        if (waitingThreadId != null) {
            ClientConnectionThread waitingClient = clientsManager.getClientWithId(waitingThreadId);
            int gameId = commandDirector.CreateNewGame();

            currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS)));

            waitingClient.beginAction(jsonParser
                    .parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS)));

            if (randomGenerator.nextBoolean()) {
                playerValidator.addNewGame(threadId, waitingThreadId, gameId);

                waitingClient.completeAction(jsonParser
                        .parseResponseToJson(new ResponseDTO(new ArrayList<>(), new Prisoners(0, 0))));
            } else {
                playerValidator.addNewGame(waitingThreadId, threadId, gameId);

                currentClient.completeAction(jsonParser
                        .parseResponseToJson(new ResponseDTO(new ArrayList<>(), new Prisoners(0, 0))));
            }
        } else {
            playerValidator.addWaitingPlayer(boardSize, threadId);
        }
    }

    @Override
    protected void handleNotNullGameInfo() {

        currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.CANT_CREATE_GAME)));
    }
}
