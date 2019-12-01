package main.model;

import core.model.MoveIdentity;

public class GameInfo {
    private MoveIdentity moveIdentity;
    private int secondPlayerId;

    public GameInfo(MoveIdentity moveIdentity, int secondPlayerId) {
        this.moveIdentity = moveIdentity;
        this.secondPlayerId = secondPlayerId;
    }

    public int getSecondPlayerId() {
        return secondPlayerId;
    }

    public MoveIdentity getMoveIdentity() {
        return moveIdentity;
    }
}
