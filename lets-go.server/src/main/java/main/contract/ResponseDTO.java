package main.contract;

import core.model.Change;
import main.contract.enums.ResponseType;

import java.util.ArrayList;

public class ResponseDTO {
    private ResponseType responseType;
    private ArrayList<Change> changes;

    public ResponseDTO(ArrayList<Change> changes) {

        responseType = ResponseType.MOVEEXECUTED;
        this.changes = changes;
    }

    public ResponseDTO(ResponseType responseType) {
        if (responseType == ResponseType.INVALIDMOVE || responseType == ResponseType.SERVERERROR)
        {
            this.responseType =responseType;
        }
    }

    public ResponseDTO() {
        responseType = ResponseType.SUCCESS;
    }

    public ArrayList<Change> getChanges() {
        return changes;
    }

    public ResponseType getResponseType() {
        return responseType;
    }
}
