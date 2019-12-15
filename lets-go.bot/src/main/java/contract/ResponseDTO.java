package contract;

import contract.enums.ResponseType;

import java.util.ArrayList;

public class ResponseDTO {
    private ResponseType responseType;
    private ArrayList<Change> changes;
    private ResponsePrisoners responsePrisoners;

    public ResponseDTO(ArrayList<Change> changes, ResponsePrisoners responsePrisoners) {

        responseType = ResponseType.MOVE_EXECUTED;
        this.changes = changes;
        this.responsePrisoners = responsePrisoners;
    }

    public ResponseDTO(ResponseType responseType) {
        this.responseType = responseType;
    }


    public ArrayList<Change> getChanges() {
        return changes;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public ResponsePrisoners getResponsePrisoners() {
        return responsePrisoners;
    }
}
