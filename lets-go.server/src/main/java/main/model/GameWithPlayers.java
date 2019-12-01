package main.model;

public class GameWithPlayers {
    public int whiteId;
    public int blackId;
    public int gameId;

    public GameWithPlayers(int whiteId, int blackId, int gameId) {
        this.whiteId =whiteId;
        this.blackId=blackId;
        this.gameId=gameId;
    }
}
