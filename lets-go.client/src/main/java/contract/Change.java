package contract;

<<<<<<< HEAD:lets-go.client/src/main/java/contract/Change.java
import contract.enums.Occupation;
=======
import core.model.enums.Occupancy;
import main.contract.Coordinates;
>>>>>>> dfe73ce7090a5906f19515da3a8ffb670390130a:lets-go.server/src/main/java/core/model/Change.java

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
