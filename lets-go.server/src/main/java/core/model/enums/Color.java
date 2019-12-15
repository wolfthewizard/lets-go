package core.model.enums;

import contract.enums.Occupancy;

public enum Color {
    WHITE,
    BLACK;

    public Occupancy toOccupancy() {

        if (this.equals(Color.BLACK)) {
            return Occupancy.BLACK;
        } else {
            return Occupancy.WHITE;
        }
    }

    public Color reverse() {

        if (this.equals(Color.BLACK)) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }
}
