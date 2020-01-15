package main;

import contract.enums.BoardSize;
import core.interfaces.IRewindManager;
import main.windows.MenuWindow;
import main.windows.RewindBoardWindow;

import javax.swing.*;

public class RewindManager implements IRewindManager {

    @Override
    public void rewind(BoardSize boardSize) {
        Rewind rewindBoardWindow = new RewindBoardWindow(new RewindPanel(boardSize));

        /* todo */
    }

    @Override
    public void handleInvalidId() {
        JOptionPane.showMessageDialog(null, "Game id was invalid.");
        new MenuWindow();
    }
}
