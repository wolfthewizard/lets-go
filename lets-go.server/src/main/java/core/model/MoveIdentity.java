package core.model;

import main.model.Color;

public class MoveIdentity {
    private Color playerColor;
    private int gameId;

    public MoveIdentity(Color playerColor, int gameId) {
        this.playerColor=playerColor;
        this.gameId=gameId;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public int getGameId() {
        return gameId;
    }
}
