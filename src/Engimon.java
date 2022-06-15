import java.util.ArrayList;
import java.util.Random;

public abstract class Engimon extends Organism {
    protected String name;
    protected String species;
    protected String parentAname;
    protected String parentAspecies;
    protected String parentBname;
    protected String parentBspecies;
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
        this.parentAname = "";
        this.parentAspecies = "";
        this.parentBname = "";
        this.parentBspecies = "";
    }

    public String getName() {
        return this.name;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String s) {
        this.species = s;
    }

    public void setParentABNameSpecies(String parentAname, String parentAspecies, String parentBname,
            String parentBspecies) {
        this.parentAname = parentAname;
        this.parentAspecies = parentAspecies;
        this.parentBname = parentBname;
        this.parentBspecies = parentBspecies;
    }

    public void printlnParentABNameSpecies() {
        System.out.println("Parent A Name: " + this.parentAname);
        System.out.println("Parent A Species: " + this.parentAspecies);
        System.out.println("Parent B Name: " + this.parentBname);
        System.out.println("Parent B Species: " + this.parentBspecies);
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

    public String randomSpecies(Element el) {
        String species = "";
        Random rand = new Random();
        int randomhalf = rand.nextInt(2);
        try {
            switch (randomhalf) {
                case 0:
                    switch (el) {
                        case Fire:
                            species = "Fire.One.Species";
                            break;
                        case Water:
                            species = "Water.One.Species";
                            break;
                        case Electric:
                            species = "Electric.One.Species";
                            break;
                        case Ground:
                            species = "Ground.One.Species";
                            break;
                        case Ice:
                            species = "Ice.One.Species";
                            break;
                        case Fire_Electric:
                            species = "Fire_Electric.One.Species";
                            break;
                        case Water_Ice:
                            species = "Water_Ice.One.Species";
                            break;
                        case Water_Ground:
                            species = "Water_Ground.One.Species";
                            break;
                    }
                    break;
                case 1:
                    switch (el) {
                        case Fire:
                            species = "Fire.Two.Species";
                            break;
                        case Water:
                            species = "Water.Two.Species";
                            break;
                        case Electric:
                            species = "Electric.Two.Species";
                            break;
                        case Ground:
                            species = "Ground.Two.Species";
                            break;
                        case Ice:
                            species = "Ice.Two.Species";
                            break;
                        case Fire_Electric:
                            species = "Fire_Electric.Two.Species";
                            break;
                        case Water_Ice:
                            species = "Water_Ice.Two.Species";
                            break;
                        case Water_Ground:
                            species = "Water_Ground.Two.Species";
                            break;
                    }
                    break;

            }

        } catch (Exception e) {
            System.out.println("Error random species");
        }
        return species;
    }
};
