package core.helpers;

import contract.enums.ResponseType;
import core.interfaces.ICommunicatorSender;
import core.interfaces.IEndOfGameHandler;

public class EndOfGameHandler implements IEndOfGameHandler {

    private final ICommunicatorSender communicatorSender;

    public EndOfGameHandler(ICommunicatorSender communicatorSender){
        this.communicatorSender = communicatorSender;
    }

    public void handleGameEnd(ResponseType endType) {

        switch (endType){
            case GAMEWON:
                System.out.println("Ha ! I won. As always ;)");
                break;
            case GAMELOST:
                System.out.println("Sorry boss :( He was better");
                break;
            case TIE:
                System.out.println("Hope you know how to tie a tie");
                break;
            case PLAYER_LEFT:
                System.out.println("He gave up. He couldn't withstand my brilliant moves ;)");
                break;
            case SERVER_ERROR:
                System.out.println("Server broke down :/");
                break;
            case CANT_CREATE_GAME:
                System.out.println("I can't play ! Why ?!");
                break;
            default:
                System.out.println("Somthing broke...");
                break;
        }

        communicatorSender.sendLeaveGameMessage();
        System.exit(0);
    }
}
