package main.model;

public class GameWithPlayers {
    private final int whiteId;
    private final int blackId;
    private final int gameId;

    public GameWithPlayers(int whiteId, int blackId, int gameId) {
        this.whiteId =whiteId;
        this.blackId=blackId;
        this.gameId=gameId;
    }

    public int getWhiteId() {
        return whiteId;
    }

    public int getBlackid() {
        return blackId;
    }

    public int getGameId() {
        return gameId;
    }
}
