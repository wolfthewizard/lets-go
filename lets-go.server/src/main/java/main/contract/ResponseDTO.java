package main.contract;

import core.model.MoveExecution;
import main.contract.enums.ResponseType;

public class ResponseDTO {
    public ResponseType responseType;
    public MoveExecution moveExecution;

    public ResponseDTO(MoveExecution moveExecution) {

        responseType = ResponseType.MOVEEXECUTED;
        this.moveExecution = moveExecution;
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
}
