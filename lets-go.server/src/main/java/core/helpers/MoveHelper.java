package core.helpers;

import contract.Coordinates;
import contract.enums.Occupancy;
import core.interfaces.IMoveHelper;

import java.util.ArrayList;
import java.util.List;

public class MoveHelper implements IMoveHelper {

    @Override
    public List<Coordinates> getChainStartingWithCords(Occupancy[][] board, int boardSizeValue, Coordinates startingCords, Occupancy occupancy) {

        List<Coordinates> chain = new ArrayList<>();

        if (board[startingCords.getX()][startingCords.getY()] != occupancy) {
            return chain;
        }

        chain.add(startingCords);

        for (Coordinates cords : getNeighbouringCords(board, boardSizeValue, startingCords, occupancy)) {
            chainBuildingRecursive(board, boardSizeValue, cords, occupancy, chain);
        }

        return chain;
    }

    @Override
    public boolean chainContains(List<Coordinates> chain, Coordinates coordinates) {

        for (Coordinates cords : chain) {

            if (cords.getX() == coordinates.getX() && cords.getY() == coordinates.getY()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean doesChainBorderWith(Occupancy[][] board, int boardSizeValue, List<Coordinates> chain, Occupancy occupancy) {

        for (Coordinates coordinates : chain) {

            if (!getNeighbouringCords(board, boardSizeValue, coordinates, occupancy).isEmpty()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isChainWithoutBreaths(Occupancy[][] board, int boardSizeValue, List<Coordinates> chain) {

        return !doesChainBorderWith(board, boardSizeValue, chain, Occupancy.EMPTY);
    }

    @Override
    public List<Coordinates> getNeighbouringCords(Occupancy[][] board, int boardSizeValueValue, Coordinates coordinates, Occupancy occupancy) {

        List<Coordinates> neighbours = new ArrayList<>();

        int x = coordinates.getX();
        int y = coordinates.getY();

        if (x - 1 >= 0) {

            if (board[x - 1][y] == occupancy) {
                neighbours.add(new Coordinates(x - 1, y));
            }
        }

        if (x + 1 < boardSizeValueValue) {

            if (board[x + 1][y] == occupancy) {
                neighbours.add(new Coordinates(x + 1, y));
            }
        }

        if (y - 1 >= 0) {

            if (board[x][y - 1] == occupancy) {
                neighbours.add(new Coordinates(x, y - 1));
            }
        }

        if (y + 1 < boardSizeValueValue) {

            if (board[x][y + 1] == occupancy) {
                neighbours.add(new Coordinates(x, y + 1));
            }
        }

        return neighbours;
    }

    private void chainBuildingRecursive(Occupancy[][] board, int boardSizeValue, Coordinates coordinates, Occupancy occupancy, List<Coordinates> chain) {

        if (!chainContains(chain, coordinates)) {
            chain.add(coordinates);

            for (Coordinates cords : getNeighbouringCords(board, boardSizeValue, coordinates, occupancy)) {
                chainBuildingRecursive(board, boardSizeValue, cords, occupancy, chain);
            }
        }
    }
}
