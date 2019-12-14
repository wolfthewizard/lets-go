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

        int deltaX;
        int deltaY;

        deltaX = -1;

        if (x + deltaX >= 0) {

            deltaY = -1;

            if (y + deltaY >= 0) {

                if (potentialState[x + deltaX][y + deltaY] == color.toOccupancy()) {
                    neighbours.add(new Coordinates(x + deltaX, y + deltaY));
                }
            }

            deltaY = 1;

            if (y + deltaY < boardSizeValue) {

                if (potentialState[x + deltaX][y + deltaY] == color.toOccupancy()) {
                    neighbours.add(new Coordinates(x + deltaX, y + deltaY));
                }
            }
        }

        deltaX = 1;

        if (x + deltaX < boardSizeValue) {

            deltaY = -1;

            if (y + deltaY >= 0) {

                if (potentialState[x + deltaX][y + deltaY] == color.toOccupancy()) {
                    neighbours.add(new Coordinates(x + deltaX, y + deltaY));
                }
            }

            deltaY = 1;

            if (y + deltaY < boardSizeValue) {

                if (potentialState[x + deltaX][y + deltaY] == color.toOccupancy()) {
                    neighbours.add(new Coordinates(x + deltaX, y + deltaY));
                }
            }
        }

        return neighbours;
    }
}
