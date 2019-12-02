package core.model;

import contract.Change;
import contract.Prisoners;

import java.util.ArrayList;

public class MoveExecution {
    private ArrayList<Change> changes;
    private Prisoners prisoners;

    public MoveExecution(ArrayList<Change> changes, Prisoners prisoners) {
        this.changes = changes;
        this.prisoners = prisoners;
    }

    public ArrayList<Change> getChanges() {
        return changes;
    }

    public Prisoners getPrisoners() {
        return prisoners;
    }
}
