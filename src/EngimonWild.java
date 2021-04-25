import java.util.Random;
import java.util.ArrayList;
public class EngimonWild extends Engimon {

    public EngimonWild(String name, Coordinate bound) {
        super(name, new ArrayList<Skill>(), bound, 1);
        // species random
        Random rand = new Random();
        this.level = rand.nextInt(30);
        do {
            this.element = Element.values()[rand.nextInt(8)];
            try {
                switch (element) {
                    case Water:
                    case Ice:
                    case Water_Ice: {
                            System.out.println("Test");
                            int x = rand.nextInt(21)+18;
                            int y = rand.nextInt(15)+1;
                            this.setCo(x, y);
                            System.out.println("Coordinate Random Spawn:" + x + ',' + y);
                        }
                        break;
                    case Fire:
                    case Fire_Electric:
                    case Ground:
                    case Electric: {
                            System.out.println("Test");
                            int x, y;
                            y = rand.nextInt(28)+1;
                            if (y <= 15) x = rand.nextInt(17)+1;
                            else x = rand.nextInt(38)+1;
                            System.out.println("Coordinate Random Spawn:" + x + ',' + y);
                            this.setCo(x, y);
                        }
                        break;
                    case Water_Ground: {
                            this.setCo(rand.nextInt(38)+1, rand.nextInt(28)+1);
                        }
                        break;
                }
                break;
            } catch (CoordinateMapOutOFBoundException e) {
                
            }
        } while (true);
    }

    public void MoveUp(Map map) throws CoordinateMapOutOFBoundException, CoordinateEngimonIsNotInAreaException {
        switch (element) {
            case Water:
            case Ice:
            case Water_Ice:
                if (map.get(this.getX(), this.getY()-1) != 'o') {
                    throw new CoordinateEngimonIsNotInAreaException();
                } else {
                    super.MoveUp();
                }
                break;
            case Fire:
            case Fire_Electric:
            case Ground:
            case Electric: 
                if (map.get(this.getX(), this.getY()-1) != '-') {
                    throw new CoordinateEngimonIsNotInAreaException();
                } else {
                    super.MoveUp();
                }
                break;
            case Water_Ground:
                break;
        }
    }
    public void MoveDown(Map map) throws CoordinateMapOutOFBoundException, CoordinateEngimonIsNotInAreaException {
        switch (element) {
            case Water:
            case Ice:
            case Water_Ice:
                if (map.get(this.getX(), this.getY()+1) != 'o') {
                    throw new CoordinateEngimonIsNotInAreaException();
                } else {
                    super.MoveDown();
                }
                break;
            case Fire:
            case Fire_Electric:
            case Ground:
            case Electric: 
                if (map.get(this.getX(), this.getY()+1) != '-') {
                    throw new CoordinateEngimonIsNotInAreaException();
                } else {
                    super.MoveDown();
                }
                break;
            case Water_Ground:
                break;
        }
    }
    public void MoveLeft(Map map) throws CoordinateMapOutOFBoundException, CoordinateEngimonIsNotInAreaException {
        switch (element) {
            case Water:
            case Ice:
            case Water_Ice:
                if (map.get(this.getX()-1, this.getY()) != 'o') {
                    throw new CoordinateEngimonIsNotInAreaException();
                } else {
                    super.MoveLeft();
                }
                break;
            case Fire:
            case Fire_Electric:
            case Ground:
            case Electric: 
                if (map.get(this.getX()-1, this.getY()) != '-') {
                    throw new CoordinateEngimonIsNotInAreaException();
                } else {
                    super.MoveLeft();
                }
                break;
            case Water_Ground:
                break;
        }
    }
    public void MoveRight(Map map) throws CoordinateMapOutOFBoundException, CoordinateEngimonIsNotInAreaException {
        switch (element) {
            case Water:
            case Ice:
            case Water_Ice:
                if (map.get(this.getX()+1, this.getY()) != 'o') {
                    throw new CoordinateEngimonIsNotInAreaException();
                } else {
                    super.MoveRight();
                }
                break;
            case Fire:
            case Fire_Electric:
            case Ground:
            case Electric: 
                if (map.get(this.getX()+1, this.getY()) != '-') {
                    throw new CoordinateEngimonIsNotInAreaException();
                } else {
                    super.MoveRight();
                }
                break;
            case Water_Ground:
                break;
        }
    }

    
}
