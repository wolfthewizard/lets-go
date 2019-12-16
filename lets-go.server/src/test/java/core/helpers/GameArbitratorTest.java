package core.helpers;

import static org.junit.jupiter.api.Assertions.*;

import contract.Coordinates;
import contract.enums.Occupancy;
import core.model.enums.Color;
import core.model.enums.MoveResponseType;
import core.model.enums.Winner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class GameArbitratorTest {

    private GameArbitrator gameArbitrator;

    private Occupancy[][] board1;
    private Occupancy[][] board2;
    private Occupancy[][] board3;

    private int boardSizeValue;
    private List<Coordinates> chain1;
    private List<Coordinates> chain2;

    @BeforeEach
    void setup() {

        boardSizeValue = 9;

        board1 = new Occupancy[boardSizeValue][];
        for (int i = 0; i < boardSizeValue; i++) {
            board1[i] = new Occupancy[boardSizeValue];
            for (int j = 0; j < boardSizeValue; j++) {
                board1[i][j] = Occupancy.EMPTY;
            }
        }

        board1[0][0] = Occupancy.BLACK;
        board1[0][1] = Occupancy.BLACK;
        board1[1][1] = Occupancy.BLACK;
        board1[2][1] = Occupancy.BLACK;
        board1[3][1] = Occupancy.BLACK;
        board1[3][0] = Occupancy.BLACK;

        board1[4][4] = Occupancy.WHITE;

        board2 = new Occupancy[boardSizeValue][];
        for (int i = 0; i < boardSizeValue; i++) {
            board2[i] = new Occupancy[boardSizeValue];
            for (int j = 0; j < boardSizeValue; j++) {
                board2[i][j] = Occupancy.EMPTY;
            }
        }

        board2[0][0] = Occupancy.WHITE;
        board2[0][1] = Occupancy.WHITE;
        board2[0][2] = Occupancy.WHITE;
        board2[1][2] = Occupancy.WHITE;
        board2[2][2] = Occupancy.WHITE;
        board2[2][1] = Occupancy.WHITE;
        board2[2][0] = Occupancy.WHITE;
        board2[1][0] = Occupancy.WHITE;

        board2[4][4] = Occupancy.BLACK;

        board3 = new Occupancy[boardSizeValue][];
        for (int i = 0; i < boardSizeValue; i++) {
            board3[i] = new Occupancy[boardSizeValue];
            for (int j = 0; j < boardSizeValue; j++) {
                board3[i][j] = Occupancy.EMPTY;
            }
        }

        gameArbitrator = new GameArbitrator(new MoveHelper());
    }

    @Test
    public void determineWinner_returnsBlack_whenBlackHasMoreTerritory() {

        Winner winner = gameArbitrator.determineWinner(board1, 9);
        assertEquals(Winner.BLACK, winner);
    }

    @Test
    public void determineWinner_returnsBlack_whenWhiteHasMoreTerritory() {

        Winner winner = gameArbitrator.determineWinner(board2, 9);
        assertEquals(Winner.WHITE, winner);
    }

    @Test
    public void determineWinner_returnsBlack_whenItsTie() {

        Winner winner = gameArbitrator.determineWinner(board3, 9);
        assertEquals(Winner.TIE, winner);
    }

    @Test
    public void toMoveResponseType_returnsCurrentPlayerWon_whenBlackHasWonAndMoved() {

        assertEquals(gameArbitrator.toMoveResponseType(Winner.BLACK, Color.BLACK), MoveResponseType.CURRENT_PLAYER_WON);
    }

    @Test
    public void toMoveResponseType_returnsOtherPlayerWon_whenBlackHasWonAndWhiteMoved() {

        assertEquals(gameArbitrator.toMoveResponseType(Winner.BLACK, Color.WHITE), MoveResponseType.OTHER_PLAYER_WON);
    }

    @Test
    public void toMoveResponseType_returnsCurrentPlayerWon_whenWhiteHasWonAndBlackMoved() {

        assertEquals(gameArbitrator.toMoveResponseType(Winner.WHITE, Color.BLACK), MoveResponseType.OTHER_PLAYER_WON);
    }

    @Test
    public void toMoveResponseType_returnsOtherPlayerWon_whenWhiteHasWonAndMoved() {

        assertEquals(gameArbitrator.toMoveResponseType(Winner.WHITE, Color.WHITE), MoveResponseType.CURRENT_PLAYER_WON);
    }

    @Test
    public void toMoveResponseType_returnsTie_whenItsTie() {

        assertEquals(MoveResponseType.TIE, gameArbitrator.toMoveResponseType(Winner.TIE, Color.BLACK));
    }
}
