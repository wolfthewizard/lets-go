package frontend;

import core.serversender.ServerCommunicator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassButtonActionListener implements ActionListener {

    private static PassButtonActionListener instance = new PassButtonActionListener();

    private ServerCommunicator serverCommunicator;
    private boolean processingAllowed;

    private PassButtonActionListener(){}

    public static PassButtonActionListener getInstance(){
        return instance;
    }

    public void setServerCommunicator(ServerCommunicator serverCommunicator) {
        this.serverCommunicator = serverCommunicator;
    }

    public void setProcessingAllowed(boolean processingAllowed) {
        this.processingAllowed = processingAllowed;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(processingAllowed) {
            serverCommunicator.sendMovePassMessage();
        }
    }
}
