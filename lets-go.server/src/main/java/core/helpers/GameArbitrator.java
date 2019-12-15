package core.helpers;

import contract.Coordinates;
import contract.enums.Occupancy;
import core.interfaces.IGameArbitrator;
import core.interfaces.IMoveHelper;
import core.model.enums.Winner;

import java.util.ArrayList;
import java.util.List;

public class GameArbitrator implements IGameArbitrator {

    private IMoveHelper moveHelper;

    public GameArbitrator(IMoveHelper moveHelper) {
        this.moveHelper = moveHelper;
    }

    @Override
    public Winner determineWinner(Occupancy[][] board, int boardSizeValue) {

        int blacksPoints = 0;
        int whitesPoints = 0;

        List<List<Coordinates>> listOfUnoccupiedChains = new ArrayList<>();

        for (int y = 0; y < boardSizeValue; y++) {
            for (int x = 0; x < boardSizeValue; x++) {

                boolean toAdd = true;

                for (List<Coordinates> chain : listOfUnoccupiedChains) {

                    if (moveHelper.chainContains(chain, new Coordinates(x, y))) {
                        toAdd = false;
                        break;
                    }
                }

                if (toAdd) {
                    listOfUnoccupiedChains.add(moveHelper.getChainStartingWithCords(board, boardSizeValue, new Coordinates(x, y), Occupancy.EMPTY));
                }
            }
        }

        for (List<Coordinates> chain : listOfUnoccupiedChains) {

            if (moveHelper.doesChainBorderWith(board, boardSizeValue, chain, Occupancy.BLACK)
                    && !moveHelper.doesChainBorderWith(board, boardSizeValue, chain, Occupancy.WHITE)) {
                blacksPoints += chain.size();
            } else if (!moveHelper.doesChainBorderWith(board, boardSizeValue, chain, Occupancy.BLACK)
                    && moveHelper.doesChainBorderWith(board, boardSizeValue, chain, Occupancy.WHITE)) {
                whitesPoints += chain.size();
            }
        }

        if (blacksPoints > whitesPoints) {
            return Winner.BLACK;
        } else if (blacksPoints < whitesPoints) {
            return Winner.WHITE;
        } else {
            return Winner.TIE;
        }
    }
}
