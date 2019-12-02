package core.contract;

import core.contract.enums.ResponseType;

import java.util.ArrayList;

public class ResponseDTO {

    private ResponseType responseType;
    private ArrayList<Change> changes;

    public ResponseDTO(ArrayList<Change> changes) {
        responseType = ResponseType.MOVE_EXECUTED;
        this.changes = changes;
    }

    public ResponseDTO(ResponseType responseType) {
        this.responseType =responseType;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public ArrayList<Change> getChanges() {
        return changes;
    }
}
