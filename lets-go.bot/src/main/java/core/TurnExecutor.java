package core;

import contract.Coordinates;
import core.interfaces.IBoardManager;
import core.interfaces.ICommunicatorSender;
import core.interfaces.IMovePerformer;
import core.interfaces.ITurnExecutor;
import core.model.Color;

public class TurnExecutor implements ITurnExecutor {

    private final ICommunicatorSender communicatorSender;
    private final IMovePerformer movePerformer;
    private final IBoardManager boardManager;
    private Color myColor;

    public TurnExecutor(ICommunicatorSender communicatorSender, IMovePerformer movePerformer, IBoardManager boardManager) {
        this.communicatorSender = communicatorSender;
        this.movePerformer = movePerformer;
        this.boardManager = boardManager;
    }

    @Override
    public void setMyColor(Color color) {
        myColor = color;
    }

    @Override
    public void executeTurn(boolean lastTurnInvalid) {
        Coordinates coordinates = movePerformer.performMove(boardManager.getBoard(), boardManager.getBoardSize(), myColor, lastTurnInvalid);

        if(coordinates == null){
            communicatorSender.sendMovePassMessage();
        }
        else{
            communicatorSender.sendMoveMessage(coordinates);
        }
    }

}
