package main.actionlisteners;

import core.serversender.ServerCommunicator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassButtonActionListener implements ActionListener {

    private static PassButtonActionListener instance = new PassButtonActionListener();

    private ServerCommunicator serverCommunicator;

    private PassButtonActionListener(){}

    public static PassButtonActionListener getInstance(){
        return instance;
    }

    public void setServerCommunicator(ServerCommunicator serverCommunicator) {
        this.serverCommunicator = serverCommunicator;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        serverCommunicator.sendMovePassMessage();
    }
}
