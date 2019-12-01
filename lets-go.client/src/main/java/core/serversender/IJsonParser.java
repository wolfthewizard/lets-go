package core.serversender;

import core.contract.ActionDTO;
import core.contract.ResponseDTO;

public interface IJsonParser {
    ResponseDTO parseJsonToResponse(String json);

    String parseActionToJson(ActionDTO actionDTO);
}
