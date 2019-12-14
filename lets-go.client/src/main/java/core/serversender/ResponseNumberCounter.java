package core.serversender;

import contract.enums.ResponseType;
import core.interfaces.IResponseNumberCounter;

public class ResponseNumberCounter implements IResponseNumberCounter {

    private int responsesToRead = 0;

    public int getNumberOfResponsesToRead() {
        return responsesToRead;
    }

    public void addNumberOfResponsesToRead(int add) {
        responsesToRead += add;
    }

    public void countResponse(ResponseType responseType) {
        if (responseType == ResponseType.INVALID_MOVE) {
            responsesToRead -= 2;
        } else {
            responsesToRead--;
        }
    }
}
