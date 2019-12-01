package main.helpers;


import main.contract.ActionDTO;
import main.contract.ResponseDTO;

public interface IJsonParser {

    ActionDTO parseJsonToAction(String json);

    String parseResponseToJson(ResponseDTO responseDTO);
}
