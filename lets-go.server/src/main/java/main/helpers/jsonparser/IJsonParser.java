package main.helpers.jsonparser;


import contract.ActionDTO;
import contract.ResponseDTO;

public interface IJsonParser {

    ActionDTO parseJsonToAction(String json);

    String parseResponseToJson(ResponseDTO responseDTO);
}
