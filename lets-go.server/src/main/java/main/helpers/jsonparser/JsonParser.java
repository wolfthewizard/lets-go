package main.helpers.jsonparser;

import com.google.gson.Gson;

import contract.ActionDTO;
import contract.ResponseDTO;

public class JsonParser implements IJsonParser {

    private Gson jsonParser;

    public JsonParser() {
        jsonParser = new Gson();
    }

    @Override
    public ActionDTO parseJsonToAction(String json) {
        return jsonParser.fromJson(json, ActionDTO.class);
    }

    @Override
    public String parseResponseToJson(ResponseDTO responseDTO) {
        return jsonParser.toJson(responseDTO);
    }
}
