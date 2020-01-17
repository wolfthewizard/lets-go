package contract.gamerecord;

import contract.enums.BoardSize;
import contract.enums.Winner;

import java.util.Date;
import java.util.List;

public class GameRecord {
    private BoardSize boardSize;
    private Winner winner;
    private Date gameDate;
    private List<TurnRecord> turns;

    public GameRecord(BoardSize boardSize, Winner winner,
                      Date gameDate, List<TurnRecord> turns)
    {
        this.boardSize = boardSize;
        this.winner = winner;
        this.gameDate = gameDate;
        this.turns = turns;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public List<TurnRecord> getTurns() {
        return turns;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public Winner getWinner() {
        return winner;
    }
}
