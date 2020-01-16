package core.model.entities;

import contract.enums.BoardSize;
import contract.enums.Winner;

import java.util.Date;

public class GameEntity {
    private int Id;
    private BoardSize boardSize;
    private Winner winner;
    private Date gameDate;

    public int getId() {
        return Id;
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

    public void setBoardSize(BoardSize boardSize) {
        this.boardSize = boardSize;
    }

    public void setGameDate(Date gameDate) {
        this.gameDate = gameDate;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setWinner(Winner winner) {
        this.winner = winner;
    }
}
