package core.model;

import contract.enums.BoardSize;
import contract.enums.Occupancy;

public class Board {

    private Occupancy[][] currentState;
    private Occupancy[][] previousTurnState;
    private Prisoners currentPrisoners;

    public Board(BoardSize boardSize) {

        currentState = new Occupancy[boardSize.getValue()][];
        for (int i = 0; i < boardSize.getValue(); i++) {
            currentState[i] = new Occupancy[boardSize.getValue()];
            for (int j = 0; j < boardSize.getValue(); j++) {
                currentState[i][j] = Occupancy.EMPTY;
            }
        }

        previousTurnState = new Occupancy[boardSize.getValue()][];
        for (int i = 0; i < boardSize.getValue(); i++) {
            previousTurnState[i] = new Occupancy[boardSize.getValue()];
            for (int j = 0; j < boardSize.getValue(); j++) {
                previousTurnState[i][j] = Occupancy.EMPTY;
            }
        }

        currentPrisoners = new Prisoners(0, 0);
    }

    public Occupancy[][] getCurrentState() {
        return currentState;
    }

    public Occupancy[][] getPreviousTurnState() {
        return previousTurnState;
    }

    public Prisoners getCurrentPrisoners() {
        return currentPrisoners;
    }

    public void setCurrentPrisoners(Prisoners currentPrisoners) {
        this.currentPrisoners = currentPrisoners;
    }

    public void insertState(Occupancy[][] state) {

        previousTurnState = currentState;
        currentState = state;
    }
}
