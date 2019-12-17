package main.helpers.playervalidator;

import contract.ResponseDTO;
import contract.enums.BoardSize;
import core.interfaces.ICommandDirector;
import core.model.MoveIdentity;
import core.model.enums.Color;
import main.ClientConnectionThread;
import main.IClientsManager;
import main.helpers.jsonparser.IJsonParser;
import main.helpers.playervalidator.IPlayerValidator;
import main.model.GameInfo;
import main.model.GameWithPlayers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class PlayerValidatorTest {

    private PlayerValidator playerValidator;

    @BeforeEach
    public void setUp(){
        playerValidator = new PlayerValidator();
    }

    @Test
    public void addNewGame_removesWaitingPlayer() {

        playerValidator.addWaitingPlayer(BoardSize.NINE, 1);

        playerValidator.addNewGame(1, 2, 3);

        Integer result = playerValidator.getWaitingPlayerId(BoardSize.NINE);

        assertNull(result);
    }

    @Test
    public void removeGame_removesGame() {

        playerValidator.addNewGame(1,2,3);
        playerValidator.removeGame(3);
        GameInfo result = playerValidator.getGameInfo(1);

        assertNull(result);
    }

    @Test
    public void playerLeft_removeesPlayerFromWaitingPlayers() {

        playerValidator.addWaitingPlayer(BoardSize.NINE, 2);
        playerValidator.playerLeft(2);
        Integer result = playerValidator.getWaitingPlayerId(BoardSize.NINE);

        assertNull(result);
    }

    @Test
    public void getGameInfo_getsRightGame() {

        playerValidator.addNewGame(1,2,3);
        GameInfo result = playerValidator.getGameInfo(1);

        assertNotNull(result);
        assertEquals(result.getSecondPlayerId(), 2);
    }

    @Test
    public void addWaitingPlayer_addsPlayersToRightBoard() {

        playerValidator.addWaitingPlayer(BoardSize.NINE, 3);
        Integer nullResult = playerValidator.getWaitingPlayerId(BoardSize.THIRTEEN);
        Integer notNullResult = playerValidator.getWaitingPlayerId(BoardSize.NINE);

        assertNull(nullResult);
        assertNotNull(notNullResult);
    }

    @Test
    public void getWaitingPlayer_returnNullForNoPlayers() {
        Integer result = playerValidator.getWaitingPlayerId(BoardSize.NINETEEN);

        assertNull(result);
    }
}
