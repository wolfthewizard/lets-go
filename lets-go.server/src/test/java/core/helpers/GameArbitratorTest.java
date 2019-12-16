package core.helpers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import contract.Coordinates;
import contract.enums.Occupancy;
import core.interfaces.IMoveHelper;
import core.model.enums.Color;
import core.model.enums.MoveResponseType;
import core.model.enums.Winner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class GameArbitratorTest {

    private GameArbitrator gameArbitrator;

    private Occupancy[][] board;
    private int boardSizeValue;
    private List<Coordinates> chain1;
    private List<Coordinates> chain2;

    @BeforeEach
    void setup() {

        boardSizeValue = 9;

        board = new Occupancy[boardSizeValue][];
        for (int i = 0; i < boardSizeValue; i++) {
            board[i] = new Occupancy[boardSizeValue];
            for (int j = 0; j < boardSizeValue; j++) {
                board[i][j] = Occupancy.EMPTY;
            }
        }

        board[0][0] = Occupancy.BLACK;
        board[0][1] = Occupancy.BLACK;
        board[1][1] = Occupancy.BLACK;
        board[2][1] = Occupancy.BLACK;
        board[3][1] = Occupancy.BLACK;
        board[3][0] = Occupancy.BLACK;

        board[4][4] = Occupancy.WHITE;

        chain1 = new ArrayList<>();
        chain2 = new ArrayList<>();

        IMoveHelper moveHelperMock = Mockito.mock(IMoveHelper.class);

        Coordinates cords10 = new Coordinates(1, 0);
        chain1.add(cords10);
        chain1.add(new Coordinates(2, 0));

        //when(moveHelperMock.chainContains(chain1, any())).thenReturn(true);
        when(moveHelperMock.chainContains(chain1, cords10)).thenReturn(false);

        //when(moveHelperMock.getChainStartingWithCords())
        when(moveHelperMock.getChainStartingWithCords(board, 9, cords10, Occupancy.EMPTY)).thenReturn(chain1);

        gameArbitrator = new GameArbitrator(moveHelperMock);
    }

    @Test
    public void determineWinner_returnsBlack_whenBlackHasMoreTerritory() {

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
