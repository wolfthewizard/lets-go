package contract;

import contract.enums.ResponseType;
import contract.gamerecord.GameRecord;

import java.util.List;

public class ResponseDTO {
    private ResponseType responseType;
    private List<Change> changes;
    private ResponsePrisoners responsePrisoners;
    private GameRecord gameRecord;
    private int gameId;

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
        this.responseType = responseType;
    }

    public ResponseDTO(ResponseType responseType, int gameId) {
        this.responseType = responseType;
        this.gameId = gameId;
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

    public int getGameId() {
        return gameId;
    }
}
