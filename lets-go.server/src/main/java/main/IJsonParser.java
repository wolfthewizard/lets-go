package main;


import main.model.ActionDTO;
import main.model.ResponseDTO;

public interface IJsonParser {

    ActionDTO parseJsonToAction(String json);

    String parseResponseToJson(ResponseDTO responseDTO);
}
