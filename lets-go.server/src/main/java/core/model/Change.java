package core.model;

import core.model.enums.Occupation;
import main.contract.Coordinates;

public class Change {
    private Occupation occupation;
    private Coordinates coordinates;

    public Change(Occupation occupation, Coordinates coordinates) {
        this.occupation = occupation;
        this.coordinates = coordinates;
    }

    public Occupation getOccupation() {
        return occupation;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
