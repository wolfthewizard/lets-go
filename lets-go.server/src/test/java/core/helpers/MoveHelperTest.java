package core.helpers;

import static org.junit.jupiter.api.Assertions.*;

import contract.Coordinates;
import contract.enums.Occupancy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MoveHelperTest {

    private MoveHelper moveHelper;
    private Occupancy[][] board;
    List<Coordinates> chain1;
    List<Coordinates> chain2;

    @BeforeEach
    void setup() {

        moveHelper = new MoveHelper();

        board = new Occupancy[9][];
        for (int i = 0; i < 9; i++) {
            board[i] = new Occupancy[9];
            for (int j = 0; j < 9; j++) {
                board[i][j] = Occupancy.EMPTY;
            }
        }

        chain1 = new ArrayList<>();
        chain2 = new ArrayList<>();

        board[0][0] = Occupancy.BLACK;
        board[0][1] = Occupancy.BLACK;
        board[1][1] = Occupancy.BLACK;
        board[2][1] = Occupancy.BLACK;
        board[3][1] = Occupancy.BLACK;
        board[3][0] = Occupancy.BLACK;

        board[1][0] = Occupancy.WHITE;
        board[2][0] = Occupancy.WHITE;


        chain1.add(new Coordinates(0, 0));
        chain1.add(new Coordinates(0, 1));
        chain1.add(new Coordinates(1, 1));
        chain1.add(new Coordinates(2, 1));
        chain1.add(new Coordinates(3, 1));
        chain1.add(new Coordinates(3, 0));

        chain2.add(new Coordinates(1, 0));
        chain2.add(new Coordinates(2, 0));
    }

    @Test
    public void getChainStartingWithCords_returnsSixElementChain_whenChainIsFormed() {

        assertEquals(moveHelper.getChainStartingWithCords(board, 9, new Coordinates(0, 0), Occupancy.BLACK).size(), 6);
    }

    @Test
    public void chainContains_returnsTrue_whenElementIsInChain() {

        assertTrue(moveHelper.chainContains(chain2, new Coordinates(2, 0)));
    }

    @Test
    public void doesChainBorderWith_returnsFalse_whenItDoesnt() {

        assertFalse(moveHelper.doesChainBorderWith(board, 9, chain2, Occupancy.EMPTY));
    }

    @Test
    public void doesChainBorderWith_returnsTrue_whenItDoes() {

        assertTrue(moveHelper.doesChainBorderWith(board, 9, chain2, Occupancy.BLACK));
    }

    @Test
    public void isChainWithoutBreaths_returnsTrue_whenItIsSurrounded() {

        assertTrue(moveHelper.isChainWithoutBreaths(board, 9, chain2));
    }

    @Test
    public void isChainWithoutBreaths_returnsFalse_whenItHasABreath() {

        assertFalse(moveHelper.isChainWithoutBreaths(board, 9, chain1));
    }

    @Test
    public void getNeighbouringCords_returnsTwoElementList_whenStoneHas2Neighbours() {

        assertEquals(moveHelper.getNeighbouringCords(board, 9, new Coordinates(0, 1), Occupancy.BLACK).size(), 2);
    }
}
