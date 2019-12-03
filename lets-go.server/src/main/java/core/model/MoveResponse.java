package core.model;

import core.model.enums.MoveResponseType;

public class MoveResponse {
    private MoveResponseType moveResponseType;
    private MoveExecution moveExecution;

    public MoveResponse(MoveResponseType moveResponseType, MoveExecution moveExecution) {
        this.moveResponseType = moveResponseType;
        this.moveExecution = moveExecution;
    }

    public MoveResponseType getMoveResponseType() {
        return moveResponseType;
    }

    public MoveExecution getMoveExecution() {
        return moveExecution;
    }
}