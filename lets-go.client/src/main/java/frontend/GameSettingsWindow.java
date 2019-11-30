package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettingsWindow extends JFrame {

    public GameSettingsWindow() {
        super("Game settings");
        setResizable(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setBounds(400, 200, 300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel opponentTypeTitle = new JLabel("Choose oppponent type:");
        this.add(opponentTypeTitle);
        ButtonGroup gameTypeRadios = new ButtonGroup();

        JRadioButton playerRadio = new JRadioButton("player");
        JRadioButton botRadio = new JRadioButton("bot");

        gameTypeRadios.add(playerRadio);
        gameTypeRadios.add(botRadio);
        playerRadio.setSelected(true);
        JPanel radios = new JPanel();
        radios.add(playerRadio);
        radios.add(botRadio);
        this.add(radios);

        JLabel boardSizeTitle = new JLabel("Choose board size:");
        String[] sizes = new String[]{"9x9", "13x13", "19x19"};
        JComboBox boardSizes = new JComboBox<>(sizes);
        boardSizes.setSelectedIndex(2);

        this.add(boardSizeTitle);
        this.add(boardSizes);

        /* #region startSimulationButton Set Up */

        JButton startGameButton = new JButton("Start game");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // todo : communicate with server

                BoardSize boardSize;

                switch (String.valueOf(boardSizes.getSelectedItem())) {
                    case "9x9":
                        boardSize = BoardSize.NINE;
                        break;
                    case "13x13":
                        boardSize = BoardSize.THIRTEEN;
                        break;
                    case "19x19":
                        boardSize = BoardSize.NINETEEN;
                        break;
                    default:
                        boardSize = null;
                }

                GameBoardWindow gbw = new GameBoardWindow(boardSize);

                setVisible(false);
                dispose();
            }
        });

        add(startGameButton);
        for (Component comp : getComponents()) {
            ((JComponent) comp).setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        setVisible(true);
    }
}
