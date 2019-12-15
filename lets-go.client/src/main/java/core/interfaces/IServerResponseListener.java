package core.interfaces;

import contract.ResponseDTO;
import core.interfaces.IResponseNumberCounter;

public interface IServerResponseListener {

    void responseReceived(String response);

    void setResponseNumberCounter(IResponseNumberCounter responseNumberCounter);

    void passResponseDTO(ResponseDTO responseDTO);
}
