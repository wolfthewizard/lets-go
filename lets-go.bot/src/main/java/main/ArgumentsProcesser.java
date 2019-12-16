package main;

import core.interfaces.IGameInitializer;

public class ArgumentsProcesser implements IArgumentProcesser {

    private final IGameInitializer gameInitializer;

    public ArgumentsProcesser(IGameInitializer gameInitializer){
        this.gameInitializer = gameInitializer;
    }

    public void processArgument(String argument){
        switch (argument) {
            case "-help":
                System.out.println("-small to enable a bot for 9x9 board");
                System.out.println("-medium to enable a bot for 13x13 board");
                System.out.println("-hard to enable a bot for 19x19 board");
                break;
            case "-small":
                gameInitializer.startSmallBoardGame();
                break;
            case "-medium":
                gameInitializer.startMediumBoardGame();
                break;
            case "-large":
                gameInitializer.startLargeBoardGame();
                break;
        }

    }

}
