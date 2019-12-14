package core;

import contract.enums.BoardSize;
import core.interfaces.ICommunicatorSender;
import core.interfaces.IGameInitializer;
import core.interfaces.IServerResponseReceiver;
import infrastructure.ServerSender;

public class GameInitializer implements IGameInitializer {

    private final ICommunicatorSender communicatorSender;
    public GameInitializer(ICommunicatorSender communicatorSender){
        this.communicatorSender=communicatorSender;
    }

    public void StartSmallBoardGame() {
        communicatorSender.sendStartGameMessage(BoardSize.NINE);
    }

    public void StartMediumBoardGame() {
        communicatorSender.sendStartGameMessage(BoardSize.THIRTEEN);
    }

    public void StartLargeBoardGame() {
        communicatorSender.sendStartGameMessage(BoardSize.NINETEEN);
    }
}
