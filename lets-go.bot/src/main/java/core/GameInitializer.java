package core;

import contract.enums.BoardSize;
import core.interfaces.IBoardManager;
import core.interfaces.ICommunicatorSender;
import core.interfaces.IGameInitializer;

public class GameInitializer implements IGameInitializer {

    private final ICommunicatorSender communicatorSender;
    private final IBoardManager boardManager;

    public GameInitializer(ICommunicatorSender communicatorSender, IBoardManager boardManager){
        this.communicatorSender=communicatorSender;
        this.boardManager = boardManager;
    }

    public void StartSmallBoardGame() {
        initializeGame(BoardSize.NINE);
    }

    public void StartMediumBoardGame() {
        initializeGame(BoardSize.THIRTEEN);
    }

    public void StartLargeBoardGame() {
        initializeGame(BoardSize.NINETEEN);
    }

    private void initializeGame(BoardSize boardSize){
        communicatorSender.sendStartGameMessage(boardSize);
        boardManager.initializeBoard(boardSize);
    }

}
