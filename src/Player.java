
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
        super.MoveUp();
    }
    public void MoveDown() throws CoordinateMapOutOFBoundException {
        super.MoveDown();
    }
    public void MoveLeft() throws CoordinateMapOutOFBoundException { 
        super.MoveLeft();
    }
    public void MoveRight() throws CoordinateMapOutOFBoundException { 
        super.MoveRight();
    }
}
