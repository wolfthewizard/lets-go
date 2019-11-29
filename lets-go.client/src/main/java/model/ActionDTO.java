package model;

import model.enums.ActionType;
import model.enums.BoardSize;

public class ActionDTO {
    public ActionType actionType;
    public int playerId;
    public BoardSize boardSize;
    public boolean isMultiplayer;
    public Coordinates coordinates;

    public ActionDTO(boolean isMultiplayer, BoardSize boardSize){
        actionType = ActionType.STARTGAME;
        this.boardSize = boardSize;
        this.isMultiplayer=isMultiplayer;
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
