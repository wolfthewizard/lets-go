package main.helpers;

import core.ICommandDirector;
import core.model.Move;
import core.model.MoveExecution;
import core.model.MoveIdentity;
import javafx.util.Pair;
import main.contract.ActionDTO;
import main.contract.ResponseDTO;
import main.contract.enums.ResponseType;

import java.util.Random;

public class ActionProcesser implements IActionProcesser {

    private final IJsonParser jsonParser;
    private final IPlayerValidator playerValidator;
    private final ICommandDirector commandDirector;
    private final Random randomGenerator=new Random();

    public ActionProcesser(IJsonParser jsonParser, IPlayerValidator playerValidator,
                           ICommandDirector commandDirector) {

        this.jsonParser=jsonParser;
        this.playerValidator = playerValidator;
        this.commandDirector = commandDirector;
    }

    public String ProcessAction(String message, int threadId) {

        ActionDTO action = jsonParser.parseJsonToAction(message);

        MoveIdentity moveIdentity;
        MoveExecution moveExecution;

        switch(action.actionType)
        {
            case STARTBOTGAME:

                if(playerValidator.getMoveIdentity(threadId) != null) {
                    return jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.CANTCREATEGAME));
                }
                Pair<Integer, MoveExecution> idWithMove;
                if (randomGenerator.nextBoolean()){
                    idWithMove = commandDirector.CreateNewBotGame(true, action.boardSize);
                    playerValidator.addNewGame(threadId, 0, idWithMove.getKey());
                }
                else {
                    idWithMove = commandDirector.CreateNewBotGame(false, action.boardSize);
                    playerValidator.addNewGame(0, threadId, idWithMove.getKey());
                }
                System.out.println(threadId);
                return jsonParser.parseResponseToJson(new ResponseDTO());

            case STARTMULTIPLAYERGAME:

                if(playerValidator.getMoveIdentity(threadId) != null) {
                    return jsonParser.parseResponseToJson(new ResponseDTO(ResponseType.CANTCREATEGAME));
                }

                commandDirector.CreateNewMultiplayerGame();
                //later


            case PASSMOVE:

                moveIdentity =playerValidator.getMoveIdentity(threadId);

                 moveExecution= commandDirector.TryToMove(new Move(moveIdentity, null));

                return jsonParser.parseResponseToJson(new ResponseDTO(moveExecution));

            case DOMOVE:
                
                 moveIdentity=playerValidator.getMoveIdentity(threadId);

                 moveExecution = commandDirector.TryToMove(new Move(moveIdentity, action.coordinates));

                 return jsonParser.parseResponseToJson(new ResponseDTO(moveExecution));

            case LEAVEGAME:

                moveIdentity =playerValidator.getMoveIdentity(threadId);

                playerValidator.removeGame(moveIdentity.gameId);

                commandDirector.CancelGame(moveIdentity);

            default:
                return null;
        }
    }
}
