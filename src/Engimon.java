import java.util.ArrayList;

public abstract class Engimon extends Organism {
    protected String name;
    protected String species;
    protected boolean active;
    protected Element element;
    protected int level;
    protected ArrayList<Skill> skills;
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

    public String getName() {
        return this.name;
    }

    public String getSpecies() {
        return this.species;
    }

    public Element getElement() {
        return this.element;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int a) {
        this.level = a;
    }

    public ArrayList<Skill> getSkills() {
        return this.skills;
    }

    public int getLife() {
        return this.life;
    }

    public void decrease1Life() {
        this.life -= 1;
    }

    public int sumTotalSkillPower() {
        int sumPower = 0;
        for (Skill skill : skills) {
            sumPower += skill.getPower();
        }
        return sumPower;
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

    private String randomSpecies(Element el) {
        String species = "";
        try {
            switch (el) {
            case Fire:
                species = "Fire.Species";
            case Water:
                species = "Water.Species";
            case Electric:
                species = "Electric.Species";
            case Ground:
                species = "Ground.Species";
            case Ice:
                species = "Ice.Species";
            case Fire_Electric:
                species = "Fire_Electric.Species";
            case Water_Ice:
                species = "Water_Ice.Species";
            case Water_Ground:
                species = "Water_Ground.Species";
            }
        } catch (Exception e) {
            System.out.println("Error random species");
        }
        return species;
    }
};
