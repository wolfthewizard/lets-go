package core.serversender;

import contract.enums.ResponseType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResponseNumberCounterTest {

    private ResponseNumberCounter responseNumberCounter;

    @BeforeEach
    void setup() {

        responseNumberCounter = new ResponseNumberCounter();
    }

    @Test
    public void getNumberOfResponsesToRead_returnsZero_whenItsCalledAtTheStart() {

        assertEquals(0, responseNumberCounter.getNumberOfResponsesToRead());
    }

    @Test
    public void addNumberOfResponsesToRead_returnsTen_whenCalledAfterAddingTen() {

        responseNumberCounter.addNumberOfResponsesToRead(10);

        assertEquals(10, responseNumberCounter.getNumberOfResponsesToRead());
    }

    @Test
    public void countResponse_decreasesNOfResponsesByTwo_whenGivenInvalidMove() {

        responseNumberCounter.countResponse(ResponseType.INVALID_MOVE);

        assertEquals(-2, responseNumberCounter.getNumberOfResponsesToRead());
    }

    @Test
    public void countResponse_decreasesNOfResponsesByOne_else() {

        responseNumberCounter.countResponse(ResponseType.SERVER_ERROR);

        assertEquals(-1, responseNumberCounter.getNumberOfResponsesToRead());
    }
}
