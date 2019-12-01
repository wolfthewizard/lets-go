package main.contract;

import core.model.MoveExecution;

public class ResponseDTO {
    public ResponseType responseType;
    public MoveExecution moveExecution;
    public int gameId;

    public ResponseDTO(MoveExecution moveExecution) {
        responseType = ResponseType.MOVEEXECUTED;
    }

    public ResponseDTO(ResponseType responseType) {
        if (responseType == ResponseType.INVALIDMOVE || responseType == ResponseType.SERVERERROR)
        {
            this.responseType =responseType;
        }
    }

    public ResponseDTO(int gameId) {
        responseType = ResponseType.SUCCESS;
        this.gameId = gameId;
    }
}
