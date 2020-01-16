package main;

import contract.Change;
import contract.Coordinates;
import contract.enums.BoardSize;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RewindPanel extends JPanel {

    private TileIcon tiles[][];

    public RewindPanel(BoardSize boardSize) {

        super();

        tiles = new TileIcon[boardSize.getValue()][boardSize.getValue()];

        setLayout(new GridLayout(tiles[0].length, tiles.length, 0, 0));

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                tiles[i][j] = new TileIcon(new Coordinates(i, j));
            }
        }

        for (int y = tiles[0].length-1; y >= 0; y--) {
            for (TileIcon[] tile : tiles) {
                this.add(tile[y]);
            }
        }
    }

    public int getDimension() {
        return tiles[0].length * tiles[0][0].getDimension();
    }

    public void enforceChanges(List<Change> changes) {

        for(Change change : changes) {
            tiles[change.getCoordinates().getX()][change.getCoordinates().getY()]
                    .setImg(change.getOccupancy());
        }
    }
}
