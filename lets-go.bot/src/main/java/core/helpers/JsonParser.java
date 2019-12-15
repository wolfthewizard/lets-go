package core.helpers;

import com.google.gson.Gson;
import contract.ActionDTO;
import contract.ResponseDTO;
import core.interfaces.IJsonParser;


public class JsonParser implements IJsonParser {

    private Gson jsonParser;

    public JsonParser() {
        jsonParser = new Gson();
    }

    @Override
    public ResponseDTO parseJsonToResponse(String json) {
        return jsonParser.fromJson(json, ResponseDTO.class);
    }

    @Override
    public String parseActionToJson(ActionDTO actionDTO) {
        return jsonParser.toJson(actionDTO);
    }
}
