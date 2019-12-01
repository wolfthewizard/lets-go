package core.contract;

public class Coordinates {

    public int x;
    public int y;

    public Coordinates(){}

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", "+ y + ")";
    }
}
