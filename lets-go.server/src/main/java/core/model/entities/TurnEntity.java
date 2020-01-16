package core.model.entities;

public class TurnEntity {
    private int gameId;
    private int Id;
    private int turnNumber;

    public int getGameId() {
        return gameId;
    }

    public int getId() {
        return Id;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }
}
