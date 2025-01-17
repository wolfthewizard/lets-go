package main.windows;

import contract.Change;
import core.serversender.ServerCommunicator;
import main.BoardPanel;
import main.Game;
import main.actionlisteners.PassButtonActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static java.lang.System.exit;

public class GameBoardWindow extends JFrame implements Game {

    private JLabel serverResponseLabel;
    private JLabel whoseMoveLabel;
    private JLabel opponentsCaptivesLabel;
    private JLabel playersCaptivesLabel;

    private BoardPanel boardPanel;

    public GameBoardWindow(BoardPanel boardPanel) {

        super("Let's Go!");

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.OK_OPTION) {
                    closeGame();
                    setDefaultCloseOperation(EXIT_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }
            }
        });

        setResizable(false);

        serverResponseLabel = new JLabel("Waiting for server response...");
        whoseMoveLabel = new JLabel(" ");
        opponentsCaptivesLabel = new JLabel(" ");
        playersCaptivesLabel = new JLabel(" ");
        this.boardPanel = boardPanel;
        JButton passButton = new JButton("Pass");

        setSize(boardPanel.getDimension(), boardPanel.getDimension() + 180);
        setLocationRelativeTo(null);

        JPanel serverResponsePanel = new JPanel();
        serverResponsePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        serverResponsePanel.add(serverResponseLabel);

        JPanel whoseMovePanel = new JPanel();
        whoseMovePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        whoseMovePanel.add(whoseMoveLabel);

        JPanel playersCaptivesPanel = new JPanel();
        playersCaptivesPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        playersCaptivesPanel.add(playersCaptivesLabel);

        JPanel opponentsCaptivesPanel = new JPanel();
        opponentsCaptivesPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        opponentsCaptivesPanel.add(opponentsCaptivesLabel);

        JPanel passButtonPanel = new JPanel();
        passButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        passButtonPanel.add(passButton);

        JPanel boardPanelPanel = new JPanel();
        boardPanelPanel.setLayout(new FlowLayout());
        boardPanelPanel.add(boardPanel);

        add(serverResponsePanel);
        add(whoseMovePanel);
        add(opponentsCaptivesPanel);
        add(boardPanelPanel);
        add(playersCaptivesPanel);
        add(passButtonPanel);

        passButton.addActionListener(PassButtonActionListener.getInstance());

        setVisible(true);
    }

    public void clearServerResponse() {
        serverResponseLabel.setText(" ");
    }

    public void setOpponentsCaptives(int nOfCaptives) {
        opponentsCaptivesLabel.setText("Opponent's captives: " + nOfCaptives);
    }

    public void setPlayersCaptives(int nOfCaptives) {
        playersCaptivesLabel.setText("Player's captives: " + nOfCaptives);
    }

    public void signalPlayersMove() {
        whoseMoveLabel.setText("It's your turn.");
    }

    public void signalOpponentsMove() {
        whoseMoveLabel.setText("It's opponent's turn.");
    }

    public void signalInvalidMove() {
        serverResponseLabel.setText("Move was invalid.");
    }

    public void signalOpponentsLeave() {
        JOptionPane.showMessageDialog(null, "Opponent has left.\nYou win!");
    }

    public void signalWin(int gameId) {
        JOptionPane.showMessageDialog(this, "You win!\nThis game id was: " + gameId);
    }

    public void signalLose(int gameId) {
        JOptionPane.showMessageDialog(this, "You lose.\nThis game id was: " + gameId);
    }

    public void signalTie(int gameId) {
        JOptionPane.showMessageDialog(this, "It's a tie.\nThis game id was: " + gameId);
    }

    public void openNewGameCreation() {
        new MenuWindow();
    }

    public void closeGame() {
        ServerCommunicator.getInstance().sendLeaveGameMessage();
        setVisible(false);
    }

    public void exitApp() {
        exit(0);
    }

    public void signalFailedToCreateGame() {
        JOptionPane.showMessageDialog(null, "Couldn't create game.");
    }

    public void signalServerFailed() {
        JOptionPane.showMessageDialog(null, "Server has crashed.");
    }

    public void enforceChanges(List<Change> changes) {
        boardPanel.enforceChanges(changes);
    }
}