package main;

import main.enums.ActionType;
import main.model.ActionDTO;

public interface IActionProcesser {

    String ProcessAction(String action);
}
