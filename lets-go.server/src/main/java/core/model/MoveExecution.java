package core.model;

import contract.Change;
import contract.ResponsePrisoners;

import java.util.ArrayList;
import java.util.List;

public class MoveExecution {

    private List<Change> changes;
    private ResponsePrisoners responsePrisoners;

    public MoveExecution(List<Change> changes, ResponsePrisoners responsePrisoners) {
        this.changes = changes;
        this.responsePrisoners = responsePrisoners;
    }

    public List<Change> getChanges() {
        return changes;
    }

    public ResponsePrisoners getResponsePrisoners() {
        return responsePrisoners;
    }
}
