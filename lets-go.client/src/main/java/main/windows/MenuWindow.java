package main.windows;

import javax.swing.*;

public class MenuWindow extends JFrame {

    public MenuWindow() {
        super("Menu");

        setResizable(false);
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(210, 130);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton startGameButton = new JButton("New game");
        JPanel startGameButtonPanel = new JPanel();
        startGameButtonPanel.add(startGameButton);
        add(startGameButtonPanel);

        JButton rewindGameButton = new JButton("Rewind game");
        JPanel rewindGameButtonPanel = new JPanel();
        rewindGameButtonPanel.add(rewindGameButton);
        add(rewindGameButtonPanel);

        startGameButton.addActionListener(actionEvent -> {
            new GameSettingsWindow();

            setVisible(false);
            dispose();
        });

        rewindGameButton.addActionListener(actionEvent -> {
            new RewindSettingsWindow();

            setVisible(false);
            dispose();
        });

        setVisible(true);
    }
}
