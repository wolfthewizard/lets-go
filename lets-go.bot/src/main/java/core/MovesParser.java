package core;

import contract.Change;
import contract.enums.Occupancy;
import core.interfaces.IBoardManager;
import core.interfaces.IMovePerformer;
import core.interfaces.IMovesParser;
import core.interfaces.ITurnExecutor;
import core.model.Color;

import java.util.ArrayList;

public class MovesParser implements IMovesParser {

    private final IBoardManager boardManager;
    private final ITurnExecutor turnExecutor;
    private boolean firstMove = true;
    private boolean isMyMove = false;
    private Color myColor;

    public MovesParser(IBoardManager boardManager, ITurnExecutor turnExecutor) {
        this.boardManager = boardManager;
        this.turnExecutor = turnExecutor;
    }

    public void parseMoves(ArrayList<Change> changes) {
        if (firstMove) {
            firstMove=false;
            isMyMove = true;
            if (changes.size() > 0) {
                turnExecutor.setMyColor(Color.WHITE);
            } else {
                turnExecutor.setMyColor(Color.BLACK);
            }
        }

        boardManager.saveMoves(changes);

        if(isMyMove){
            turnExecutor.executeTurn();
            isMyMove = false;
        }
        else {
            isMyMove = true;
        }
    }
}
