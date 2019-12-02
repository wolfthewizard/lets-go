package core.model;

import main.contract.Coordinates;

public class Move extends MoveIdentity {
    private Coordinates coordinates;

    public Move(MoveIdentity moveIdentity, Coordinates coordinates) {
        super(moveIdentity.getPlayerColor(), moveIdentity.getGameId());

        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
