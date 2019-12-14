package main;

import core.CommandDirector;
import core.helpers.MoveValidator;
import core.services.GameManagerService;
import core.services.MoveExecutorService;
import infrastructure.GameRepository;
import main.helpers.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        IJsonParser jsonParser = new JsonParser();
        IClientsManager clientsManager = new ClientsManager();
        GameRepository gameRepository = new GameRepository();
        ServerListener serverCommunicator = new ServerListener(new ActionProcesser(jsonParser,
                new PlayerValidator(),
                new CommandDirector(new GameManagerService(gameRepository), new MoveExecutorService(gameRepository, new MoveValidator())), clientsManager),
                new IdGenerator(), clientsManager);
    }
}
