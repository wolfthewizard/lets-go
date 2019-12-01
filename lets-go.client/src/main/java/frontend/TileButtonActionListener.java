package frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TileButtonActionListener implements ActionListener {

    private static TileButtonActionListener instance = new TileButtonActionListener();

    private TileButtonActionListener(){}

    public static TileButtonActionListener getInstance() {
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        TileButton performer = (TileButton) actionEvent.getSource();

        System.out.println("My cords are: " + performer.getCoordinates());
        // todo : send a msg to server using the acquired data

        if(new Random().nextBoolean()) {
            performer.setImg(Occupancy.BLACK);
        } else {
            performer.setImg(Occupancy.WHITE);
        }


    }
}
