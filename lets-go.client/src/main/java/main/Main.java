package main;

import core.serversender.JsonParser;
import core.serversender.ResponseNumberCounter;
import core.serversender.ServerCommunicator;
import infrastructure.ServerConnector;
import main.windows.GameSettingsWindow;

public class Main {
    public static void main(String[] args) {

        ServerCommunicator.initialize(new JsonParser(), new ResponseNumberCounter(), new ServerConnector());
        new GameSettingsWindow();
    }
}
