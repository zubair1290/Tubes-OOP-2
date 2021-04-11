// import java.time.LocalTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class ThreadEngimonWild extends Thread {
    private ArrayList<EngimonWild> eWilds;
    private String command;
    private Map map;
    private Coordinate bound;

    public ThreadEngimonWild(Map map, Coordinate bound, ArrayList<EngimonWild> eWilds) {
        super();
        this.map = map;
        this.bound = bound;
        this.eWilds = eWilds;
        this.command = "";
    }

    public void run() {
        Random rand = new Random();
        LocalTime time = LocalTime.now();
        int remainderSpawn = time.getSecond() % 20;
        int remainderMove = time.getSecond() % 3;
        boolean isHasSpawn = false;
        boolean isHasMove = false;
        do {
            LocalTime time2 = LocalTime.now();
            if (isHasSpawn && time2.getSecond() % 20 == remainderSpawn+1) {
                isHasSpawn = false;
            }
            if (isHasMove && time2.getSecond() % 3 == remainderMove+1) {
                isHasMove = false;
            }

            // setiap 20 detik spawn engimon wild
            if (time2.getSecond() % 20 == remainderSpawn && !isHasSpawn) {
                this.eWilds.add(new EngimonWild(this.bound));
                isHasSpawn = true;
            }
            // setiap 3 detik engimon wild bergerak
            if (time2.getSecond() % 3 == remainderMove && !isHasMove) {
                for (EngimonWild eWild : this.eWilds) {
                    try {
                        int move = rand.nextInt(4);
                        // System.out.println("" + move);
                        int xEngimonOld = eWild.getX(), yEngimonOld = eWild.getY();
                        if (move == 0) {
                            eWild.MoveRight(this.map);
                        } else if (move == 1) {
                            eWild.MoveDown(this.map);
                        } else if (move == 2) {
                            eWild.MoveLeft(this.map);
                        } else if (move == 3) {
                            eWild.MoveUp(this.map);
                        }
                        map.updateMap(xEngimonOld, yEngimonOld);;
                        map.updateEngimonMap(eWild);
                    } catch (CoordinateMapOutOFBoundException e) {
                        // e.printStackTrace();
                    } catch (CoordinateEngimonIsNotInAreaException e) {
                        // e.printStackTrace();
                    }
                }
                isHasMove = true;
            }
        } while (!command.equals("q"));
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
