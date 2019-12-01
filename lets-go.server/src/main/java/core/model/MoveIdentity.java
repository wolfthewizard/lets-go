package core.model;

import main.Color;

public class MoveIdentity {
    public Color playerColor;
    public int gameId;

    public MoveIdentity(Color playerColor, int gameId) {
        this.playerColor=playerColor;
        this.gameId=gameId;
    }
}