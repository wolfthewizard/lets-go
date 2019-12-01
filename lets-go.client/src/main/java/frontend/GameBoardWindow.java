package frontend;

import core.contract.enums.BoardSize;

import javax.swing.*;
import java.awt.*;

public class GameBoardWindow extends JFrame {

    private JLabel serverResponseLabel;
    private JLabel whoseMoveLabel;
    private JLabel opponentsCaptivesLabel;
    private JLabel playersCaptivesLabel;

    private BoardPanel boardPanel;
    private JButton passButton;

    public GameBoardWindow(BoardSize size) {

        super("Let's Go!");



        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        serverResponseLabel = new JLabel("This is response from server.");
        whoseMoveLabel = new JLabel("It's your turn.");
        opponentsCaptivesLabel = new JLabel("Opponent's captives: 0");
        playersCaptivesLabel = new JLabel("Player's captives: 0");
        boardPanel = new BoardPanel(size);
        passButton = new JButton("Pass");

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

        passButton.addActionListener(actionEvent -> {
            System.out.println("pass");
            // todo : send a pass signal to server
        });

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
}
