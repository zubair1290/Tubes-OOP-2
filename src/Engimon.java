
public abstract class Engimon extends Organism{
    private String species;
    private boolean active;
    private Element element;
    private int level;
    private Skill skill;

    public Engimon(boolean active) {
        this("", active, Element.Ground, new Skill());
    }

    public Engimon() {
        this(false);
    }

    public Engimon(String species, boolean active, Element element, Skill skill) {
        this.species = species;
        this.active = active;
        this.element = element;
        this.skill = skill;
        this.level = 0;
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
