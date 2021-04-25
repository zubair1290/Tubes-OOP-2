import java.util.ArrayList;

public abstract class Engimon extends Organism{
    protected String name;
    protected String species;
    protected boolean active;
    protected Element element;
    protected int level;
    ArrayList <Skill> skills;
    protected int exp;
    protected int cumExp;
    protected int life;

    public Engimon(String name, ArrayList<Skill> skills, Coordinate bound, int life) {
        super(bound);
        this.name = name;
        this.life = life;
        this.level = 0;
        this.skills = skills;
        this.exp = 0;
        this.cumExp = 0;
    }

    public String getName() { return this.name; }
    public String getSpecies() { return this.species; }
    public Element getElement() { return this.element; }
    public int getLevel() { return this.level; }
    public ArrayList<Skill> getSkills() { return this.skills; }
    public int getLife() { return this.life; }

    public int sumTotalSkillPower() {
        return 1;
    }

    public void expUp() {
        this.exp += 5;
    }

    public void levelUp() {
        this.level++;
        this.cumExp += this.exp;
        this.exp = 0;
    }

    public boolean isLevelUp() {
        return this.exp == 100;
    }
};
