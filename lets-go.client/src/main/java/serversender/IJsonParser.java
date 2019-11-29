package serversender;

import model.ActionDTO;
import model.ResponseDTO;

public interface IJsonParser {
    ResponseDTO parseJsonToResponse(String json);

    String parseActionToJson(ActionDTO actionDTO);
}
