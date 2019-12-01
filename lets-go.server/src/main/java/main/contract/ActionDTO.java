package main.contract;

public class ActionDTO {
    public ActionType actionType;
    public int playerId;
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

    public ActionDTO(int playerId, Coordinates coordinates){
        actionType=ActionType.DOMOVE;
        this.coordinates=coordinates;
    }

    public ActionDTO(int playerId, ActionType actionType){

        if (actionType==ActionType.LEAVEGAME || actionType==ActionType.PASSMOVE) {
            this.actionType = actionType;
            this.playerId = playerId;
        }
    }
}
