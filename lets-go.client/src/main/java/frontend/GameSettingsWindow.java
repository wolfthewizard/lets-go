package frontend;

import core.FrontendManager;
import core.contract.enums.BoardSize;
import core.serversender.JsonParser;
import core.serversender.ServerCommunicator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSettingsWindow extends JFrame {

    public GameSettingsWindow() {

        super("Game settings");

        setResizable(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel opponentTypeLabel = new JLabel("Choose oppponent type:");

        ButtonGroup gameTypeRadios = new ButtonGroup();
        JRadioButton playerRadio = new JRadioButton("player");
        JRadioButton botRadio = new JRadioButton("bot");
        gameTypeRadios.add(playerRadio);
        gameTypeRadios.add(botRadio);
        playerRadio.setSelected(true);

        JLabel boardSizeLabel = new JLabel("Choose board size:");

        String[] sizes = new String[]{"9x9", "13x13", "19x19"};
        JComboBox boardSizeCombo = new JComboBox<>(sizes);
        boardSizeCombo.setSelectedIndex(2);

        JButton startGameButton = new JButton("Start game");

        JPanel opponentTypePanel = new JPanel();
        opponentTypePanel.add(opponentTypeLabel);

        JPanel radioGroupPanel = new JPanel();
        radioGroupPanel.add(playerRadio);
        radioGroupPanel.add(botRadio);

        JPanel boardSizePanel = new JPanel();
        boardSizePanel.add(boardSizeLabel);

        JPanel boardSizeComboPanel = new JPanel();
        boardSizeComboPanel.add(boardSizeCombo);

        JPanel startGamePanel = new JPanel();
        startGamePanel.add(startGameButton);

        this.add(opponentTypePanel);
        this.add(radioGroupPanel);
        this.add(boardSizePanel);
        this.add(boardSizeComboPanel);
        this.add(startGamePanel);


        startGameButton.addActionListener(e -> {

            BoardSize boardSize;

            switch (String.valueOf(boardSizeCombo.getSelectedItem())) {
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

            ServerCommunicator serverCommunicator = new ServerCommunicator(new JsonParser(),
                    new FrontendManager(new GameBoardWindow(boardSize)));
            TileButtonActionListener.getInstance().setServerCommunicator(serverCommunicator);
            PassButtonActionListener.getInstance().setServerCommunicator(serverCommunicator);
            serverCommunicator.sendStartGameMessage(botRadio.isSelected(), boardSize);

            setVisible(false);
            dispose();
        });

        setVisible(true);
    }
}
