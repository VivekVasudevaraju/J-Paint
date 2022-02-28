package controller;

public class MouseCoordinates {
    private int XCoordinate;
    private int YCoordinate;

    public MouseCoordinates(int XCoordinate,int YCoordinate) {
        this.XCoordinate = XCoordinate;
        this.YCoordinate = YCoordinate;
    }

    public int getXCoordinate() {
        return this.XCoordinate;
    }

    public int getYCoordinate() {
        return this.YCoordinate;
    }
}
