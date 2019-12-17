package core.helpers;

import contract.Coordinates;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.interfaces.IMovePerformer;
import core.model.Color;

import java.util.Random;

public class MovePerformer implements IMovePerformer {

    private Random random = new Random();
    private boolean lastReturnedRand = false;

    @Override
    public Coordinates performMove(Occupancy[][] board, BoardSize boardSize, Color colorOfMovingPlayer,
                                   boolean lastMoveInvalid) {

        Coordinates returnCords;
        returnCords = iterateOverBoard(board, boardSize, colorOfMovingPlayer, false, lastMoveInvalid);
        if (returnCords != null) {
            return returnCords;
        }
        returnCords = iterateOverBoard(board, boardSize, colorOfMovingPlayer, true, lastMoveInvalid);
        if (returnCords != null) {
            return returnCords;
        }

        if (lastMoveInvalid && lastReturnedRand) {
            lastReturnedRand = false;
            return null;
        }
        lastReturnedRand = true;

        return new Coordinates(random.nextInt(boardSize.getValue()), random.nextInt(boardSize.getValue()));
    }

    private Coordinates iterateOverBoard(Occupancy[][] board, BoardSize boardSize, Color colorOfMovingPlayer,
                                         boolean returnAny, boolean lastMoveInvalid) {
        for (int i = 0; i < boardSize.getValue(); i++) {
            for (int j = 0; j < boardSize.getValue(); j++) {
                if (board[i][j] == colorOfMovingPlayer.reverse().toOccupancy()) {
                    Coordinates cords = checkIfEnemyHasOneBreath(new Coordinates(i, j), board, boardSize, returnAny);
                    if (cords != null && !lastMoveInvalid) {
                        lastReturnedRand = false;
                        return cords;
                    }
                }
            }
        }

        return null;
    }

    private Coordinates checkIfEnemyHasOneBreath(Coordinates coordinates, Occupancy[][] board,
                                                 BoardSize boardSize, boolean returnAny) {
        int breathCount = 0;
        Coordinates lastCords = null;

        if (coordinates.getY() + 1 < boardSize.getValue() &&
                board[coordinates.getX()][coordinates.getY() + 1] == Occupancy.EMPTY) {
            breathCount++;
            lastCords = new Coordinates(coordinates.getX(), coordinates.getY() + 1);
            if (returnAny) {
                return lastCords;
            }
        }
        if (coordinates.getX() + 1 < boardSize.getValue() &&
                board[coordinates.getX() + 1][coordinates.getY()] == Occupancy.EMPTY) {
            breathCount++;
            lastCords = new Coordinates(coordinates.getX() + 1, coordinates.getY());
            if (returnAny) {
                return lastCords;
            }
        }
        if (coordinates.getY() - 1 >= 0 &&
                board[coordinates.getX()][coordinates.getY() - 1] == Occupancy.EMPTY) {
            breathCount++;
            lastCords = new Coordinates(coordinates.getX(), coordinates.getY() - 1);
            if (returnAny) {
                return lastCords;
            }
        }
        if (coordinates.getX() - 1 >= 0 &&
                board[coordinates.getX() - 1][coordinates.getY()] == Occupancy.EMPTY) {
            breathCount++;
            lastCords = new Coordinates(coordinates.getX() - 1, coordinates.getY());
            if (returnAny) {
                return lastCords;
            }
        }

        if (breathCount == 1) {
            return lastCords;
        }

        return null;
    }
}
