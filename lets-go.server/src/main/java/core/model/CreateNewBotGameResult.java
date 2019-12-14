package core.model;

import contract.Change;
import contract.Prisoners;

import java.util.ArrayList;

public class CreateNewBotGameResult {

    private int gameId;
    private ArrayList<Change> changes;
    private Prisoners prisoners;

    public CreateNewBotGameResult(int gameId, ArrayList<Change> changes, Prisoners prisoners) {
        this.gameId = gameId;
        this.changes = changes;
        this.prisoners = prisoners;
    }

    public int getGameId() {
        return gameId;
    }

    public ArrayList<Change> getChanges() {
        return changes;
    }

    public Prisoners getPrisoners() {
        return prisoners;
    }
}
