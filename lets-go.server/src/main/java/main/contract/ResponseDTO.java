package main.contract;

import core.model.Change;
import main.contract.enums.ResponseType;

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


    public ArrayList<Change> getChanges() {
        return changes;
    }

    public ResponseType getResponseType() {
        return responseType;
    }
}
