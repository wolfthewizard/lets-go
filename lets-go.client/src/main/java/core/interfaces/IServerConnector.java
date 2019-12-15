package core.interfaces;

public interface IServerConnector {
    void setServerResponseListener(IServerResponseListener serverResponseListener);

    void sendMessage(String message);

    void shutDown();
}
