package main.actionlisteners;

import contract.Coordinates;
import core.serversender.ServerCommunicator;
import main.TileButton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TileButtonActionListenerTest {

    private TileButtonActionListener instance;

    private ServerCommunicator serverCommunicatorMock;

    @BeforeEach
    void setup() {

        instance = TileButtonActionListener.getInstance();

        serverCommunicatorMock = Mockito.mock(ServerCommunicator.class);
    }

    @Test
    public void getInstance_returnsInstance() {

        assertNotNull(instance);
    }

    @Test
    public void setServerCommunicator_doesNotThrow() {

        assertDoesNotThrow(() -> instance.setServerCommunicator(serverCommunicatorMock));
    }

    @Test
    public void actionPerformed_callsSendMovePassMessage() {

        instance.setServerCommunicator(serverCommunicatorMock);

        TileButton tileButton = new TileButton(new Coordinates(1, 1));
        tileButton.addActionListener(instance);

        tileButton.doClick();

        //todo : why so?
        verify(serverCommunicatorMock, times(2)).sendMoveMessage(any());
    }

}
