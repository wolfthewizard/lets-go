package frontend;

import javax.swing.*;

public class TileButton extends JButton {

    private int x;
    private int y;

    public TileButton(int x, int y) {
        super();
        this.x = x;
        this.y = y;
        setSize(35, 35);
        //this.setIcon(IconFlyweight.getIcon(Occupancy.EMPTY));
        this.setIcon(new ImageIcon("/home/mateusz/Dokumenty/tp/lets-go/lets-go.client/src/main/java/frontend/images/tileEmpty.png"));   //todo remove absoulte path to non static icon
        this.addActionListener(TileButtonActionListener.getInstance());

//        setBorder(BorderFactory.createEmptyBorder());
//        setContentAreaFilled(false);
    }

    public void setImg(Occupancy type) {
        this.setIcon(IconFlyweight.getIcon(type));
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}
