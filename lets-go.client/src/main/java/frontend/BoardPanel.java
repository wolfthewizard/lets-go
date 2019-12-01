package frontend;

import core.contract.Coordinates;
import core.contract.enums.BoardSize;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {

    private TileButton[][] tiles;

    public BoardPanel(BoardSize boardSize) {

        super();

        tiles = new TileButton[boardSize.getValue()][boardSize.getValue()];

        setLayout(new GridLayout(tiles[0].length, tiles.length, 0, 0));

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new TileButton(new Coordinates(i, j));
            }
        }

        for (int y = tiles[0].length-1; y >= 0; y--) {
            for (int x = 0; x < tiles.length; x++) {
                this.add(tiles[x][y]);
            }
        }
    }

    public int getDimension() {
        return tiles[0].length * tiles[0][0].getDimension();
    }
}
