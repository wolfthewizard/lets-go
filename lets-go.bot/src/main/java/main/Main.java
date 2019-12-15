package main;

import core.*;
import core.helpers.BoardManager;
import core.helpers.EndOfGameHandler;
import core.helpers.JsonParser;
import core.helpers.MovePerformer;
import core.interfaces.IGameInitializer;
import core.interfaces.ITurnExecutor;
import infrastructure.ServerConnector;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ServerCommunicator serverCommunicator = new ServerCommunicator(new ServerConnector(), new JsonParser());
        BoardManager boardManager = new BoardManager();
        ITurnExecutor turnExecutor = new TurnExecutor(serverCommunicator, new MovePerformer(), boardManager);
        ServerResponseReceiver serverResponseReceiver = new ServerResponseReceiver(new MovesParser(boardManager, turnExecutor), new EndOfGameHandler(serverCommunicator), turnExecutor);
        serverCommunicator.setServerResponseReceiver(serverResponseReceiver);
        IGameInitializer gameInitializer = new GameInitializer(serverCommunicator, boardManager);
        IArgumentProcesser argumentProcesser = new ArgumentsProcesser(gameInitializer);

        argumentProcesser.processArgument(args[0]);

        IExitListener exitListener = new ExitListener(serverCommunicator, new Scanner(System.in));
        exitListener.waitForExit();

    }
}
