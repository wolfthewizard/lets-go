package main.windows;

import main.Rewind;
import main.RewindPanel;

import javax.swing.*;
import java.awt.*;

public class RewindBoardWindow extends JFrame implements Rewind {

    private JLabel moveNoteLabel;

    private RewindPanel rewindPanel;

    public RewindBoardWindow(RewindPanel rewindPanel) {
        super("It's Rewind Time!");

        setResizable(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(rewindPanel.getDimension(), rewindPanel.getDimension() + 50);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel moveNotePanel = new JPanel();
        moveNotePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        moveNotePanel.add(moveNoteLabel);
        add(moveNotePanel);

        setVisible(true);
    }

    @Override
    public void signalPass() {
        moveNoteLabel.setText("Player has passed.");
    }

    @Override
    public void signalWinner() {
        /* todo : color */
    }

    @Override
    public void clearMoveNote() {
        moveNoteLabel.setText("");
    }
}
