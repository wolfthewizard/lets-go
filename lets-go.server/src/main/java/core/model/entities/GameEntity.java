package core.model.entities;

import contract.enums.BoardSize;
import contract.enums.Winner;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Games")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="boardSize")
    private BoardSize boardSize;

    @Enumerated(EnumType.ORDINAL)
    @Column(name="winner")
    private Winner winner;

    @Column(name="gameDate")
    private Date gameDate;

    @OneToMany(mappedBy = "gameEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TurnEntity> turnEntities = new ArrayList<>();

    public int getId() {
        return id;
    }

    public Winner getWinner() {
        return winner;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public List<TurnEntity> getTurnEntities() {
        return turnEntities;
    }

    public void setBoardSize(BoardSize boardSize) {
        this.boardSize = boardSize;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }

    public void setTurnEntities(List<TurnEntity> turnEntities) {
        this.turnEntities = turnEntities;
    }
}
