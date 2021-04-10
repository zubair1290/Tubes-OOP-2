
public class CoordinateMapOutOFBoundException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Coordinate bound;
    
    public CoordinateMapOutOFBoundException(Coordinate bound) {
        this.bound = bound;
    }

    public Coordinate getBound() {
        return bound;
    }
}
