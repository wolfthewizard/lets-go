package main.helpers.actionhandler;

import contract.ResponseDTO;
import contract.enums.BoardSize;
import contract.enums.ResponseType;
import core.interfaces.ICommandDirector;
import core.model.CreateNewBotGameResult;
import main.ClientConnectionThread;
import main.helpers.IJsonParser;
import main.helpers.IPlayerValidator;
import main.model.GameInfo;

import java.util.Random;

public class StartBotGameActionHandler extends AbstractActionHandler {

    private Random randomGenerator;
    private IPlayerValidator playerValidator;
    private BoardSize boardSize;
    private int threadId;

    public StartBotGameActionHandler(GameInfo gameInfo, ClientConnectionThread currentClient, IJsonParser jsonParser,
                                     ICommandDirector commandDirector, IPlayerValidator playerValidator, int threadId,
                                     BoardSize boardSize) {
        super(gameInfo, currentClient, jsonParser, commandDirector);

        randomGenerator = new Random();
        this.playerValidator = playerValidator;
        this.threadId  = threadId;
        this.boardSize = boardSize;
    }

    @Override
    protected void handleNullGameInfo() {
        CreateNewBotGameResult createNewBotGameResult;

        if (randomGenerator.nextBoolean()) {
            createNewBotGameResult = commandDirector.CreateNewBotGame(true, boardSize);
            playerValidator.addNewGame(threadId, 0, createNewBotGameResult.getGameId());
        } else {
            createNewBotGameResult = commandDirector.CreateNewBotGame(false, boardSize);
            playerValidator.addNewGame(0, threadId, createNewBotGameResult.getGameId());
        }

        currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.SUCCESS)));
        currentClient.completeAction(jsonParser.parseResponseToJson(
                new ResponseDTO(createNewBotGameResult.getChanges(), createNewBotGameResult.getPrisoners())));
    }

    @Override
    protected void handleNotNullGameInfo() {

        currentClient.beginAction(jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.CANT_CREATE_GAME)));
    }
}
