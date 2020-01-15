package contract;

import contract.enums.ResponseType;
import contract.gamerecord.GameRecord;

import java.util.List;

public class ResponseDTO {
    private ResponseType responseType;
    private List<Change> changes;
    private ResponsePrisoners responsePrisoners;
    private GameRecord gameRecord;

    public ResponseDTO(List<Change> changes, ResponsePrisoners responsePrisoners) {

        responseType = ResponseType.MOVE_EXECUTED;
        this.changes = changes;
        this.responsePrisoners = responsePrisoners;
    }

    public ResponseDTO(GameRecord gameRecord) {

        responseType = ResponseType.GAME_GOT;
        this.gameRecord = gameRecord;
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

    public GameRecord getGameRecord() {
        return gameRecord;
    }
}
