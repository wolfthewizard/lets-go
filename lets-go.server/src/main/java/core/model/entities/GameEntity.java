package core.model.entities;

import contract.enums.BoardSize;
import contract.enums.Winner;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Games")
public class GameEntity {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private BoardSize boardSize;

    @Enumerated(EnumType.ORDINAL)
    private Winner winner;

    private Date gameDate;

    @OneToMany(mappedBy = "gameEntity", cascade = CascadeType.ALL)
    private List<TurnEntity> turnEntities;

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
