package core.model.entities;

import contract.enums.Occupancy;

import javax.persistence.*;

@Entity
@Table(name = "Changes")
public class ChangeEntity {

    @Id
    private int id;

    private int x;

    private int y;

    @Enumerated(EnumType.ORDINAL)
    private Occupancy occupancy;

    @ManyToOne
    @JoinColumn(name = "turnId")
    private TurnEntity turnEntity;


    public int getId() {
        return id;
    }

    public TurnEntity getTurnEntity() {
        return turnEntity;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Occupancy getOccupancy() {
        return occupancy;
    }

    public void setTurnEntity(TurnEntity turnEntity) {
        this.turnEntity = turnEntity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setOccupancy(Occupancy occupancy) {
        this.occupancy = occupancy;
    }
}

