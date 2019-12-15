package core.helpers;

import contract.Coordinates;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.interfaces.IMovePerformer;
import core.model.Color;

import java.util.Random;

public class MovePerformer implements IMovePerformer {

    private Random random = new Random();

    @Override
    public Coordinates performMove(Occupancy[][] board, BoardSize boardSize, Color colorOfMovingPlayer) {

        return new Coordinates(random.nextInt(boardSize.getValue()), random.nextInt(boardSize.getValue()));
    }
}
