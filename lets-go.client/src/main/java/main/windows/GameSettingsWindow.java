package main.windows;

import contract.enums.BoardSize;
import core.serversender.JsonParser;
import core.serversender.ServerCommunicator;
import core.serversender.GameServerResponseListener;
import main.BoardPanel;
import main.GameManager;
import main.actionlisteners.PassButtonActionListener;
import main.actionlisteners.TileButtonActionListener;

import javax.swing.*;

public class GameSettingsWindow extends JFrame {

    public GameSettingsWindow() {

        super("Game settings");

        setResizable(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel connectionAddressLabel = new JLabel("Choose connection address:");

        JTextField connectionAddressTextField = new JTextField("localhost", 10);

//        JLabel opponentTypeLabel = new JLabel("Choose oppponent type:");

//        ButtonGroup gameTypeRadios = new ButtonGroup();
//        JRadioButton playerRadio = new JRadioButton("player");
//        JRadioButton botRadio = new JRadioButton("bot");
//        gameTypeRadios.add(playerRadio);
//        gameTypeRadios.add(botRadio);
//        playerRadio.setSelected(true);

        JLabel boardSizeLabel = new JLabel("Choose board size:");

        String[] sizes = new String[]{"9x9", "13x13", "19x19"};
        JComboBox<String> boardSizeCombo = new JComboBox<>(sizes);
        boardSizeCombo.setSelectedIndex(2);

        JButton startGameButton = new JButton("Start game");

//        JPanel opponentTypePanel = new JPanel();
//        opponentTypePanel.add(opponentTypeLabel);

//        JPanel radioGroupPanel = new JPanel();
//        radioGroupPanel.add(playerRadio);
//        radioGroupPanel.add(botRadio);

        JPanel connectionAddressPanel = new JPanel();
        connectionAddressPanel.add(connectionAddressLabel);

        JPanel connectionAddressSelectionPanel = new JPanel();
        connectionAddressSelectionPanel.add(connectionAddressTextField);

        JPanel boardSizePanel = new JPanel();
        boardSizePanel.add(boardSizeLabel);

        JPanel boardSizeComboPanel = new JPanel();
        boardSizeComboPanel.add(boardSizeCombo);

        JPanel startGamePanel = new JPanel();
        startGamePanel.add(startGameButton);

//        this.add(opponentTypePanel);
//        this.add(radioGroupPanel);
        this.add(connectionAddressPanel);
        this.add(connectionAddressSelectionPanel);
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
                default:
                    boardSize = BoardSize.NINETEEN;
            }

            ServerCommunicator serverCommunicator = ServerCommunicator.getInstance();
            serverCommunicator.setServerResponseListener(new GameServerResponseListener(
                    new GameManager(new GameBoardWindow(new BoardPanel(boardSize))), new JsonParser()), connectionAddressTextField.getText());
            TileButtonActionListener.getInstance().setServerCommunicator(serverCommunicator);
            PassButtonActionListener.getInstance().setServerCommunicator(serverCommunicator);
            serverCommunicator.sendStartGameMessage(boardSize);

            setVisible(false);
            dispose();
        });

        setVisible(true);
    }
}
