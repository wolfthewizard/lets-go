package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TileButtonActionListener implements ActionListener {

    private static TileButtonActionListener instance = new TileButtonActionListener();

    private TileButtonActionListener(){}

    public static TileButtonActionListener getInstance() {
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        TileButton performer = (TileButton) actionEvent.getSource();
        StringBuilder sb = new StringBuilder();

        // todo : send a msg to server using the acquired data
    }
}
