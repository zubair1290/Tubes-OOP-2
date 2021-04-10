
public class Player extends Organism {
    private Inventory<Skill> skills;
    private Inventory<EngimonPlayer> engimons;
    private EngimonPlayer engimonPlayerActive;

    public Player(Coordinate Bound, int x, int y) {
        super(Bound, x, y);
    }
    
    public void changeEngimonActive(EngimonPlayer ePlayer, int idx) {
        ePlayer = this.setActiveEngimon(idx);
    }

    private EngimonPlayer setActiveEngimon(int idx) {
        return null;
        // return engimons.setActive(idx);
    }

    public void viewAllEngimon() {
        // engimons.viewAll();
    }
    public void MoveUp() throws CoordinateMapOutOFBoundException {
        try {
            super.MoveUp();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }
    public void MoveDown() throws CoordinateMapOutOFBoundException {
        try {
            super.MoveDown();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }
    public void MoveLeft() throws CoordinateMapOutOFBoundException { 
        try {
            super.MoveLeft();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }
    public void MoveRight() throws CoordinateMapOutOFBoundException { 
        try {
            super.MoveRight();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }
}
