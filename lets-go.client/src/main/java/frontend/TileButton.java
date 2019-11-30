package frontend;

import javax.swing.*;

public class TileButton extends JButton {

    private static int ICON_SIZE = 35;

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

    public int getXCoordinate() {
        return cords.getX();
    }

    public int getYCoordinate() {
        return cords.getY();
    }

    public int getDimension() {
        return ICON_SIZE;
    }
}
