package main.windows;

import core.serversender.JsonParser;
import core.serversender.RewindServerResponseListener;
import core.serversender.ServerCommunicator;
import main.RewindManager;

import javax.swing.*;

public class RewindSettingsWindow extends JFrame {

    public RewindSettingsWindow() {
        super("Rewind settings");

        setResizable(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel idLabel = new JLabel("Enter game id:");
        JPanel idPanel = new JPanel();
        idPanel.add(idLabel);
        add(idPanel);

        JTextField gameIdTextField = new JTextField(10);
        JPanel gameIdPanel = new JPanel();
        gameIdPanel.add(gameIdTextField);
        add(gameIdPanel);

        JLabel delayLabel = new JLabel("Enter delay in ms:");
        JPanel delayPanel = new JPanel();
        delayPanel.add(delayLabel);
        add(delayPanel);

        JTextField rewindDelayTextField = new JTextField("500", 10);
        JPanel rewindDelayPanel = new JPanel();
        rewindDelayPanel.add(rewindDelayTextField);
        add(rewindDelayPanel);

        JButton startRewindButton = new JButton("Start rewind");
        JPanel startRewindPanel = new JPanel();
        startRewindPanel.add(startRewindButton);
        add(startRewindPanel);

        startRewindButton.addActionListener(actionEvent -> {

            int delay, gameId;
            try {
                delay = Integer.parseInt(rewindDelayTextField.getText());
                gameId = Integer.parseInt(gameIdTextField.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Input format was invalid");
                return;
            }

            if (delay < 0 && gameId < 0) {
                JOptionPane.showMessageDialog(null, "Delay and id must be positive numbers");
                return;
            }

            ServerCommunicator serverCommunicator = ServerCommunicator.getInstance();
            serverCommunicator.setServerResponseListener(new RewindServerResponseListener(
                    new RewindManager(delay), new JsonParser()));
            serverCommunicator.sendStartRewindMessage(gameId);

            setVisible(false);
            dispose();
        });

        setVisible(true);
    }
}
