package frontend;

import core.serversender.ServerCommunicator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileButtonActionListener implements ActionListener {

    private static TileButtonActionListener instance = new TileButtonActionListener();

    private ServerCommunicator serverCommunicator;
    private boolean processingAllowed = false;


    private TileButtonActionListener(){}

    public static TileButtonActionListener getInstance() {
        return instance;
    }

    public void setServerCommunicator(ServerCommunicator serverCommunicator){
        this.serverCommunicator = serverCommunicator;
    }

    public void setProcessingAllowed(boolean processingAllowed) {
        this.processingAllowed = processingAllowed;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(processingAllowed) {
            TileButton performer = (TileButton) actionEvent.getSource();
            serverCommunicator.sendMoveMessage(performer.getCoordinates());
        }
    }
}
