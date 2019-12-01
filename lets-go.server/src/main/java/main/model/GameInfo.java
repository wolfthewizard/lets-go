package main.model;

import core.model.MoveIdentity;

public class GameInfo {
    public MoveIdentity moveIdentity;
    public int secondPlayerId;

    public GameInfo(MoveIdentity moveIdentity, int secondPlayerId) {
        this.moveIdentity = moveIdentity;
        this.secondPlayerId = secondPlayerId;
    }
}
