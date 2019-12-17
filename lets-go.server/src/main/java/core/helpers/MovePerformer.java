package core.helpers;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;
import core.interfaces.IMoveHelper;
import core.interfaces.IMovePerformer;
import core.model.enums.Color;

import java.util.List;

public class MovePerformer implements IMovePerformer {

    private IMoveHelper moveHelper;

    public MovePerformer(IMoveHelper moveHelper) {
        this.moveHelper = moveHelper;
    }

    @Override
    public int performMove(Coordinates moveCoordinates, Color playerColor, Occupancy[][] potentialState, int boardSizeValue, List<Change> changes) {

        int killedEnemies = 0;

        //1st phase
        changeBoard(potentialState, changes, moveCoordinates, playerColor.toOccupancy());

        //2nd phase
        List<Coordinates> enemiesSurrounding = moveHelper.getNeighbouringCords(potentialState, boardSizeValue, moveCoordinates, playerColor.reverse().toOccupancy());

        for (Coordinates enemy : enemiesSurrounding) {

            List<Coordinates> chain = moveHelper.getChainStartingWithCords(potentialState, boardSizeValue, enemy, playerColor.reverse().toOccupancy());

            if (moveHelper.isChainWithoutBreaths(potentialState, boardSizeValue, chain)) {

                killedEnemies = removeChainFromBoard(potentialState, changes, chain);
            }
        }

        if (killedEnemies != 0) {

            return killedEnemies;
        }

        //3rd phase
        List<Coordinates> chain = moveHelper.getChainStartingWithCords(potentialState, boardSizeValue, moveCoordinates, playerColor.toOccupancy());

        if (moveHelper.isChainWithoutBreaths(potentialState, boardSizeValue, chain)) {

            killedEnemies = -removeChainFromBoard(potentialState, changes, chain);
        }

        return killedEnemies;
    }

    private int removeChainFromBoard(Occupancy[][] potentialState, List<Change> changes, List<Coordinates> chain) {

        int amountOfRemovedStones = 0;

        for (Coordinates coordinates : chain) {

            changeBoard(potentialState, changes, coordinates, Occupancy.EMPTY);
            amountOfRemovedStones++;
        }

        return amountOfRemovedStones;
    }

    private void changeBoard(Occupancy[][] potentialState, List<Change> changes, Coordinates coordinates, Occupancy occupancy) {

        potentialState[coordinates.getX()][coordinates.getY()] = occupancy;
        changes.add(new Change(occupancy, coordinates));
    }
}
