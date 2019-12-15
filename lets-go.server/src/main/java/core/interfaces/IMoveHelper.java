package core.interfaces;

import contract.Coordinates;
import contract.enums.Occupancy;

import java.util.List;

public interface IMoveHelper {

    List<Coordinates> getChainStartingWithCords(Occupancy[][] board, int boardSizeValue, Coordinates startingCords, Occupancy occupancy);
    List<Coordinates> getNeighbouringCords(Occupancy[][] board, int boardSizeValue, Coordinates coordinates, Occupancy occupancy);

    boolean chainContains(List<Coordinates> chain, Coordinates coordinates);
    boolean doesChainBorderWith(Occupancy[][] board, int boardSizeValue, List<Coordinates> chain, Occupancy occupancy);
    boolean isChainWithoutBreaths(Occupancy[][] board, int boardSizeValue, List<Coordinates> chain);
}
