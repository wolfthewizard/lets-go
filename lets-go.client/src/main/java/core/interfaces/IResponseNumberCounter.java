package core.interfaces;

import contract.enums.ResponseType;

public interface IResponseNumberCounter {

    int getNumberOfResponsesToRead();

    void addNumberOfResponsesToRead(int add);

    void countResponse(ResponseType responseType);
}
