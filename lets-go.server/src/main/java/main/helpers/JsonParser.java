package main.helpers;

import com.google.gson.Gson;

import main.contract.ActionDTO;
import main.contract.ResponseDTO;

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
