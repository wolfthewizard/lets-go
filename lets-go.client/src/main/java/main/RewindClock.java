package main;

import contract.gamerecord.GameRecord;
import contract.gamerecord.TurnRecord;

public class RewindClock implements Runnable {

    private Rewind rewindBoardWindow;
    private GameRecord gameRecord;
    private int delay;

    public RewindClock(Rewind rewindBoardWindow, GameRecord gameRecord, int delay) {
        this.rewindBoardWindow = rewindBoardWindow;
        this.gameRecord = gameRecord;
        this.delay = delay;
    }

    @Override
    public void run() {

        for (TurnRecord turn : gameRecord.getTurns()) {

            if (rewindBoardWindow == null) {
                return;
            }

            if (turn.getChanges().isEmpty()) {
                rewindBoardWindow.signalPass();
            } else {
                rewindBoardWindow.enforceChanges(turn.getChanges());
            }

            rewindBoardWindow.clearMoveNote();

            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                System.out.println("I was interrupted.");
            }
        }

        rewindBoardWindow.signalWinner(gameRecord.getWinner());
    }
}
