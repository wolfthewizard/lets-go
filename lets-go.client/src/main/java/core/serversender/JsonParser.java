package core.serversender;

import com.google.gson.Gson;
import contract.ActionDTO;
import contract.ResponseDTO;
import core.interfaces.IJsonParser;


public class JsonParser implements IJsonParser {

    private Gson jsonParser;

    public JsonParser() {
        jsonParser = new Gson();
    }

    public ResponseDTO parseJsonToResponse(String json) {
        return jsonParser.fromJson(json, ResponseDTO.class);
    }

    public String parseActionToJson(ActionDTO actionDTO) {
        return jsonParser.toJson(actionDTO);
    }
}
