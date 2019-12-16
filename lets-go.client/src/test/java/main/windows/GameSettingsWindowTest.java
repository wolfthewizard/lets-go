package main.windows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameSettingsWindowTest {

    private GameSettingsWindow gameSettingsWindow;

    @Test
    public void doesNotThrow() {

        assertDoesNotThrow(() -> gameSettingsWindow = new GameSettingsWindow());
        gameSettingsWindow.setVisible(false);
    }
}
