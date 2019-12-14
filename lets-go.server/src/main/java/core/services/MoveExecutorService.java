package core.services;

import contract.Change;
import contract.Coordinates;
import contract.ResponsePrisoners;
import contract.enums.Occupancy;
import core.interfaces.IGameRepository;
import core.interfaces.IMoveExecutorService;
import core.interfaces.IMoveValidator;
import core.model.*;
import core.model.enums.MoveResponseType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MoveExecutorService implements IMoveExecutorService {

    private IGameRepository gameRepository;
    private IMoveValidator moveValidator;

    private Occupancy[][] potentialState;
    private int boardSizeValue;
    private List<Change> changes;

    public MoveExecutorService(IGameRepository gameRepository, IMoveValidator moveValidator) {

        this.gameRepository = gameRepository;
        this.moveValidator = moveValidator;
    }

    @Override
    public MoveResponse executeMove(Move move) {

        //todo : remove this dummy return
        return new MoveResponse(MoveResponseType.GAME_GOES_ON, new MoveExecution((ArrayList) Arrays.asList(new Change(move.getPlayerColor().toOccupancy(), move.getCoordinates())), new ResponsePrisoners(0, 0)));
        /*
        Game game = gameRepository.getGame(move.getGameId());
        Board board = game.getBoard();

        if (!moveValidator.validateVacancy(board.getCurrentState(), move.getCoordinates())) {
            return new MoveResponse(MoveResponseType.INVALID_MOVE, null);
        }

        changes = new ArrayList<>();
        changes.add(new Change(move.getPlayerColor().toOccupancy(), move.getCoordinates()));

        potentialState = new Occupancy[game.getBoardSize().getValue()][];
        for (int i = 0; i < game.getBoardSize().getValue(); i++) {
            potentialState[i] = board.getCurrentState()[i].clone();
        }

        boardSizeValue = game.getBoardSize().getValue();

        doMove(move);

        return null;

         */
    }

    private void doMove(Move move) {

        //1st phase
        potentialState[move.getCoordinates().getX()][move.getCoordinates().getY()] = move.getPlayerColor().toOccupancy();

        //2nd phase


        //3rd phase

    }

    private List<Coordinates> getNeighbouringCords(Coordinates coordinates, Color color) {

        List<Coordinates> neighbours = new ArrayList<>();

        int x = coordinates.getX();
        int y = coordinates.getY();

        if (x - 1 >= 0) {

            if (potentialState[x - 1][y] == color.toOccupancy()) {
                neighbours.add(new Coordinates(x - 1, y));
            }
        }

        if (x + 1 < boardSizeValue) {

            if (potentialState[x + 1][y] == color.toOccupancy()) {
                neighbours.add(new Coordinates(x + 1, y));
            }
        }

        if (y - 1 >= 0) {

            if (potentialState[x][y - 1] == color.toOccupancy()) {
                neighbours.add(new Coordinates(x, y - 1));
            }
        }

        if (y + 1 < boardSizeValue) {

            if (potentialState[x][y + 1] == color.toOccupancy()) {
                neighbours.add(new Coordinates(x, y + 1));
            }
        }

        return neighbours;
    }
}
