package frontend;

import core.FrontendManager;
import core.contract.ResponseDTO;
import core.contract.enums.BoardSize;
import core.contract.enums.ResponseType;
import core.serversender.JsonParser;
import core.serversender.OnServerResponseListener;
import core.serversender.ServerCommunicator;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static java.lang.System.exit;

public class GameAwaitingWindow extends JFrame implements OnServerResponseListener {

    private final BoardSize boardSize;
    private final ServerCommunicator serverCommunicator;

    GameAwaitingWindow(BoardSize boardSize) {

        super("Waiting");

        this.boardSize = boardSize;

        serverCommunicator = new ServerCommunicator(new JsonParser(), this);

        setResizable(false);
        setSize(300, 200);
        setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit lobby?");
                if (i == 0) {
                    serverCommunicator.sendLeaveGameMessage();
                    exit(0);
                }
            }
        });


        add(new JLabel("Waiting for other player to join."));

        setVisible(true);
    }

    @Override
    public void responseReceived(ResponseDTO responseDTO) {

        if(responseDTO.getResponseType() == ResponseType.SUCCESS) {
            new ServerCommunicator(new JsonParser(), new FrontendManager(new GameBoardWindow(boardSize)));
            setVisible(false);
            dispose();
        } else if(responseDTO.getResponseType() == ResponseType.SERVER_ERROR ||
                responseDTO.getResponseType() == ResponseType.CANT_CREATE_GAME) {
            int i = JOptionPane.showConfirmDialog(null, "Couldn't create game.");
            exit(0);
        }
    }
}
