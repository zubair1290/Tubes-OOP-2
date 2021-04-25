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
        plyr.addEngimonPlayer(new EngimonPlayer("Cturty", mapBound, false, plyr));
        plyr.addEngimonPlayer(new EngimonPlayer("Podqer", mapBound, false, plyr));
        plyr.addEngimonPlayer(new EngimonPlayer("Fergty", mapBound, false, plyr));
        plyr.addEngimonPlayer(new EngimonPlayer("Klesrfy", mapBound, false, plyr));
        plyr.changeEngimonActive(3);
        plyr.changeEngimonActive(1);

        // Engimon Liar List
        ArrayList<EngimonWild> eWilds = new ArrayList<>();
        
        // Scanner
        Scanner sc = new Scanner(System.in);

        // command
        String command = "";
        StringBuilder fullCommand = new StringBuilder();
        boolean isProcessingCommand = false;
        boolean waitAnotherCommand = false;
        boolean isCanBattle = false;
        
        // Show map for map, player, and active engimon player
        map.Show();
        map.updatePlayerMap(plyr.getX(), plyr.getY());
        map.updateEngimonActivePlayerMap(plyr.getXActiveEngimon(), plyr.getYActiveEngimon());
        
        // Thread Engimon Wild
        ThreadEngimonWild myEngimonWildThread = new ThreadEngimonWild(map, mapBound, eWilds);
        myEngimonWildThread.start();

        LocalTime time;
        // skill();
        do {
            for (EngimonWild eWild : eWilds) {
                map.updateEngimonWildMap(eWild);
            }
            isCanBattle = false;
            
            time = LocalTime.now();
            // System.out.println(fullCommand);

            if (fullCommand.toString().contains("viewengimonliar")) {
                System.out.println(fullCommand.toString());
                fullCommand.delete(0, fullCommand.length()-1);
                // System.out.println("VIEW");
                // viewAllEngimonLiar(eWilds);
                isProcessingCommand = false;
            } else if (fullCommand.toString().contains("viewengimonplayer")) {
                fullCommand.delete(0, fullCommand.length()-1);
                plyr.viewAllEngimon();
                isProcessingCommand = false;
            } else if (fullCommand.toString().contains("listcommand")) {
                System.out.println("list command: ");
                System.out.println("w: ");
                System.out.println("a: ");
                System.out.println("s: ");
                System.out.println("d: ");
                System.out.println("set active: ");
                System.out.println("battle: ");
                System.out.println("view engimon player: ");
                System.out.println("view engimon liar: ");
            } else if (fullCommand.toString().contains("set active")) {

            }

            if (!isProcessingCommand) {
                for (EngimonWild eWild : eWilds) {
                    Coordinate eWildCoordinate = plyr.minusCo(eWild);
                    if (eWildCoordinate.isEqual(0, 1) || eWildCoordinate.isEqual(1, 0) || eWildCoordinate.isEqual(0, -1) || eWildCoordinate.isEqual(-1, 0)) {
                        isCanBattle = true;
                        System.out.println("Wanna battle?");
                        break;
                    }
                }

                System.out.print("=> ");
            }
            
            isProcessingCommand = false;
            waitAnotherCommand = false;
            
            
            command = sc.next();
            if (command.equals("w") || command.equals("a") || command.equals("s") || command.equals("d")) {
                int xPOld = plyr.getX(), yPOld = plyr.getY();
                int xEPlayerOld = plyr.getXActiveEngimon(), yEPlayerOld = plyr.getYActiveEngimon();
                try {
                    if (command.equals("w")) plyr.MoveUp();    
                    else if (command.equals("a")) plyr.MoveLeft();
                    else if (command.equals("s")) plyr.MoveDown();
                    else if (command.equals("d")) plyr.MoveRight();
                    map.updateMap(xPOld, yPOld);
                    map.updateMap(xEPlayerOld, yEPlayerOld);
                    plyr.getActiveEngimonPlayer().followPlayer();
                    map.updatePlayerMap(plyr.getX(), plyr.getY());
                    map.updateEngimonActivePlayerMap(plyr.getXActiveEngimon(), plyr.getYActiveEngimon());
                    
                } catch (CoordinateMapOutOFBoundException e) {
                    waitAnotherCommand = true;
                    System.out.println("Out OF BOUND!");
                }
            } else if (command.equals("battle")) {
                if (isCanBattle) {
                    ArrayList<Coordinate> listAllCoordinatesEWildToBattle = new ArrayList<>();
                    ArrayList<EngimonWild> listAllEWildToBattle = new ArrayList<>();
                    String[] eWildPos = {"left", "right", "up", "down"};
                    boolean[] eWildBoolPos = {false, false, false, false};
                    Coordinate[] eWildPossibleCo = {new Coordinate(1, 0), new Coordinate(-1, 0), new Coordinate(0, 1), new Coordinate(0, -1)};
                    for (EngimonWild eWild : eWilds) {
                        Coordinate eWildCo = plyr.minusCo(eWild);
                        for (int i=0; i < 4; i++) {
                            if (eWildCo.isEqual(eWildPossibleCo[i].getX(), eWildPossibleCo[i].getY())) {
                                eWildBoolPos[i] = true;
                                listAllEWildToBattle.add(eWild);
                                listAllCoordinatesEWildToBattle.add(eWildCo);
                                if (i < 2) {
                                    System.out.println("Engimon Wild is on the " + eWildPos[i]);
                                } else if (i == 3) {
                                    System.out.println("Engimon Wild is above");
                                } else {
                                    System.out.println("Engimon Wild is below");
                                }
                                break;
                            }
                        }
                    }
                    
                    myEngimonWildThread.setCommand("sleep");
                    System.out.print("=> ");
                    command = sc.next();

                    for (int i=0; i < 4; i++) {
                        if (command.equals(eWildPos[i])) {
                            if (eWildBoolPos[i]) {
                                for (EngimonWild engimonWild : listAllEWildToBattle) {
                                    if (engimonWild.getX() == eWildPossibleCo[i].getX() && engimonWild.getY() == eWildPossibleCo[i].getY()) {
                                        plyr.fight(engimonWild);
                                    }
                                }
                            } else {
                                if (i < 2) {
                                    System.out.println("There is not any engimon wild on the " + eWildPos[i] + ".");
                                } else if (i == 3) {
                                    System.out.println("There is not any engimon wild above");
                                } else {
                                    System.out.println("There is not any engimon wild below");
                                }
                            }
                        } 
                    }
                    myEngimonWildThread.interrupt();
                } else {
                    System.out.println("There is not any engimon wild to battle!");
                }
            } else if (command.equals("view") || command.equals("engimon") || command.equals("liar") || 
                command.equals("player") || command.equals("list") || command.equals("command") || 
                command.equals("set") || command.equals("active")) {
                if (fullCommand.length() > 40) {
                    fullCommand.delete(0, fullCommand.length()-1);
                    isProcessingCommand = false;
                } else {
                    isProcessingCommand = true;
                    fullCommand.append(command);
                }
            }
        

            if (!isProcessingCommand && !waitAnotherCommand && !command.equals("q")) {
                map.Show();
                System.out.println("Time in seconds: " + time.getSecond());
            }
            
            myEngimonWildThread.setCommand(command);

            
        } while (!command.equals("q"));
        myEngimonWildThread.interrupt();
        sc.close();
    }

    public static void viewAllEngimonLiar(ArrayList<EngimonWild> eWilds) {
        System.out.println("Engimon Wild:");
        for (EngimonWild eWild : eWilds) {
            System.out.println("Name: " + eWild.getName());
            System.out.println("Coordinate: " + "(" + eWild.getX() + "," + eWild.getY() + ")");
            System.out.println("Species: " + eWild.getSpecies());
            System.out.print("Element: ");
            System.out.println(eWild.getElement());
            System.out.println("Life: " + eWild.getLife());
            System.out.println("Level: " + eWild.getLevel());
            System.out.println();
        }
    }

    public void skill() {
        //Skill Punch();
        Skill Punch = new Skill("Punch", 400, 1, 1, new Element [] {Element.Fire, Element.Ice, Element.Water, Element.Electric, Element.Ground, Element.Fire_Electric, Element.Water_Ice, Element.Water_Ground});
        //Skill JudoKick();
        Skill JudoKick = new Skill("JudoKick", 400, 1, 1, new Element [] {Element.Fire, Element.Ice, Element.Water, Element.Electric, Element.Ground, Element.Fire_Electric, Element.Water_Ice, Element.Water_Ground});
        Skill Fireball = new Skill("Fireball", 500, 1, 1, new Element[] {Element.Fire});
        Skill Flamethrower = new Skill("Flamethrower", 600, 1, 1, new Element[] {Element.Fire});
        Skill NuclearHit = new Skill("NuclearHit", 700, 1, 1, new Element[] {Element.Fire_Electric});
        Skill Wave = new Skill("Wave", 500, 1, 1, new Element[] {Element.Water});
        Skill Tsunami = new Skill("Tsunami", 600, 1, 1, new Element[] {Element.Water_Ground});
        Skill Storm = new Skill("Storm", 700, 1, 1, new Element[] {Element.Water});
        Skill FrostNova = new Skill("FrostNova", 500, 1, 1, new Element[] {Element.Ice});
        Skill CryogenicFreeze = new Skill("CryogenicFreeze", 600, 1, 1, new Element[] {Element.Ice});
        Skill IceSpikes = new Skill("IceSpikes", 700, 1, 1, new Element[] {Element.Water_Ice});
        Skill Earthquake = new Skill("Earthquake", 500, 1, 1, new Element[] {Element.Ground});
        Skill MeteorStrike = new Skill("MeteorStrike", 600, 1, 1, new Element[] {Element.Ground});
        Skill Asteroid = new Skill("Asteroid", 700, 1, 1, new Element[] {Element.Ground});
        Skill Lightning = new Skill("Lightning", 500, 1, 1, new Element[] {Element.Electric});
        Skill ElectroBall = new Skill("ElectroBall", 600, 1, 1, new Element[] {Element.Fire_Electric});
        Skill TeslaRay = new Skill("TeslaRay", 700, 1, 1, new Element[] {Element.Electric});
    }
    
    // public void learn(Skill s, EngimonPlayer a){
    public void learn(Skill skill, Player player){
        EngimonPlayer ePlayer = player.getActiveEngimonPlayer();
		if (ePlayer.isSkillFull()){
			// throw (LearnExp(SLOT_SKILL_FULL));
			replaceSkillEngimon(skill, ePlayer);
		} else {
			// if (a.isThereSameElmt(s.elements)){
            if (skill.getElements().contains(ePlayer.getElement())) {
                // if (isThereSameSkill(s.skill)){
                if (ePlayer.getSkills().contains(skill)) {
                    // throw (LearnExp(THERE_IS_SAME_SKILL));
                } else {
                    if (Inventory.getCountItem() < Inventory.maxCap()) {
                        // 	a.addSkill(S, E);
                        ePlayer.addSkill(skill);
                        player.addSkill(skill);
                    } else {
                        System.out.println("Your Inventory is full");
                    }
                }
			} else {
				// throw (LearnExp(NOT_SAME_ELMT));
			}
		}
    }
	public void replaceSkillEngimon(Skill skill, EngimonPlayer ePlayer){
		System.out.println("Replace? (Y/N) : ");
		Scanner sc = new Scanner(System.in);
		String confirm = sc.nextLine();
		if (confirm == "Y") {
			// showEngimonSkills(e);
            ePlayer.showSkills();
			System.out.println("Skill yang ingin direposisi (1/2/3/4):");
			int x = sc.nextInt();
            // e.skill[x-1] = s;
            ePlayer.skills.set(x-1, skill);
			System.out.println("Reposisi Skill selesai ...");
		}
        sc.close();
	}

}
