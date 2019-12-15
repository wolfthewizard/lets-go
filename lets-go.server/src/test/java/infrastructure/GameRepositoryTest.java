package infrastructure;

import core.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameRepositoryTest {

    private GameRepository gameRepository;

    private Game game;

    @BeforeEach
    void setup() {

        game = Mockito.mock(Game.class);
        when(game.getId()).thenReturn(0);

        gameRepository = new GameRepository();
    }

    @Test
    public void createGame_doesNotThrow_whenGameIsInserted() {

        assertDoesNotThrow(() -> gameRepository.createGame(game));
    }

    @Test
    public void getGame_returnsNull_whenGameIsNotInDB() {

        assertNull(gameRepository.getGame(0));
    }

    @Test
    public void getGame_returnsGame_whenGameIsInDB() {

        gameRepository.createGame(game);

        assertNotNull(gameRepository.getGame(0));
    }

    @Test
    public void removeGame_doesntThrow_whenGameIsNotInDB() {

        assertDoesNotThrow(() -> gameRepository.removeGame(0));
    }

    @Test
    public void removeGame_removesGame_whenGameIsInDB() {

        gameRepository.createGame(game);
        gameRepository.removeGame(0);

        assertNull(gameRepository.getGame(0));
    }

    @Test
    public void fetchId_returnsDifferentsIds() {

        int a = gameRepository.fetchId();
        int b = gameRepository.fetchId();

        assertNotEquals(a, b);
    }
}
