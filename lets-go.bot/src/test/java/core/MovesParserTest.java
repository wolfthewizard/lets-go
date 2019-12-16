package core;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import contract.Change;
import contract.Coordinates;
import contract.enums.Occupancy;
import contract.enums.ResponseType;
import core.interfaces.IBoardManager;
import core.interfaces.ICommunicatorSender;
import core.interfaces.ITurnExecutor;
import core.model.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

public class MovesParserTest {

    private MovesParser movesParser;
    private IBoardManager boardManager;
    private ITurnExecutor turnExecutor;

    @BeforeEach
    public void setUp(){

        boardManager = Mockito.mock(IBoardManager.class);
        turnExecutor = Mockito.mock(ITurnExecutor.class);

        movesParser = new MovesParser(boardManager, turnExecutor);
    }

    @Test
   public void parseMoves_SetsColorToBlack_ForFirstEmptyMove() {

        movesParser.parseMoves(new ArrayList<>());

        verify(turnExecutor, times(1)).setMyColor(Color.BLACK);
    }

    @Test
    public void parseMoves_SetsColorToWhite_ForFirstNonEmptyMove() {

        ArrayList<Change> changes = new ArrayList<>();
        changes.add(new Change(Occupancy.BLACK, new Coordinates(1,2)));
        movesParser.parseMoves(changes);

        verify(turnExecutor, times(1)).setMyColor(Color.WHITE);
    }

    @Test
    public void parseMoves_DoesNotSetColor_ForSecondCall() {

        ArrayList<Change> changes = new ArrayList<>();
        changes.add(new Change(Occupancy.BLACK, new Coordinates(1,2)));
        movesParser.parseMoves(changes);
        movesParser.parseMoves(new ArrayList<>());

        verify(turnExecutor, times(1)).setMyColor(Color.WHITE);
    }

    @Test
    public void parseMoves_CallsExecuteTurnTwice_WhenBotStarts() {

        ArrayList<Change> changes = new ArrayList<>();
        changes.add(new Change(Occupancy.BLACK, new Coordinates(1,2)));
        movesParser.parseMoves(changes);
        movesParser.parseMoves(new ArrayList<>());
        movesParser.parseMoves(new ArrayList<>());

        verify(turnExecutor, times(2)).executeTurn();
    }

    @Test
    public void parseMoves_CallsExecuteTurnTwice_WhenEnemyStarts() {

        movesParser.parseMoves(new ArrayList<>());
        movesParser.parseMoves(new ArrayList<>());
        movesParser.parseMoves(new ArrayList<>());

        verify(turnExecutor, times(2)).executeTurn();
    }

    @Test
    public void parseMoves_CallsSaveMove_EveryTime() {

        movesParser.parseMoves(new ArrayList<>());
        movesParser.parseMoves(new ArrayList<>());

        verify(boardManager, times(2)).saveMoves(new ArrayList<>());
    }
}
