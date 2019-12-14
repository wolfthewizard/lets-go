package core.model;

import contract.Prisoners;
import contract.enums.BoardSize;
import contract.enums.Occupancy;

public class Game {
    private int id;
    private Occupancy[][] board;
    private BoardSize boardSize;
    private Color playersTurn;
    private Prisoners currentPrisoners;

    public Game(int id, BoardSize boardSize, Color startingPlayer){
        this.id = id;
        this.boardSize = boardSize;
        this.playersTurn = startingPlayer;
        currentPrisoners = new Prisoners(0,0);
        board = new Occupancy[boardSize.getValue()][];
        for(int i=0; i< boardSize.getValue();i++) {
            board[i] = new Occupancy[boardSize.getValue()];
            for(int j=0;j<boardSize.getValue();j++) {
                board[i][j] = Occupancy.EMPTY;
            }
        }
    }

    public int getId() {
        return id;
    }

    public Color getPlayersTurn() {
        return playersTurn;
    }

    public Occupancy[][] getBoard() {
        return board;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public Prisoners getCurrentPrisoners() {
        return currentPrisoners;
    }

    public void setTile(int x, int y, Occupancy occupancy){
        board[x][y] = occupancy;
    }
}
