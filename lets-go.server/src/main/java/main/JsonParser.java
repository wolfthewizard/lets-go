package main;

import com.google.gson.Gson;

import main.model.ActionDTO;
import main.model.ResponseDTO;

public class JsonParser implements IJsonParser {

    Gson jsonParser;

    public JsonParser() {
        jsonParser = new Gson();
    }

    public ActionDTO parseJsonToAction(String json) {
        return jsonParser.fromJson(json, ActionDTO.class);
    }

    public String parseResponseToJson(ResponseDTO responseDTO) {
        return jsonParser.toJson(responseDTO);
    }
}
