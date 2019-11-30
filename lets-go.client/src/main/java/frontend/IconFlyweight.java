package frontend;

import javax.swing.*;

public class IconFlyweight {
    //todo create images and specify relative path to them
    private static ImageIcon emptyTile = new ImageIcon("/home/mateusz/Dokumenty/tp/lets-go/lets-go.client/src/main/java/frontend/images/tileEmpty.png");    //todo remove absoolute pathss
    private static ImageIcon blackTile = new ImageIcon("/home/mateusz/Dokumenty/tp/lets-go/lets-go.client/src/main/java/frontend/images/tileBlack.png");
    //private static ImageIcon whiteTile = new ImageIcon(IconFlyweight.class.getResource("images/tileWhite.png"));
    private static ImageIcon whiteTile = new ImageIcon("/home/mateusz/Dokumenty/tp/lets-go/lets-go.client/src/main/java/frontend/images/tileWhite.png");

    private IconFlyweight(){}

    public static ImageIcon getIcon(Occupancy type) {
        switch(type) {
            case BLACK:
                return blackTile;
            case WHITE:
                return whiteTile;
            case EMPTY:
                return emptyTile;
            default:
                return null;
        }
    }
}
