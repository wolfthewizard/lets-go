package main.helpers.actionhandlers;

import core.interfaces.ICommandDirector;
import main.ClientConnectionThread;
import main.helpers.jsonparser.IJsonParser;
import main.model.GameInfo;

public abstract class AbstractActionHandler {

    protected GameInfo gameInfo;
    protected ClientConnectionThread currentClient;
    protected IJsonParser jsonParser;
    protected ICommandDirector commandDirector;

    protected AbstractActionHandler(GameInfo gameInfo, ClientConnectionThread currentClient,
                                    IJsonParser jsonParser, ICommandDirector commandDirector) {

        this.gameInfo = gameInfo;
        this.currentClient = currentClient;
        this.jsonParser = jsonParser;
        this.commandDirector = commandDirector;
    }

    public void HandleAction() {
        if (gameInfo == null) {

            handleNullGameInfo();

            return;
        }

        handleNotNullGameInfo();
    }

    protected abstract void handleNullGameInfo();
    protected abstract void handleNotNullGameInfo();
}
