package main.helpers.actionprocesser;

public interface IActionProcesser {

    void ProcessAction(String action, int id);

    void closeAllConnections();
}
