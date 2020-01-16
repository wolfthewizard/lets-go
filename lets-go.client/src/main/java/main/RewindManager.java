package main;

import contract.gamerecord.GameRecord;
import core.interfaces.IRewindManager;
import main.windows.MenuWindow;
import main.windows.RewindBoardWindow;

import javax.swing.*;

public class RewindManager implements IRewindManager {

    private int delay;

    public RewindManager(int delay) {
        this.delay = delay;
    }

    @Override
    public void rewind(GameRecord gameRecord) {
        Rewind rewindBoardWindow = new RewindBoardWindow(new RewindPanel(gameRecord.getBoardSize()));

        RewindClock rewindClock = new RewindClock(rewindBoardWindow, gameRecord, delay);
        rewindClock.run();
    }

    @Override
    public void handleInvalidId() {
        JOptionPane.showMessageDialog(null, "Game id was invalid.");
        new MenuWindow();
    }
}
