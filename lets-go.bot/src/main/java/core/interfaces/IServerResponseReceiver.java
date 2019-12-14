package core.interfaces;

import contract.ResponseDTO;

public interface IServerResponseReceiver {
    void responseReceived(ResponseDTO response);
}
