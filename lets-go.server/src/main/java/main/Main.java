package main;

import core.CommandDirector;
import core.helpers.GameArbitrator;
import core.helpers.MoveHelper;
import core.helpers.MovePerformer;
import core.helpers.MoveValidator;
import core.interfaces.IGameRepository;
import core.interfaces.IMoveHelper;
import core.services.GameManagerService;
import core.services.MoveExecutorService;
import infrastructure.DBMediationService;
import infrastructure.DBQueryExecutionService;
import infrastructure.EntityMapper;
import infrastructure.GameRepository;
import infrastructure.services.IDBMediationService;
import main.helpers.actionprocesser.ActionProcesser;
import main.helpers.jsonparser.IJsonParser;
import main.helpers.jsonparser.JsonParser;
import main.helpers.playervalidator.PlayerValidator;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        IJsonParser jsonParser = new JsonParser();
        IClientsManager clientsManager = new ClientsManager();
        IGameRepository gameRepository = new GameRepository();
        IDBMediationService dbMediationService = new DBMediationService(new EntityMapper(), new DBQueryExecutionService());
        IMoveHelper moveHelper = new MoveHelper();
        ServerListenerThread serverListenerThread = new ServerListenerThread(new ActionProcesser(jsonParser,
                new PlayerValidator(),
                new CommandDirector(new GameManagerService(gameRepository, dbMediationService), new MoveExecutorService(
                        gameRepository, new MoveValidator(), new GameArbitrator(moveHelper), new MovePerformer(moveHelper))), clientsManager), clientsManager);

        serverListenerThread.start();

        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().equals("exit"));
        serverListenerThread.closeConnection();
    }
}
