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

        //setLayout(new FlowLayout());
        setResizable(false);
        setBounds(400, 200, 800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        whoseMove = new JLabel("It's move");
        opponentsCaptives = new JLabel("Opponent's captives: ");
        playersCaptives = new JLabel("Player's captives: ");
        boardPanel = new BoardPanel(size);
        passButton = new JButton("Pass");

        JPanel playersCaptivesPanel = new JPanel();
        playersCaptivesPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        playersCaptivesPanel.add(playersCaptives);

        //add(whoseMove);
        //add(opponentsCaptives);
        //add(playersCaptivesPanel);
        add(boardPanel);
        //add(passButton);

        passButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // todo : send a pass signal to server
            }
        });

        for (Component comp : getComponents()) {
            ((JComponent) comp).setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        setVisible(true);
    }
}
