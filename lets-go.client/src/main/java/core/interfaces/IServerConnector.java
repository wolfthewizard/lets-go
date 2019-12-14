package core.interfaces;

import core.serversender.OnServerResponseListener;

public interface IServerConnector {
    void setServerResponseListener(OnServerResponseListener serverResponseListener);

    void sendMessage(String message);

    void shutDown();
}
