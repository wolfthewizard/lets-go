package main;


import contract.enums.Occupancy;

import javax.swing.*;
import java.io.File;

public class IconFlyweight {

    private static ImageIcon emptyTile = new ImageIcon(new File("images/tileEmpty.png").getAbsolutePath());
    private static ImageIcon blackTile = new ImageIcon(new File("images/tileBlack.png").getAbsolutePath());
    private static ImageIcon whiteTile = new ImageIcon(new File("images/tileWhite.png").getAbsolutePath());

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
