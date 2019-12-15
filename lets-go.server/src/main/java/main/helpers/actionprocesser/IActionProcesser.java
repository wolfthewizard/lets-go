package main.helpers.actionprocesser;

public interface IActionProcesser {

    void processAction(String action, int id);

    void closeAllConnections();
}
