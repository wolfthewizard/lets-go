package core.model;

import contract.ResponsePrisoners;
import core.model.Color;

public class Prisoners {

    private int blacksPrisoners;
    private int whitesPrisoners;

    public Prisoners(int blackPrisoners, int whitePrisoners) {
        this.blacksPrisoners = blackPrisoners;
        this.whitesPrisoners = whitePrisoners;
    }

    public ResponsePrisoners toResponsePrisoners(Color movemakersColor) {

        if (movemakersColor.equals(Color.BLACK)) {
            return new ResponsePrisoners(blacksPrisoners, whitesPrisoners);
        } else {
            return new ResponsePrisoners(whitesPrisoners, blacksPrisoners);
        }
    }

    public void addBlacksPrisoners(int delta) {
        blacksPrisoners += delta;
    }

    public void addWhitesPrisoners(int delta) {
        whitesPrisoners += delta;
    }

    public int getBlacksPrisoners() {
        return blacksPrisoners;
    }

    public int getWhitesPrisoners() {
        return whitesPrisoners;
    }
}
