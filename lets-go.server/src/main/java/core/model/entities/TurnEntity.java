package core.model.entities;


import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class TurnEntity {
    private int id;
    private int turnNumber;

    private GameEntity gameEntity;
    private ArrayList<ChangeEntity> changeEntities;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    @OneToMany(mappedBy = "turnEntity", cascade = CascadeType.ALL)
    public ArrayList<ChangeEntity> getChangeEntities() {
        return changeEntities;
    }

    @ManyToOne
    @JoinColumn(name = "id")
    public GameEntity getGameEntity() {
        return gameEntity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void setChangeEntities(ArrayList<ChangeEntity> changeEntities) {
        this.changeEntities = changeEntities;
    }

    public void setGameEntity(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }
}
