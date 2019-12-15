package contract;

import contract.enums.ResponseType;

import java.util.List;

public class ResponseDTO {
    private ResponseType responseType;
    private List<Change> changes;
    private ResponsePrisoners responsePrisoners;

    public ResponseDTO(List<Change> changes, ResponsePrisoners responsePrisoners) {

        responseType = ResponseType.MOVE_EXECUTED;
        this.changes = changes;
        this.responsePrisoners = responsePrisoners;
    }

    public ResponseDTO(ResponseType responseType) {
            this.responseType =responseType;
    }


    public List<Change> getChanges() {
        return changes;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public ResponsePrisoners getResponsePrisoners() {
        return responsePrisoners;
    }
}
