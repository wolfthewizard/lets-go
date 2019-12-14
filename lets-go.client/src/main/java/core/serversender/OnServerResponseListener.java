package core.serversender;

import contract.ResponseDTO;
import core.interfaces.IResponseNumberCounter;

public interface OnServerResponseListener {

    void responseReceived(String response);

    void setResponseNumberCounter(IResponseNumberCounter responseNumberCounter);

    void passResponseDTO(ResponseDTO responseDTO);
}
