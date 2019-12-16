package core.services;

import contract.enums.BoardSize;
import core.interfaces.IGameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameManagerServiceTest {

    private GameManagerService gameManagerService;
    private IGameRepository gameRepositoryMock;

    @BeforeEach
    void setup() {

        gameRepositoryMock = Mockito.mock(IGameRepository.class);
        when(gameRepositoryMock.fetchId()).thenReturn(0);

        gameManagerService = new GameManagerService(gameRepositoryMock);
    }

    @Test
    public void createNewGame_returnsInt_whenGameIsCreated() {

        assertEquals(gameManagerService.createNewGame(BoardSize.NINE), 0);
    }

    @Test
    public void cancelGame_callsRemoveGame() {

        gameManagerService.cancelGame(0);
        verify(gameRepositoryMock, times(1)).removeGame(0);
    }
}
