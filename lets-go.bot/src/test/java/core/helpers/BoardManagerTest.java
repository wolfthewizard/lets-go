package core.helpers;

import contract.Change;
import contract.Coordinates;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BoardManagerTest {

    BoardManager boardManager;
    @BeforeEach
    public void setUp() {
        boardManager = new BoardManager();
    }

    @Test
    public void initializeBoard_DoesNotThrow() {
        assertDoesNotThrow(() -> boardManager.initializeBoard(BoardSize.NINE));
    }

    @Test
    public void initializeBoard_InitializesBoard() {
        boardManager.initializeBoard(BoardSize.NINE);

        assertNotNull(boardManager.getBoard());
        assertEquals(BoardSize.NINE, boardManager.getBoardSize());
    }

    @Test
    public void saveMoves_DoesNotThrow() {

        assertDoesNotThrow(() -> boardManager.saveMoves(new ArrayList<>()));
    }

    @Test
    public void saveMoves_SavesChanges() {
        ArrayList<Change> changes = new ArrayList<>();
        changes.add(new Change(Occupancy.BLACK, new Coordinates(1,1)));

        boardManager.initializeBoard(BoardSize.NINE);
        boardManager.saveMoves(changes);

        assertNotNull(boardManager.getBoard());
        assertEquals(Occupancy.BLACK, boardManager.getBoard()[1][1]);
    }

    @Test
    public void getBoard_ReturnNotNullBoard() {

        boardManager.initializeBoard(BoardSize.NINE);
        Occupancy[][] result = boardManager.getBoard();

        assertNotNull(result);
    }

    @Test
    public void getBoardSize_ReturnCorrectBoardSize() {
        boardManager.initializeBoard(BoardSize.THIRTEEN);

        BoardSize result = boardManager.getBoardSize();

        assertNotNull(result);
        assertEquals(BoardSize.THIRTEEN, result);
    }
}
