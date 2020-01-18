package contract.gamerecord;

import contract.Change;

import java.util.List;

public class TurnRecord {
    private int turnNumber;
    private List<Change> changes;

    public TurnRecord(int turnNumber, List<Change> changes)
    {
        this.turnNumber = turnNumber;
        this.changes = changes;
    }

    public List<Change> getChanges() {
        return changes;
    }

    public int getTurnNumber() {
        return turnNumber;
    }
}
