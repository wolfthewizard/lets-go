package core.helpers;

import static org.junit.jupiter.api.Assertions.*;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MoveValidatorTest {

    private MoveValidator moveValidator;

    private Occupancy[][] board;
    private Occupancy[][] boardTwoTurnsAgo;

    private int boardSizeValue = 9;

    @BeforeEach
    void setup() {

        moveValidator = new MoveValidator();

        boardSizeValue = 9;

        board = new Occupancy[boardSizeValue][];
        for (int i = 0; i < boardSizeValue; i++) {
            board[i] = new Occupancy[boardSizeValue];
            for (int j = 0; j < boardSizeValue; j++) {
                board[i][j] = Occupancy.EMPTY;
            }
        }

        boardTwoTurnsAgo = new Occupancy[boardSizeValue][];
        for (int i = 0; i < boardSizeValue; i++) {
            boardTwoTurnsAgo[i] = new Occupancy[boardSizeValue];
            for (int j = 0; j < boardSizeValue; j++) {
                boardTwoTurnsAgo[i][j] = Occupancy.EMPTY;
            }
        }

        board[1][1] = Occupancy.WHITE;

        boardTwoTurnsAgo[1][1] = Occupancy.WHITE;
    }

    @Test
    public void validateVacancy_returnsFalse_whenMoveCollides() {

        assertFalse(moveValidator.validateVacancy(board, new Coordinates(1, 1)));
    }

    @Test
    public void validateKO_returnsFalse_whenMoveRepeats() {

        assertFalse(moveValidator.validateKO(boardSizeValue, board, boardTwoTurnsAgo));
    }

    @Test
    public void validateSuicide_returnsFalse_whenMoveIsSuicide() {

        List<Change> changes = new ArrayList<>();

        Coordinates cords = new Coordinates(5, 5);
        //todo : change Coordinates comparison in tested method, as it compares references instead of values
        changes.add(new Change(Occupancy.BLACK, cords));
        changes.add(new Change(Occupancy.EMPTY, cords));

        assertFalse(moveValidator.validateSuicide(changes));
    }
}
