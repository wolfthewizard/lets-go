package core.interfaces;


import contract.ActionDTO;
import contract.ResponseDTO;

public interface IJsonParser {
    ResponseDTO parseJsonToResponse(String json);

    String parseActionToJson(ActionDTO actionDTO);
}
