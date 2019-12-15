package main.helpers.jsonparser;

import com.google.gson.Gson;

import contract.ActionDTO;
import contract.ResponseDTO;
import main.helpers.jsonparser.IJsonParser;

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