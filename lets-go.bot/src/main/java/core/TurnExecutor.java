package core;

import core.interfaces.IBoardManager;
import core.interfaces.ICommunicatorSender;
import core.interfaces.IMovePerformer;

public class TurnExecutor {

    private final ICommunicatorSender communicatorSender;
    private final IMovePerformer movePerformer;
    private final IBoardManager boardManager;

    public TurnExecutor(ICommunicatorSender communicatorSender, IMovePerformer movePerformer, IBoardManager boardManager){
        this.communicatorSender = communicatorSender;
        this.movePerformer = movePerformer;
        this.boardManager = boardManager;
    }
}
