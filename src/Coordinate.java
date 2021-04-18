
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setCo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isEqual(int x, int y) {
        return this.x == x && this.y == y;
    }

    public Coordinate minus(Coordinate co) {
        return new Coordinate(this.x - co.x, this.y - co.y);
    }
    public boolean equals(Coordinate obj) {
        return this.x == obj.x && this.y == obj.y;
    }

}
