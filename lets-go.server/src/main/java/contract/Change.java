package contract;

<<<<<<< HEAD:lets-go.server/src/main/java/contract/Change.java
import contract.enums.Occupation;
=======
import core.contract.enums.Occupancy;
>>>>>>> dfe73ce7090a5906f19515da3a8ffb670390130a:lets-go.client/src/main/java/core/contract/Change.java

public class Change {

    private Occupancy occupancy;
    private Coordinates coordinates;

<<<<<<< HEAD:lets-go.server/src/main/java/contract/Change.java
    public Change(Occupation occupation, Coordinates coordinates) {
        this.occupation = occupation;
=======
    public Change(Occupancy occupancy, Coordinates coordinates) {
        this.occupancy = occupancy;
>>>>>>> dfe73ce7090a5906f19515da3a8ffb670390130a:lets-go.client/src/main/java/core/contract/Change.java
        this.coordinates = coordinates;
    }

    public Occupancy getOccupancy() {
        return occupancy;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
