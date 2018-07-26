package dcs.nxt;

public class Coordinate {

    // The coordinates should be integers only
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Vector getDifference(Coordinate c) {
        return new Vector(this.getX() - c.getX(), this.getY() - c.getY());
    }
}
