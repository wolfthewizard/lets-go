package core.contract;

import core.contract.enums.ActionType;
import core.contract.enums.BoardSize;

public class ActionDTO {
    public ActionType actionType;
    public BoardSize boardSize;
    public Coordinates coordinates;

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
}
