import java.util.Random;

// import java.util.Random;

public class EngimonWild extends Engimon {

    public EngimonWild(Coordinate bound) {
        super(bound);
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

    
}
