package core.interfaces;

import java.io.IOException;

public interface IServerConnector {
    void resetConnection() throws IOException;
    void resetConnection(String connectionAddress) throws  IOException;

    void setServerResponseListener(IServerResponseListener serverResponseListener);

    void sendMessage(String message);

    void shutDown();
}
