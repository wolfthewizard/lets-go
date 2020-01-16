package core.model.entities;

import contract.Coordinates;
import contract.enums.Occupancy;

public class ChangeEntity {
    private int turnId;
    private Occupancy occupancy;
    private Coordinates coordinates;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getTurnId() {
        return turnId;
    }

    public Occupancy getOccupancy() {
        return occupancy;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setOccupancy(Occupancy occupancy) {
        this.occupancy = occupancy;
    }

    public void setTurnId(int turnId) {
        this.turnId = turnId;
    }
}

