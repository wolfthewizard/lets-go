package core.contract;

import core.contract.enums.ResponseType;

public class ResponseDTO {
    public ResponseType responseType;
//    MoveExecution moveExecution;

    //public ResponseDTO(MoveExecution moveExecution) {
      //  responseType = ResponseType.MOVEEXECUTED;
    //}

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
