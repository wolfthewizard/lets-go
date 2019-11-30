package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoardWindow extends JFrame {

    private JLabel whoseMove;
    private JLabel opponentsCaptives;
    private JLabel playersCaptives;

    private BoardPanel boardPanel;
    private JButton passButton;

    public GameBoardWindow(BoardSize size) {

        super("Let's Go!");

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        whoseMove = new JLabel("It's your turn.");
        opponentsCaptives = new JLabel("Opponent's captives: ");
        playersCaptives = new JLabel("Player's captives: ");
        boardPanel = new BoardPanel(size);
        passButton = new JButton("Pass");

        setSize(boardPanel.getDimension(), boardPanel.getDimension() + 160);
        setLocationRelativeTo(null);

        JPanel whoseMovePanel = new JPanel();
        whoseMovePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        whoseMovePanel.add(whoseMove);

        JPanel playersCaptivesPanel = new JPanel();
        playersCaptivesPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        playersCaptivesPanel.add(playersCaptives);

        JPanel opponentsCaptivesPanel = new JPanel();
        opponentsCaptivesPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        opponentsCaptivesPanel.add(opponentsCaptives);

        JPanel passButtonPanel = new JPanel();
        passButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        passButtonPanel.add(passButton);

        JPanel boardPanelPanel = new JPanel();
        boardPanelPanel.setLayout(new FlowLayout());
        boardPanelPanel.add(boardPanel);

        add(whoseMovePanel);
        add(opponentsCaptivesPanel);
        add(boardPanelPanel);
        add(playersCaptivesPanel);
        add(passButtonPanel);

        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("pass");
                // todo : send a pass signal to server
            }
        });

        setVisible(true);
    }
}
