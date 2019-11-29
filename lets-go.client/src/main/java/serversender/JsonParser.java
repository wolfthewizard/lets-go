package serversender;

import com.google.gson.Gson;

import model.ActionDTO;
import model.ResponseDTO;

public class JsonParser {

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
