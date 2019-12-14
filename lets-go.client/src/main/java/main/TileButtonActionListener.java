package main;

import core.serversender.ServerCommunicator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileButtonActionListener implements ActionListener {

    private static TileButtonActionListener instance = new TileButtonActionListener();

    private ServerCommunicator serverCommunicator;


    private TileButtonActionListener(){}

    public static TileButtonActionListener getInstance() {
        return instance;
    }

    public void setServerCommunicator(ServerCommunicator serverCommunicator){
        this.serverCommunicator = serverCommunicator;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        TileButton performer = (TileButton) actionEvent.getSource();
        serverCommunicator.sendMoveMessage(performer.getCoordinates());
    }
}
