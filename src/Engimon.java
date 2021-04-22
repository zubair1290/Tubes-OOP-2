
public abstract class Engimon extends Organism{
    protected String species;
    protected boolean active;
    protected Element element;
    protected int level;
    ArrayList <Skill> skill;
    protected int life;

    public Engimon(Coordinate bound, int life) {
        super(bound);
        this.life = life;
    }

    public boolean getActive() {
        return active;
    }

    public Element getElement() {
        return element;
    }

    public int sumTotalSkillPower() {
        return 1;
    }

    public int getLevel() {
        return level;
    }

    public Skill getSkill() {
        return skill;
    }

    public String getSpecies() {
        return species;
    }

    public int getLife() {
        return life;
    }
};
