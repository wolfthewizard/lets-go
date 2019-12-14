package main;

import contract.Change;
import contract.Prisoners;
import contract.enums.Occupancy;
import core.CommandDirector;
import core.helpers.MoveValidator;
import core.interfaces.ICommandDirector;
import core.model.*;
import contract.enums.BoardSize;
import core.model.enums.MoveResponseType;
import core.services.GameManagerService;
import core.services.MoveExecutorService;
import infrastructure.GameRepository;
import main.helpers.*;
import core.model.Color;

import java.io.IOException;
import java.util.ArrayList;

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
