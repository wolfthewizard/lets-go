package main;

import contract.Coordinates;
import contract.enums.Occupancy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileButtonTest {

    private TileButton tileButton;

    private Coordinates cords;

    @BeforeEach
    void setup() {

        cords = new Coordinates(0, 0);
        tileButton = new TileButton(cords);
    }

    @Test
    public void getCoordinates_returnsCords() {

        assertEquals(cords.getX(), tileButton.getCoordinates().getX());
        assertEquals(cords.getY(), tileButton.getCoordinates().getY());
    }

    @Test
    public void getDimension_returnsDimension() {

        assertEquals(35, tileButton.getDimension());
    }

    @Test
    public void setImg_doesntThrow_whenGivenBlack() {

        assertDoesNotThrow(() -> tileButton.setImg(Occupancy.BLACK));
    }

    @Test
    public void setImg_doesntThrow_whenGivenWhite() {

        assertDoesNotThrow(() -> tileButton.setImg(Occupancy.WHITE));
    }

    @Test
    public void setImg_doesntThrow_whenGivenEmpty() {

        assertDoesNotThrow(() -> tileButton.setImg(Occupancy.EMPTY));
    }
}
