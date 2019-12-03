package frontend;


import contract.Change;
import contract.Coordinates;
import contract.enums.BoardSize;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
            for (TileButton[] tile : tiles) {
                this.add(tile[y]);
            }
        }
    }

    public int getDimension() {
        return tiles[0].length * tiles[0][0].getDimension();
    }

    public void enforceChanges(ArrayList<Change> changes) {

        for(Change change : changes) {
            tiles[change.getCoordinates().getX()][change.getCoordinates().getY()]
                    .setImg(change.getOccupancy());
        }
    }
}
