package core.serversender;

import com.google.gson.Gson;

import core.contract.ActionDTO;
import core.contract.ResponseDTO;

public class JsonParser implements IJsonParser {

    Gson jsonParser;

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