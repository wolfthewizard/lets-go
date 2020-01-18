package core;

import contract.Change;
import core.interfaces.IBoardManager;
import core.interfaces.IMovesParser;
import core.interfaces.ITurnExecutor;
import core.model.Color;

import java.util.ArrayList;
import java.util.List;

public class MovesParser implements IMovesParser {

    private final IBoardManager boardManager;
    private final ITurnExecutor turnExecutor;
    private boolean firstMove = true;
    private boolean isMyMove = false;

    public MovesParser(IBoardManager boardManager, ITurnExecutor turnExecutor) {
        this.boardManager = boardManager;
        this.turnExecutor = turnExecutor;
    }

    @Override
    public void parseMoves(List<Change> changes) {
        if (firstMove) {
            firstMove = false;
            isMyMove = true;
            if (changes.isEmpty()) {
                turnExecutor.setMyColor(Color.BLACK);
            } else {
                turnExecutor.setMyColor(Color.WHITE);
            }
        }

        boardManager.saveMoves(changes);

        if (isMyMove) {
            turnExecutor.executeTurn(false);
            isMyMove = false;
        } else {
            isMyMove = true;
        }
    }
}
