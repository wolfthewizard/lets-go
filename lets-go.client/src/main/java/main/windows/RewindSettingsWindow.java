package main.windows;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JTextField rewindDelayTextField = new JTextField(10);
        JPanel rewindDelayPanel = new JPanel();
        rewindDelayPanel.add(rewindDelayTextField);
        add(rewindDelayPanel);

        JButton startRewindButton = new JButton("Start rewind");
        JPanel startRewindPanel = new JPanel();
        startRewindPanel.add(startRewindButton);
        add(startRewindPanel);

        startRewindButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                /* todo */
            }
        });

        setVisible(true);
    }
}