package core.model;

import contract.enums.BoardSize;

public class Game {

    private int id;
    private Board board;
    private BoardSize boardSize;
    private Color playersTurn;
    private boolean lastTurnPassed;

    public Game(int id, Board board, BoardSize boardSize) {

        this.id = id;
        this.boardSize = boardSize;
        this.playersTurn = Color.BLACK;
        this.board = board;
        lastTurnPassed = false;
    }

    public int getId() {
        return id;
    }

    public Color getPlayersTurn() {
        return playersTurn;
    }

    public Board getBoard() {
        return board;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public boolean isLastTurnPassed() {
        return lastTurnPassed;
    }
}
