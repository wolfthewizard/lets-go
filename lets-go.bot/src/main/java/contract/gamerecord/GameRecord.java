package contract.gamerecord;

import contract.enums.BoardSize;
import contract.enums.Winner;

import java.util.ArrayList;
import java.util.Date;

public class GameRecord {
    private BoardSize boardSize;
    private Winner winner;
    private Date gameDate;
    private ArrayList<TurnRecord> turns;

    public GameRecord(BoardSize boardSize, Winner winner,
                      Date gameDate, ArrayList<TurnRecord> turns)
    {
        this.boardSize = boardSize;
        this.winner = winner;
        this.gameDate = gameDate;
        this.turns = turns;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public ArrayList<TurnRecord> getTurns() {
        return turns;
    }

    public Date getGameDate() {
        return gameDate;
    }

    public Winner getWinner() {
        return winner;
    }
}
