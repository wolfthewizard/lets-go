package main.windows;

import contract.Change;
import contract.enums.Winner;
import main.Rewind;
import main.RewindPanel;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class RewindBoardWindow extends JFrame implements Rewind {

    private JLabel moveNoteLabel;

    private RewindPanel rewindPanel;

    public RewindBoardWindow(RewindPanel rewindPanel) {
        super("It's Rewind Time!");

        setResizable(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(rewindPanel.getDimension(), rewindPanel.getDimension() + 70);
        setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new MenuWindow();

                setVisible(false);
                dispose();
            }
        });

        this.rewindPanel = rewindPanel;

        moveNoteLabel = new JLabel(" ");
        JPanel moveNotePanel = new JPanel();
        moveNotePanel.add(moveNoteLabel);
        add(moveNotePanel);

        add(rewindPanel);

        setVisible(true);
    }

    @Override
    public void signalPass() {
        moveNoteLabel.setText("Player has passed.");
    }

    @Override
    public void signalWinner(Winner winner) {

        if (winner == null) {
            moveNoteLabel.setText("There was an error in determining the winner");
            return;
        }

        switch (winner) {
            case BLACK:
                moveNoteLabel.setText("Black has won.");
                break;

            case WHITE:
                moveNoteLabel.setText("White has won.");
                break;

            case TIE:
                moveNoteLabel.setText("It's a tie.");
                break;
        }
    }

    @Override
    public void clearMoveNote() {
        moveNoteLabel.setText(" ");
    }

    @Override
    public void enforceChanges(List<Change> changes) {
        rewindPanel.enforceChanges(changes);
    }
}
