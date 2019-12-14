package main;

import core.GameInitializer;
import core.interfaces.IGameInitializer;

public class Main {

    public static void main(String[] args) {

        IGameInitializer gameInitializer = new GameInitializer();
        switch (args[0]){
            case "-help":
                System.out.println("-small to enable a bot for 9x9 board");
                System.out.println("-small to enable a bot for 13x13 board");
                System.out.println("-small to enable a bot for 19x19 board");
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
    }
}
