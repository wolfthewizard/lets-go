package core;

import contract.enums.BoardSize;
import core.interfaces.IBoardManager;
import core.interfaces.ICommunicatorSender;
import core.interfaces.IGameInitializer;

public class GameInitializer implements IGameInitializer {

    private final ICommunicatorSender communicatorSender;
    private final IBoardManager boardManager;

    public GameInitializer(ICommunicatorSender communicatorSender, IBoardManager boardManager) {
        this.communicatorSender = communicatorSender;
        this.boardManager = boardManager;
    }

    public void startSmallBoardGame() {
        initializeGame(BoardSize.NINE);
    }

    public void startMediumBoardGame() {
        initializeGame(BoardSize.THIRTEEN);
    }

    public void startLargeBoardGame() {
        initializeGame(BoardSize.NINETEEN);
    }

    private void initializeGame(BoardSize boardSize) {
        communicatorSender.sendStartGameMessage(boardSize);
        boardManager.initializeBoard(boardSize);
    }
}
