package main;

import contract.enums.Occupancy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IconFlyweightTest {

    @Test
    public void getIcon_returnsIcon_whenGivenBlackOccupancy() {

        assertNotNull(IconFlyweight.getIcon(Occupancy.BLACK));
    }

    @Test
    public void getIcon_returnsIcon_whenGivenWhiteOccupancy() {

        assertNotNull(IconFlyweight.getIcon(Occupancy.WHITE));
    }

    @Test
    public void getIcon_returnsIcon_whenGivenEmptyOccupancy() {

        assertNotNull(IconFlyweight.getIcon(Occupancy.EMPTY));
    }
}
