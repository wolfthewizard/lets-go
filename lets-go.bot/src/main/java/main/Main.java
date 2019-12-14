package main;

import core.*;
import core.helpers.BoardManager;
import core.helpers.EndOfGameHandler;
import core.helpers.JsonParser;
import core.helpers.MovePerformer;
import core.interfaces.IGameInitializer;
import core.interfaces.ITurnExecutor;
import infrastructure.ServerConnector;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ServerCommunicator serverCommunicator = new ServerCommunicator(new ServerConnector(), new JsonParser());
        BoardManager boardManager = new BoardManager();
        ITurnExecutor turnExecutor = new TurnExecutor(serverCommunicator, new MovePerformer(), boardManager);
        ServerResponseReceiver serverResponseReceiver = new ServerResponseReceiver(new MovesParser(boardManager, turnExecutor), new EndOfGameHandler(serverCommunicator), turnExecutor);
        serverCommunicator.setServerResponseReceiver(serverResponseReceiver);
        IGameInitializer gameInitializer = new GameInitializer(serverCommunicator, boardManager);

        switch (args[0]) {
            case "-help":
                System.out.println("-small to enable a bot for 9x9 board");
                System.out.println("-medium to enable a bot for 13x13 board");
                System.out.println("-hard to enable a bot for 19x19 board");
                break;
            case "-small":
                gameInitializer.StartSmallBoardGame();
                break;
            case "-medium":
                gameInitializer.StartMediumBoardGame();
                break;
            case "-large":
                gameInitializer.StartLargeBoardGame();
                break;
        }

        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().equals("exit")) {
        }
        serverCommunicator.sendLeaveGameMessage();
    }
}
