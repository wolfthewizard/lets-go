package contract;

import contract.enums.BoardSize;
import contract.enums.ActionType;

public class ActionDTO {

    private ActionType actionType;
    private BoardSize boardSize;
    private Coordinates coordinates;
    private int gameId;


    public ActionDTO(BoardSize boardSize){
        actionType = ActionType.STARTGAME;
        this.boardSize = boardSize;
    }

    public ActionDTO(Coordinates coordinates){
        actionType=ActionType.DOMOVE;
        this.coordinates=coordinates;
    }

    public ActionDTO(int gameId){
        actionType=ActionType.GETGAME;
        this.gameId = gameId;
    }

    public ActionDTO(ActionType actionType){

        if (actionType==ActionType.LEAVEGAME || actionType==ActionType.PASSMOVE) {
            this.actionType = actionType;
        }
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getGameId() {
        return gameId;
    }
}

