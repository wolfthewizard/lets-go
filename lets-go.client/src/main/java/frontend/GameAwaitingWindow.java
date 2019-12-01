package frontend;

import core.contract.enums.BoardSize;

import javax.swing.*;

public class GameAwaitingWindow extends JFrame {

    GameAwaitingWindow(BoardSize boardSize) {

        super("Waiting");

        setResizable(false);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("Waiting for other player to join."));

        setVisible(true);

        //todo wait for signal from server

        new GameBoardWindow(boardSize);

        kill();
    }

    public void kill() {
        setVisible(false);
        dispose();
    }
}
