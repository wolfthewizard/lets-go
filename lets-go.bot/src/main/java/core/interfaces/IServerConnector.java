package core.interfaces;

public interface IServerConnector {

    void startListening(ICommunicatorListener communicatorListener);

    void sendAction(String action);

    void shutDown();
}