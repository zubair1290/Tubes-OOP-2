
public class Player extends Organism {
    private Inventory<Skill> skills;
    private Inventory<EngimonPlayer> engimons;
    private EngimonPlayer engimonPlayerActive;

    public Player(int x, int y) {
        super(x, y);
    }

    public EngimonPlayer setActiveEngimon(int idx) {
        return null;  
        // return engimons.setActive(idx);
    }

    public void viewAllEngimon() {
        // engimons.viewAll();
    }


}
