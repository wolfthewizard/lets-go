package core.model;

import contract.Change;
import contract.ResponsePrisoners;

import java.util.ArrayList;

public class MoveExecution {
    private ArrayList<Change> changes;
    private ResponsePrisoners responsePrisoners;

    public MoveExecution(ArrayList<Change> changes, ResponsePrisoners responsePrisoners) {
        this.changes = changes;
        this.responsePrisoners = responsePrisoners;
    }

    public ArrayList<Change> getChanges() {
        return changes;
    }

    public ResponsePrisoners getResponsePrisoners() {
        return responsePrisoners;
    }
}
