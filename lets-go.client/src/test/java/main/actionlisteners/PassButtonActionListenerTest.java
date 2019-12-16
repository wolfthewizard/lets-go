package main.actionlisteners;

import core.serversender.ServerCommunicator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PassButtonActionListenerTest {

    private PassButtonActionListener instance;

    private ServerCommunicator serverCommunicatorMock;

    @BeforeEach
    void setup() {

        instance = PassButtonActionListener.getInstance();

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

        instance.actionPerformed(null);

        verify(serverCommunicatorMock, times(1)).sendMovePassMessage();
    }
}
