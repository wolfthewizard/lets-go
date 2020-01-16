package core.model.entities;

import contract.Coordinates;
import contract.enums.Occupancy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ChangeEntity {
    private Occupancy occupancy;
    private Coordinates coordinates;

    private int turnId;
    private TurnEntity turnEntity;


    public Coordinates getCoordinates() {
        return coordinates;
    }

    public int getTurnId() {
        return turnId;
    }

    public Occupancy getOccupancy() {
        return occupancy;
    }

    @ManyToOne
    @JoinColumn(name = "id")
    public TurnEntity getTurnEntity() {
        return turnEntity;
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

    public void setTurnEntity(TurnEntity turnEntity) {
        this.turnEntity = turnEntity;
    }
}

