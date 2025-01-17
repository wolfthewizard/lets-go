package main;


import contract.Coordinates;
import contract.enums.Occupancy;
import main.actionlisteners.TileButtonActionListener;

import javax.swing.*;

public class TileButton extends JButton {

    private final static int ICON_SIZE = 35;

    private Coordinates cords;

    public TileButton(Coordinates cords) {
        super(IconFlyweight.getIcon(Occupancy.EMPTY));
        this.cords = cords;
        setSize(ICON_SIZE, ICON_SIZE);
        this.addActionListener(TileButtonActionListener.getInstance());

        setBorder(BorderFactory.createEmptyBorder());
        setContentAreaFilled(false);
    }

    public void setImg(Occupancy type) {
        this.setIcon(IconFlyweight.getIcon(type));
    }

    public Coordinates getCoordinates() {
        return cords;
    }

    public int getDimension() {
        return ICON_SIZE;
    }
}
