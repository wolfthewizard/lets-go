package contract;

import contract.enums.ResponseType;

import java.util.ArrayList;

public class ResponseDTO {
    private ResponseType responseType;
    private ArrayList<Change> changes;
    private Prisoners prisoners;

    public ResponseDTO(ArrayList<Change> changes, Prisoners prisoners) {

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

    public Prisoners getPrisoners() {
        return prisoners;
    }
}
