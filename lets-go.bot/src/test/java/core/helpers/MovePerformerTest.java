package core.helpers;


import contract.Coordinates;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.model.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MovePerformerTest {

    MovePerformer movePerformer;
    @BeforeEach
    public void setUp() {
        movePerformer = new MovePerformer();
    }

    @Test
    public void performMove_forNotrepeated() {

        Occupancy[][] board = new Occupancy[9][];

        for (int i=0; i<9;i++){
            board[i] = new Occupancy[9];
        }

        Coordinates result = movePerformer.performMove(board, BoardSize.NINE, Color.BLACK, false);

        assertTrue(result.getX()<9);
        assertTrue(result.getY()<9);
    }

    @Test
    public void performMove_forRepeated() {

        Occupancy[][] board = new Occupancy[9][];

        for (int i=0; i<9;i++){
            board[i] = new Occupancy[9];
        }

        Coordinates result = movePerformer.performMove(board, BoardSize.NINE, Color.BLACK, true);

        assertTrue(result.getX()<9);
        assertTrue(result.getY()<9);
    }
}