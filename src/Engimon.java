
public abstract class Engimon extends Organism{
    protected String species;
    protected boolean active;
    protected Element element;
    protected int level;
    protected Skill skill;

    public Engimon(Coordinate bound) {
        super(bound);
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
};
