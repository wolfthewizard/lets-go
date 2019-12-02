package core.model;

import core.model.enums.Occupancy;
import main.contract.Coordinates;

public class Change {
    private Occupancy occupancy;
    private Coordinates coordinates;

    public Change(Occupancy occupancy, Coordinates coordinates) {
        this.occupancy = occupancy;
        this.coordinates = coordinates;
    }

    public Occupancy getOccupancy() {
        return occupancy;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
