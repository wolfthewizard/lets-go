package core.contract;

import core.contract.enums.ActionType;
import core.contract.enums.BoardSize;

public class ActionDTO {
    private ActionType actionType;
    private BoardSize boardSize;
    private Coordinates coordinates;

    public ActionDTO(boolean isMultiplayer, BoardSize boardSize){
        if(isMultiplayer) {
            actionType = ActionType.STARTMULTIPLAYERGAME;
        }
        else {
            actionType = ActionType.STARTBOTGAME;
        }
        this.boardSize = boardSize;
    }

    public ActionDTO(Coordinates coordinates){
        actionType=ActionType.DOMOVE;
        this.coordinates=coordinates;
    }

    public ActionDTO(ActionType actionType){

        if (actionType==ActionType.LEAVEGAME || actionType==ActionType.PASSMOVE) {
            this.actionType = actionType;
        }
    }

    public ActionType getActionType() {
        return actionType;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
