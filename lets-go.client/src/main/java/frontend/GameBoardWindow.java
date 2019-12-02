package frontend;

import core.contract.Change;
import core.contract.enums.BoardSize;
import core.serversender.JsonParser;
import core.serversender.ServerCommunicator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static java.lang.System.exit;

public class GameBoardWindow extends JFrame {

    private JLabel serverResponseLabel;
    private JLabel whoseMoveLabel;
    private JLabel opponentsCaptivesLabel;
    private JLabel playersCaptivesLabel;

    private BoardPanel boardPanel;

    public GameBoardWindow(BoardSize size) {

        super("Let's Go!");

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?");
                if (i == 0) {
                    new ServerCommunicator(new JsonParser(), null).sendLeaveGameMessage();
                    exit(0);
                    // todo : fix this, clicking cancel still closes window
                }
            }
        });

        setResizable(false);

        serverResponseLabel = new JLabel("This is response from server.");
        whoseMoveLabel = new JLabel("It's your turn.");
        opponentsCaptivesLabel = new JLabel("Opponent's captives: 0");
        playersCaptivesLabel = new JLabel("Player's captives: 0");
        boardPanel = new BoardPanel(size);
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

    public void failedToCreateGame() {
        int i = JOptionPane.showConfirmDialog(null, "Couldn't create game.");
        exit(0);
    }

    public void serverFailed() {
        int i = JOptionPane.showConfirmDialog(null, "Server has crashed.");
        exit(0);
    }

    public void enforceChanges(List<Change> changes) {
        boardPanel.enforceChanges(changes);
    }
}
