package core.model.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Turns")
public class TurnEntity {

    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;


    @Column(name="turnNumber")
    private int turnNumber;

    @ManyToOne
    @JoinColumn(name = "gameId")
    private GameEntity gameEntity;

    @OneToMany(mappedBy = "turnEntity", cascade = CascadeType.ALL)
    private List<ChangeEntity> changeEntities = new ArrayList<>();

    public int getId() {
        return id;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public List<ChangeEntity> getChangeEntities() {
        return changeEntities;
    }

    public GameEntity getGameEntity() {
        return gameEntity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTurnNumber(int turnNumber) {
        this.turnNumber = turnNumber;
    }

    public void setChangeEntities(List<ChangeEntity> changeEntities) {
        this.changeEntities = changeEntities;
    }

    public void setGameEntity(GameEntity gameEntity) {
        this.gameEntity = gameEntity;
    }
}
