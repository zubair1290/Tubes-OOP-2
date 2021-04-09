import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        // Player
        Player plyr = new Player(8, 12);

        // Engimon Player
        EngimonPlayer ePlayer = new EngimonPlayer(true, plyr);
        
        // Engimon Liar List
        ArrayList<EngimonWild> eWilds = new ArrayList<>();
        
        // Scanner
        Scanner sc = new Scanner(System.in);

        // Map
        Map map = new Map("Map/map.txt");

        // command
        String command = "";
        StringBuilder fullCommand = new StringBuilder();
        boolean isProcessingCommand = false;


        map.Show();
        do {
            if (!isProcessingCommand) System.out.print("=> ");
            
            isProcessingCommand = false;
            command = sc.next();
            fullCommand.append(command);
            if (command.equals("w") || command.equals("a") || command.equals("s") || command.equals("d")) {
                updateMap(map, plyr.getX(), plyr.getY());
                updateMap(map, ePlayer.getX(), ePlayer.getY());
                if (command.equals("w")) {
                    plyr.MoveUp();
                    
                } else if (command.equals("a")) {
                    plyr.MoveLeft();
                    
                } else if (command.equals("s")) {
                    plyr.MoveDown();
                    
                } else if (command.equals("d")) {
                    plyr.MoveRight();
                }
                ePlayer.followPlayer();
                updatePlayerMap(map, plyr);
                updateEngimonMap(map, ePlayer);
            } else if (command.equals("view") || command.equals("engimon")) {
                if (fullCommand.length() > 40) {
                    fullCommand.delete(0, fullCommand.length()-1);
                    isProcessingCommand = false;
                } else {
                    isProcessingCommand = true;
                }
                continue;
            } else if (fullCommand.toString().equals("viewengimonliar")) {
                fullCommand.delete(0, fullCommand.length()-1);
                viewAllEngimonLiar(eWilds);
                isProcessingCommand = false;
            } else if (fullCommand.toString().equals("viewengimonplayer")) {
                fullCommand.delete(0, fullCommand.length()-1);
                plyr.viewAllEngimon();
                isProcessingCommand = false;
            }

            if (!isProcessingCommand) map.Show();
            
        } while (!command.equals("q"));
        
        sc.close();
    }

    public static void viewAllEngimonLiar(ArrayList<EngimonWild> eWilds) {
        for (EngimonWild eWild : eWilds) {
            System.out.println("Coordinate:" + "(" + eWild.getX() + "," + eWild.getY() + ")");
            System.out.println("Element:" + eWild.getElement());
            System.out.println("Level: " + eWild.getLevel());
            System.out.println("Element: " + eWild.getElement());
            System.out.println("Active: " + eWild.getActive());

        }
    }

    public static void changeEngimonActive(Player plyr, EngimonPlayer ePlayer, int idx) {
        ePlayer = plyr.setActiveEngimon(idx);
    }
    
    public static void updateMap(Map map, int x, int y) {
        if (x <= 17) {
            map.set(x, y, '-');
        } else if (x >= 18) {
            if (y <= 15) {
                map.set(x, y, 'o');
            } else {
                map.set(x, y, '-');
            }
        }
    }

    public static void updatePlayerMap(Map map, Player plyr) {
        map.set(plyr.getX(), plyr.getY(), 'P');
    }

    public static void updateEngimonMap(Map map, Engimon engimon) {
        int x = engimon.getX(), y = engimon.getY();
        char ch = 0;
        switch (engimon.getElement()) {
            case Water:
                ch = 'W';
                break;
            case Ice:
                ch = 'I';
                break;
            case Fire:
                ch = 'F';
                break;
            case Ground:
                ch = 'G';
                break;
            case Electric:
                ch = 'E';
                break;
            case Fire_Electric:
                ch = 'L';
                break;
            case Water_Ice:
                ch = 'S';
                break;
            case Water_Ground:
                ch = 'N';
                break;
        }
        if (engimon.getLevel() < 20) {
            ch += -'A'+'a';
        }
        map.set(x, y, ch);
    }

};