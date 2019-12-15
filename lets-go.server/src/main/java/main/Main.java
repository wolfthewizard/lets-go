package main;

import core.CommandDirector;
import core.helpers.GameArbitrator;
import core.helpers.MoveHelper;
import core.helpers.MoveValidator;
import core.interfaces.IGameRepository;
import core.interfaces.IMoveHelper;
import core.services.GameManagerService;
import core.services.MoveExecutorService;
import infrastructure.GameRepository;
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
        IMoveHelper moveHelper = new MoveHelper();
        ServerListenerThread serverListener = new ServerListenerThread(new ActionProcesser(jsonParser,
                new PlayerValidator(),
                new CommandDirector(new GameManagerService(gameRepository), new MoveExecutorService(
                        gameRepository, new MoveValidator(), new GameArbitrator(moveHelper), moveHelper)), clientsManager), clientsManager);

        serverListener.start();

        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().equals("exit")) {
        }
        serverListener.closeConnection();
    }
}
