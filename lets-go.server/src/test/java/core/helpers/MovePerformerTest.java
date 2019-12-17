package core.helpers;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;
import core.model.enums.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovePerformerTest {

    private MovePerformer movePerformer;

    Occupancy[][] potentialState;

    @BeforeEach
    void setup() {

        movePerformer = new MovePerformer(new MoveHelper());

        potentialState = new Occupancy[9][];
        for (int i = 0; i < 9; i++) {
            potentialState[i] = new Occupancy[9];
            for (int j = 0; j < 9; j++) {
                potentialState[i][j] = Occupancy.EMPTY;
            }
        }
    }

    @Test
    public void performMove_returnsCorrectNumberOfPrisoners_whenBlackDefeats() {

        potentialState[0][0] = Occupancy.WHITE;
        potentialState[1][0] = Occupancy.BLACK;

        Coordinates moveCoordinates = new Coordinates(0, 1);
        Color playerColor = Color.BLACK;

        List<Change> changes = new ArrayList<>();

        int nofPrisoners = movePerformer.performMove(moveCoordinates, playerColor, potentialState, 9, changes);

        assertEquals(1, nofPrisoners);
        assertEquals(2, changes.size());
    }

    @Test
    public void performMove_returnsCorrectNumberOfPrisoners_whenBlackIsDefeated() {

        potentialState[1][0] = Occupancy.WHITE;
        potentialState[0][1] = Occupancy.WHITE;

        Coordinates moveCoordinates = new Coordinates(0, 0);
        Color playerColor = Color.BLACK;

        List<Change> changes = new ArrayList<>();

        int nofPrisoners = movePerformer.performMove(moveCoordinates, playerColor, potentialState, 9, changes);

        assertEquals(-1, nofPrisoners);
        assertEquals(2, changes.size());
    }
}
