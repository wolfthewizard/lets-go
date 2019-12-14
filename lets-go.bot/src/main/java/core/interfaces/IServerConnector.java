package core.interfaces;

public interface IServerConnector {

    void StartListening(ICommunicatorListener communicatorListener);

    void sendAction(String action);
}