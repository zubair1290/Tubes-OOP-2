import java.util.ArrayList;

public class EngimonPlayer extends Engimon implements InventoryParent {
    private Player plyr;
    private Direction direction;
    private boolean active;

    public EngimonPlayer(String name, Element element, Coordinate bound, Player plyr) {
        this(name, element, bound, false, plyr);
        this.species = randomSpecies(this.element);
    }

    public EngimonPlayer(String name, Element element, Coordinate bound, boolean active, Player plyr) {
        super(name, new ArrayList<Skill>(), bound, 3);
        this.active = active;
        this.level = 0;

        this.element = element;
        this.species = randomSpecies(this.element);
        this.plyr = plyr;
        try {
            this.setCo(this.plyr.getX() - 1, this.plyr.getY());
        } catch (CoordinateMapOutOFBoundException e) {
            e.printStackTrace();
        }
        this.direction = Direction.right;
    }

    public void setActive() {
        active = true;
    }

    public void setDeactive() {
        active = false;
    }

    public boolean getActive() {
        return this.active;
    }

    public boolean isSkillFull() {
        return this.skills.size() >= 4;
    }

    public void decreaseLevelByBreeding() {
        this.level -= 3;
    }

    public void showSkills() {
        int i = 1;
        for (Skill skill : skills) {
            System.out.println("Skill " + i++);
            System.out.println("Name: " + skill.skill);
            System.out.println("Base Power: " + skill.basePower);
            System.out.println("Mastery Level: " + skill.masteryLevel);
            System.out.println();
        }
    }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }

    public void followPlayer() {
        if (this.getActive() == true) {
            try {
                switch (direction) {
                case up:
                    this.MoveUp();
                    break;
                case down:
                    this.MoveDown();
                    break;
                case left:
                    this.MoveLeft();
                    break;
                case right:
                    this.MoveRight();
                    break;
                }
                int x_rel = this.plyr.getX() - this.getX();
                if (x_rel == 1) {
                    this.direction = Direction.right;
                } else if (x_rel == -1) {
                    this.direction = Direction.left;
                }
                int y_rel = this.plyr.getY() - this.getY();
                if (y_rel == 1) {
                    this.direction = Direction.down;
                } else if (y_rel == -1) {
                    this.direction = Direction.up;
                }
            } catch (CoordinateMapOutOFBoundException e) {

            }
        }
    }

    public void startFollowPlayer(int x, int y) {
        try {
            this.setCo(x, y);

        } catch (CoordinateMapOutOFBoundException e) {
            e.printStackTrace();
        }
    }

    public void MoveUp() throws CoordinateMapOutOFBoundException {
        try {
            setCo(this.getX(), this.getY() - 1);
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

    public void MoveDown() throws CoordinateMapOutOFBoundException {
        try {
            setCo(this.getX(), this.getY() + 1);
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

    public void MoveLeft() throws CoordinateMapOutOFBoundException {
        try {
            setCo(this.getX() - 1, this.getY());
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

    public void MoveRight() throws CoordinateMapOutOFBoundException {
        try {
            setCo(this.getX() + 1, this.getY());
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

};
