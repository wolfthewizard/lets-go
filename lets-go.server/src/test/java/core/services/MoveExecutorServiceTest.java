package core.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import contract.Coordinates;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.interfaces.IGameArbitrator;
import core.interfaces.IGameRepository;
import core.interfaces.IMoveHelper;
import core.interfaces.IMoveValidator;
import core.model.*;
import core.model.enums.Color;
import core.model.enums.MoveResponseType;
import core.model.enums.Winner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MoveExecutorServiceTest {

    private MoveExecutorService moveExecutorService;

    private IGameRepository gameRepositoryMock;
    private IMoveValidator moveValidatorMock;
    private IGameArbitrator gameArbitratorMock;
    private IMoveHelper moveHelperMock;

    private Game game;
    private Board board;

    private Occupancy[][] currentState;
    private Occupancy[][] previousTurnState;
    private Move move;

    @BeforeEach
    void setup() {

        currentState = new Occupancy[9][];
        for (int i = 0; i < 9; i++) {
            currentState[i] = new Occupancy[9];
            for (int j = 0; j < 9; j++) {
                currentState[i][j] = Occupancy.EMPTY;
            }
        }

        previousTurnState = new Occupancy[9][];
        for (int i = 0; i < 9; i++) {
            previousTurnState[i] = new Occupancy[9];
            for (int j = 0; j < 9; j++) {
                previousTurnState[i][j] = Occupancy.EMPTY;
            }
        }

        move = new Move(new MoveIdentity(Color.BLACK, 0), new Coordinates(0 ,0));

        board = Mockito.mock(Board.class);
        when(board.getCurrentPrisoners()).thenReturn(new Prisoners(0, 0));
        when(board.getCurrentState()).thenReturn(currentState);
        when(board.getPreviousTurnState()).thenReturn(previousTurnState);

        game = Mockito.mock(Game.class);
        when(game.getBoardSize()).thenReturn(BoardSize.NINE);
        when(game.getBoard()).thenReturn(board);

        gameRepositoryMock = Mockito.mock(IGameRepository.class);
        when(gameRepositoryMock.getGame(anyInt())).thenReturn(game);

        moveValidatorMock = Mockito.mock(IMoveValidator.class);
        when(moveValidatorMock.validateKO(anyInt(), any(Occupancy[][].class), any(Occupancy[][].class))).thenReturn(true);
        when(moveValidatorMock.validateVacancy(any(Occupancy[][].class), any(Coordinates.class))).thenReturn(true);
        when(moveValidatorMock.validateSuicide(anyList())).thenReturn(true);

        gameArbitratorMock = Mockito.mock(IGameArbitrator.class);

        moveHelperMock = Mockito.mock(IMoveHelper.class);

        moveExecutorService = new MoveExecutorService(gameRepositoryMock, moveValidatorMock, gameArbitratorMock, moveHelperMock);
    }

    @Test
    public void executeMove_returnsInvalid_whenMoveIsColliding() {

        when(moveValidatorMock.validateVacancy(any(Occupancy[][].class), any(Coordinates.class))).thenReturn(false);

        assertEquals(moveExecutorService.executeMove(move).getMoveResponseType(), MoveResponseType.INVALID_MOVE);
    }

    @Test
    public void executeMove_returnsInvalid_whenMoveIsKO() {

        when(moveValidatorMock.validateKO(anyInt(), any(Occupancy[][].class), any(Occupancy[][].class))).thenReturn(false);

        assertEquals(moveExecutorService.executeMove(move).getMoveResponseType(), MoveResponseType.INVALID_MOVE);
    }

    @Test
    public void executeMove_returnsPass_whenMoveIsPass() {

        move = new Move(new MoveIdentity(Color.BLACK, 0), null);
        when(game.isLastTurnPassed()).thenReturn(false);

        assertEquals(moveExecutorService.executeMove(move).getMoveResponseType(), MoveResponseType.GAME_GOES_ON);
        assertEquals(moveExecutorService.executeMove(move).getMoveExecution().getChanges().size(), 0);
    }

    @Test
    public void executeMove_returnsWinner_whenMoveIsPassSecondTime() {

        move = new Move(new MoveIdentity(Color.BLACK, 0), null);
        when(game.isLastTurnPassed()).thenReturn(true);
        when(gameArbitratorMock.toMoveResponseType(any(Winner.class), any(Color.class))).thenReturn(MoveResponseType.CURRENT_PLAYER_WON);

        assertEquals(moveExecutorService.executeMove(move).getMoveResponseType(), MoveResponseType.CURRENT_PLAYER_WON);
    }

    @Test
    public void executemove_insertsDataIntoDataBase_whenMoveIsCorrect() {

        MoveResponseType moveResponseType = moveExecutorService.executeMove(move).getMoveResponseType();

        verify(board, times(1)).insertState(any());
        assertEquals(moveResponseType, MoveResponseType.GAME_GOES_ON);
    }
}
