package main.helpers.actionhandlers;

import contract.ResponsePrisoners;
import contract.ResponseDTO;
import contract.enums.BoardSize;
import contract.enums.ResponseType;
import core.interfaces.ICommandDirector;
import main.ClientConnectionThread;
import main.IClientsManager;
import main.helpers.jsonparser.IJsonParser;
import main.helpers.playervalidator.IPlayerValidator;
import main.model.GameInfo;

import java.util.ArrayList;
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
            int gameId = commandDirector.createNewGame(boardSize);

            String successResponse = jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS));

            currentClient.beginAction(successResponse);

            waitingClient.beginAction(successResponse);

            if (randomGenerator.nextBoolean()) {
                playerValidator.addNewGame(threadId, waitingThreadId, gameId);

                waitingClient.completeAction(jsonParser
                        .parseResponseToJson(new ResponseDTO(new ArrayList<>(), new ResponsePrisoners(0, 0))));
            } else {
                playerValidator.addNewGame(waitingThreadId, threadId, gameId);

                currentClient.completeAction(jsonParser
                        .parseResponseToJson(new ResponseDTO(new ArrayList<>(), new ResponsePrisoners(0, 0))));
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
