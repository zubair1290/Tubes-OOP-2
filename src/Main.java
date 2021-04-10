import java.util.Scanner;


import java.time.LocalTime;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        // Map
        Map map = new Map("Map/map.txt");
        Coordinate mapBound = map.getBound();

        // Player
        Player plyr = new Player(mapBound, 8, 12);

        // Engimon Player
        EngimonPlayer ePlayer = new EngimonPlayer(mapBound, true, plyr);
        
        // Engimon Liar List
        ArrayList<EngimonWild> eWilds = new ArrayList<>();
        
        // Scanner
        Scanner sc = new Scanner(System.in);

        // command
        String command = "";
        StringBuilder fullCommand = new StringBuilder();
        boolean isProcessingCommand = false;
        boolean waitAnotherCommand = false;
        
        map.Show();
        map.updatePlayerMap(plyr);
        map.updateEngimonMap(ePlayer);
        ThreadEngimonWild myEngimonWildThread = new ThreadEngimonWild(mapBound, eWilds);
        LocalTime time;
        myEngimonWildThread.start();
        do {
            for (EngimonWild eWild : eWilds) {
                map.updateEngimonMap(eWild);
            }
            
            time = LocalTime.now();

            if (fullCommand.toString().equals("viewengimonliar")) {
                System.out.println(fullCommand.toString());
                fullCommand.delete(0, fullCommand.length()-1);
                System.out.println("VIEW");
                viewAllEngimonLiar(eWilds);
                isProcessingCommand = false;
            } else if (fullCommand.toString().equals("viewengimonplayer")) {
                fullCommand.delete(0, fullCommand.length()-1);
                plyr.viewAllEngimon();
                isProcessingCommand = false;
            }

            if (!isProcessingCommand) System.out.print("=> ");
            
            isProcessingCommand = false;
            waitAnotherCommand = false;
            
            command = sc.next();
            if (command.equals("w") || command.equals("a") || command.equals("s") || command.equals("d")) {
                int xPOld = plyr.getX(), yPOld = plyr.getY();
                int xEPlayerOld = ePlayer.getX(), yEPlayerOld = ePlayer.getY();
                try {
                    if (command.equals("w")) plyr.MoveUp();    
                    else if (command.equals("a")) plyr.MoveLeft();
                    else if (command.equals("s")) plyr.MoveDown();
                    else if (command.equals("d")) plyr.MoveRight();
                    map.updateMap(xPOld, yPOld);
                    map.updateMap(xEPlayerOld, yEPlayerOld);
                    ePlayer.followPlayer();
                    map.updatePlayerMap(plyr);
                    map.updateEngimonMap(ePlayer);
                    
                } catch (CoordinateMapOutOFBoundException e) {
                    waitAnotherCommand = true;
                    System.out.println("Out OF BOUND!");
                }
            } else if (command.equals("view") || command.equals("engimon") || command.equals("liar")) {
                if (fullCommand.length() > 40) {
                    fullCommand.delete(0, fullCommand.length()-1);
                    isProcessingCommand = false;
                } else {
                    isProcessingCommand = true;
                    fullCommand.append(command);
                }
            }

            if (!isProcessingCommand && !waitAnotherCommand && !command.equals("q")) map.Show();
            myEngimonWildThread.setCommand(command);

            System.out.println("Time in seconds: " + time.getSecond());
            
        } while (!command.equals("q"));
        myEngimonWildThread.interrupt();
        sc.close();
    }

    public static void viewAllEngimonLiar(ArrayList<EngimonWild> eWilds) {
        System.out.println("VIEW ENGIMON WILD:");
        for (EngimonWild eWild : eWilds) {
            System.out.println("Ele");
            // System.out.println("Coordinate:" + "(" + eWild.getX() + "," + eWild.getY() + ")");
            // // System.out.println("Element:" + eWild.getElement());
            // eWild.getElement();
            // System.out.println("Level: " + eWild.getLevel());
            // System.out.println("Element: " + eWild.getElement());
            // System.out.println("Active: " + eWild.getActive());

        }

    }

};