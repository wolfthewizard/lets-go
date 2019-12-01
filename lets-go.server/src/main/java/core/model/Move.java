package core.model;

import main.contract.Coordinates;

public class Move extends MoveIdentity {
    Coordinates coordinates;
    public Move(MoveIdentity moveIdentity, Coordinates coordinates) {
        super(moveIdentity.playerColor, moveIdentity.gameId);

        this.coordinates = coordinates;
    }
}
