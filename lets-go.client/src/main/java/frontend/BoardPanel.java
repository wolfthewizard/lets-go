package frontend;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private TileButton[][] tiles;

    public BoardPanel(BoardSize boardSize) {

        switch(boardSize) {
            case NINE:
                tiles = new TileButton[9][9];
                break;
            case THIRTEEN:
                tiles = new TileButton[13][13];
                break;
            case NINETEEN:
                tiles = new TileButton[19][19];
                break;
        }

        setSize(400, 400);
        setLayout(new GridLayout(9, 9, 40, 40));

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new TileButton(i, j);
            }
        }

        for (int y = tiles[0].length-1; y >= 0; y--) {
            for (int x = 0; x < tiles.length; x++) {
                this.add(tiles[x][y]);
            }
        }
    }

}
