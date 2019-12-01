package main.helpers;

import core.ICommandDirector;
import core.model.Move;
import core.model.MoveExecution;
import core.model.MoveIdentity;
import javafx.util.Pair;
import main.contract.ActionDTO;
import main.contract.ResponseDTO;

import java.util.Random;

public class ActionProcesser implements IActionProcesser {

    private final IJsonParser jsonParser;
    private final IPlayerValidator playerValidator;
    private final ICommandDirector commandDirector;
    private final IIdGenerator idGenerator;
    private final Random randomGenerator=new Random();

    public ActionProcesser(IJsonParser jsonParser, IPlayerValidator playerValidator,
                           ICommandDirector commandDirector, IIdGenerator idGenerator) {

        this.jsonParser=jsonParser;
        this.playerValidator = playerValidator;
        this.commandDirector = commandDirector;
        this.idGenerator = idGenerator;
    }

    public String ProcessAction(String message) {

        ActionDTO action = jsonParser.parseJsonToAction(message);

        MoveIdentity moveIdentity;
        MoveExecution moveExecution;

        switch(action.actionType)
        {
            case STARTBOTGAME:

                int playerId = idGenerator.generateId();

                Pair<Integer, MoveExecution> idWithMove;
                if (randomGenerator.nextBoolean()){
                    idWithMove = commandDirector.CreateNewBotGame(true, action.boardSize);
                    playerValidator.addNewGame(playerId, 0, idWithMove.getKey());
                }
                else {
                    idWithMove = commandDirector.CreateNewBotGame(false, action.boardSize);
                    playerValidator.addNewGame(0, playerId, idWithMove.getKey());
                }
                System.out.println(playerId);
                return jsonParser.parseResponseToJson(new ResponseDTO(playerId));

            case STARTMULTIPLAYERGAME:
                commandDirector.CreateNewMultiplayerGame();
                //later


            case PASSMOVE:
                moveIdentity =playerValidator.getMoveIdentity(action.playerId);

                 moveExecution= commandDirector.TryToMove(new Move(moveIdentity, null));

                return jsonParser.parseResponseToJson(new ResponseDTO(moveExecution));
            case DOMOVE:
                 moveIdentity=playerValidator.getMoveIdentity(action.playerId);

                 moveExecution = commandDirector.TryToMove(new Move(moveIdentity, action.coordinates));

                 return jsonParser.parseResponseToJson(new ResponseDTO(moveExecution));

            case LEAVEGAME:
                moveIdentity =playerValidator.getMoveIdentity(action.playerId);
              //  playerValidator.removeGame(moveIdentity.gameId);

                commandDirector.CancelGame(moveIdentity);

            default:
                return null;
        }
    }
}
