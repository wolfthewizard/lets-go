package main;

import contract.Coordinates;
import contract.enums.Occupancy;

import javax.swing.*;

public class TileIcon extends JLabel {

    private final static int ICON_SIZE = 35;

    private Coordinates cords;

    public TileIcon(Coordinates cords) {

        super(IconFlyweight.getIcon(Occupancy.EMPTY));
        this.cords = cords;
        setSize(ICON_SIZE, ICON_SIZE);
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
