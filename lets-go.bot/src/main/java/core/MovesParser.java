package core;

import contract.Change;
import core.interfaces.IBoardManager;
import core.interfaces.IMovePerformer;
import core.interfaces.ITurnExecutor;

import java.util.ArrayList;

public class MovesParser {

    private final IBoardManager boardManager;
    private final ITurnExecutor turnExecutor;

    public MovesParser(IBoardManager boardManager, ITurnExecutor turnExecutor){
        this.boardManager = boardManager;
        this.turnExecutor = turnExecutor;
    }

    public void parseMoves(ArrayList<Change> changes){

    }
}
