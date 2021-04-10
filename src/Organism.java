
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

    // public abstract void MoveUp() throws CoordinateMapOutOFBoundException;
    // public abstract void MoveUp() throws CoordinateMapOutOFBoundException, CoordinateEngimonIsNotInAreaException;
    
    protected void MoveUp() throws CoordinateMapOutOFBoundException {
        try {
            setCo(co.getX(), co.getY()+1);
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(bound);
        }
    }
    protected void MoveDown() throws CoordinateMapOutOFBoundException {
        try {
            setCo(co.getX(), co.getY()+1);
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(bound);
        }
    }
    protected void MoveLeft() throws CoordinateMapOutOFBoundException { 
        try {
            setCo(co.getX()-1, co.getY());
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(bound);
        }
    }
    protected void MoveRight() throws CoordinateMapOutOFBoundException { 
        try {
            setCo(co.getX()+1, co.getY()); 
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(bound);
        }
    }

}
