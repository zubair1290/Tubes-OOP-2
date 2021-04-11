
public abstract class Organism {
    private Coordinate co;
    private Coordinate bound;

    public Organism(Coordinate bound) {
        this(bound, 0, 0);
    }

    public Organism(Coordinate bound, int x, int y) {
        this.bound = bound;
        co = new Coordinate(x, y);
    }

    public int getX() {
        return co.getX();
    }

    public int getY() {
        return co.getY();
    }

    void setCo(int x, int y) throws CoordinateMapOutOFBoundException {
        if (isInMap(x, y)) {
            co.setCo(x, y);
        } else {
            throw new CoordinateMapOutOFBoundException(co);
        }
    }

    public boolean isInMap(int x, int y) {
        return (x >= 1 && y >= 1) && (x <= this.bound.getX() && y <= this.bound.getY());
    }

    public Coordinate getBound() {
        return this.bound;
    }
    
    protected void MoveUp() throws CoordinateMapOutOFBoundException {
        setCo(co.getX(), co.getY()-1);
    }
    protected void MoveDown() throws CoordinateMapOutOFBoundException {
        setCo(co.getX(), co.getY()+1);
    }
    protected void MoveLeft() throws CoordinateMapOutOFBoundException { 
        setCo(co.getX()-1, co.getY());
    }
    protected void MoveRight() throws CoordinateMapOutOFBoundException { 
        setCo(co.getX()+1, co.getY()); 
    }

}
