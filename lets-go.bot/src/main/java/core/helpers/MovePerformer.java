package core.helpers;

import contract.Coordinates;
import contract.enums.BoardSize;
import contract.enums.Occupancy;
import core.interfaces.IMovePerformer;
import core.model.Color;

import java.util.Random;

public class MovePerformer implements IMovePerformer {

    private Random random = new Random();
    private int recurencyDepth;

    @Override
    public Coordinates performMove(Occupancy[][] board, BoardSize boardSize, Color colorOfMovingPlayer) {

        for (int i=0; i< boardSize.getValue(); i++){
            for (int j =0; j<boardSize.getValue(); j++){
                if(board[i][j] == colorOfMovingPlayer.reverse().toOccupancy()){
                    recurencyDepth=5;
                    Coordinates cords = checkIfEnemyHasOneBreath(new Coordinates(i,j), board, boardSize, colorOfMovingPlayer);
                    if(cords!= null)
                    {
                        return cords;
                    }
                }
            }
        }
        return new Coordinates(random.nextInt(boardSize.getValue()), random.nextInt(boardSize.getValue()));
    }

    private Coordinates checkIfEnemyHasOneBreath(Coordinates coordinates, Occupancy[][] board,
                                                 BoardSize boardSize, Color colorOfMovingPlayer){
        int breathCount =0;
        Coordinates lastCords=null;

        if(coordinates.getY()+1<boardSize.getValue())
        {
            if(board[coordinates.getX()][coordinates.getY()+1]==Occupancy.EMPTY){
                breathCount++;
                lastCords = new Coordinates(coordinates.getX(), coordinates.getY()+1);
            }
        }
        if(coordinates.getX()+1<boardSize.getValue())
        {
            if(board[coordinates.getX()+1][coordinates.getY()]==Occupancy.EMPTY){
                breathCount++;
                lastCords = new Coordinates(coordinates.getX() + 1, coordinates.getY());
            }
        }
        if(coordinates.getY()-1>0)
        {
            if(board[coordinates.getX()][coordinates.getY()-1]==Occupancy.EMPTY){
                breathCount++;
                lastCords = new Coordinates(coordinates.getX(), coordinates.getY() - 1);
            }
        }
        if(coordinates.getX()-1>0)
        {
            if(board[coordinates.getX()-1][coordinates.getY()]==Occupancy.EMPTY){
                breathCount++;
                lastCords = new Coordinates(coordinates.getX() -1, coordinates.getY());
            }
        }

        if (breathCount == 1){
            return lastCords;
        }

        return null;
    }
}
