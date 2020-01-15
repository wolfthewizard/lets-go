package contract.gamerecord;

import contract.Change;

import java.util.ArrayList;

public class TurnRecord {
    private int turnNumber;
    private ArrayList<Change> changes;

    public TurnRecord(int turnNumber, ArrayList<Change> changes)
    {
        this.turnNumber = turnNumber;
        this.changes = changes;
    }

    public ArrayList<Change> getChanges() {
        return changes;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
