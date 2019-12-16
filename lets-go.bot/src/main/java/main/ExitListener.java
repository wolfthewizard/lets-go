package main;

import core.interfaces.ICommunicatorSender;

import java.util.Scanner;

public class ExitListener implements IExitListener {

    private final ICommunicatorSender communicatorSender;
    private final Scanner scanner;

    public ExitListener(ICommunicatorSender communicatorSender, Scanner scanner){
        this.communicatorSender = communicatorSender;
        this.scanner = scanner;
    }

    public void waitForExit(){
        while (!scanner.nextLine().equals("exit")) {
        }
        communicatorSender.sendLeaveGameMessage();
    }
}
