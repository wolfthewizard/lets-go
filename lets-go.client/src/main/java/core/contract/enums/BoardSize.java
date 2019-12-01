package core.contract.enums;

public enum BoardSize {
    NINE(9),
    THIRTEEN(13),
    NINETEEN(19);

    private int value;

    BoardSize(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
