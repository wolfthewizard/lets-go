package main;

import contract.Change;
import contract.Coordinates;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BoardPanelTest {

    private BoardPanel boardPanel;

    @Test
    public void getDimension_returnsDimension_whenSizeIsNine() {

        boardPanel = new BoardPanel(BoardSize.NINE);

        assertEquals(9*35, boardPanel.getDimension());
    }

    @Test
    public void getDimension_returnsDimension_whenSizeIsThirteen() {

        boardPanel = new BoardPanel(BoardSize.THIRTEEN);

        assertEquals(13*35, boardPanel.getDimension());
    }

    @Test
    public void getDimension_returnsDimension_whenSizeIsNineteen() {

        boardPanel = new BoardPanel(BoardSize.NINETEEN);

        assertEquals(19*35, boardPanel.getDimension());
    }

    @Test
    public void enforceChanges_doesNotThrow() {

        boardPanel = new BoardPanel(BoardSize.NINE);

        List<Change> changes = new ArrayList<>();

        changes.add(new Change(Occupancy.BLACK, new Coordinates(1, 1)));
        changes.add(new Change(Occupancy.WHITE, new Coordinates(2, 1)));

        changes.add(new Change(Occupancy.EMPTY, new Coordinates(1, 1)));

        assertDoesNotThrow(() -> boardPanel.enforceChanges(changes));
    }

}
