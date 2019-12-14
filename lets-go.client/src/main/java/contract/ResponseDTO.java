package contract;

import contract.enums.ResponseType;

import java.util.ArrayList;

public class ResponseDTO {
    private ResponseType responseType;
    private ArrayList<Change> changes;
    private ResponsePrisoners prisoners;

    public ResponseDTO(ArrayList<Change> changes, ResponsePrisoners prisoners) {

        responseType = ResponseType.MOVE_EXECUTED;
        this.changes = changes;
        this.prisoners=prisoners;
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

    public ResponsePrisoners getPrisoners() {
        return prisoners;
    }
}
