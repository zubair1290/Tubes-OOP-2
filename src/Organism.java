
public abstract class Organism {
    private Coordinate co;

    public Organism() {
        this(0, 0);
    }

    public Organism(int x, int y) {
        co = new Coordinate(x, y);
    }

    public int getX() {
        return co.getX();
    }

    public int getY() {
        return co.getY();
    }

    void setCo(int x, int y) {
        // exception 
        co.setCo(x, y);
    }

    public void MoveUp()    { setCo(co.getX()   , co.getY()-1); }
    public void MoveDown()  { setCo(co.getX()   , co.getY()+1); }
    public void MoveLeft()  { setCo(co.getX()-1 , co.getY()  ); }
    public void MoveRight() { setCo(co.getX()+1 , co.getY()  ); }

}
