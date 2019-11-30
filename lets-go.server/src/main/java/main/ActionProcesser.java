package main;

import main.enums.ActionType;
import main.model.ActionDTO;

public class ActionProcesser implements IActionProcesser{

    private final IJsonParser jsonParser;

    public ActionProcesser(IJsonParser jsonParser) {

        this.jsonParser=jsonParser;
    }

    public String ProcessAction(String message) {

        ActionDTO action = jsonParser.parseJsonToAction(message);

        switch(action.actionType)//java 7 needed
        {
            case ActionType.STARTGAME:


            case ActionType.PASSMOVE:


            case ActionType.DOMOVE:


            case ActionType.LEAVEGAME:

        }
    }
}
