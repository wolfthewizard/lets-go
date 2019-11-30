package main;

import main.model.ActionDTO;

public class ActionProcesser implements IActionProcesser{

    private final IJsonParser jsonParser;

    public ActionProcesser(IJsonParser jsonParser) {

        this.jsonParser=jsonParser;
    }

    public String ProcessAction(String message) {

        System.out.println("got:"+message);
        ActionDTO action = jsonParser.parseJsonToAction(message);

        switch(action.actionType)//java 7 needed
        {
            case STARTGAME:


            case PASSMOVE:


            case DOMOVE:


            case LEAVEGAME:

        }
        return null;
    }
}
