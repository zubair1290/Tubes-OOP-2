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
        plyr.addEngimonPlayer(new EngimonPlayer("Cturty", Element.Electric, mapBound, false, plyr));
        plyr.addEngimonPlayer(new EngimonPlayer("Podqer", Element.Fire, mapBound, false, plyr));
        plyr.addEngimonPlayer(new EngimonPlayer("Fergty", Element.Ground, mapBound, false, plyr));
        plyr.addEngimonPlayer(new EngimonPlayer("Klesrfy", Element.Ground, mapBound, false, plyr));
        plyr.changeEngimonActive(1);

        // Engimon Liar List
        ArrayList<EngimonWild> eWilds = new ArrayList<>();

        // List Skill
        ArrayList<Skill> skills = new ArrayList<>();
        initSkill(skills);
        
        // Scanner
        Scanner sc = new Scanner(System.in);

        // Command
        String command = "";
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
            
            
            command = sc.nextLine();
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
            } else if (command.equals("learn")) {
                int c = 0;
                for (Skill skill : skills) {
                    System.out.println("No. " + c++);
                    System.out.println("Skill: " + skill.getSkill());
                    System.out.println("Base Power: " + skill.getPower());
                    System.out.print("Element Required: ");
                    for (int i=0; i < skill.getElements().size()-1; i++) {
                        System.out.print(skill.getElements().get(i));
                        System.out.print(", ");
                    }
                    System.out.println(skill.getElements().get(skill.getElements().size()-1));
                    System.out.println("Mastery Level: " + skill.getMasteryLevel());
                    System.out.println();
                }
                System.out.print("Learn Skill Number: ");
                int idx = sc.nextInt();
                learn(skills.get(idx), plyr);
                sc.nextLine();
            } else if (command.equals("view engimon liar")) {
                viewAllEngimonLiar(eWilds);
                sc.nextLine();
            } else if (command.equals("view engimon player")) {
                plyr.viewAllEngimon();
                sc.nextLine();
            } else if (command.equals("list command")) {
                System.out.println("list command: List All Command");
                System.out.println("w: Player Move Up");
                System.out.println("a: Player Move Left");
                System.out.println("s: Player Move Down");
                System.out.println("d: Player Move Right");
                System.out.println("switch active engimon: Switch Active Engimon");
                System.out.println("battle: Battle to the nearest Engimon Wild");
                System.out.println("view engimon player: View All Engimon Player");
                System.out.println("view engimon liar: View All Engimon Liar");
                sc.nextLine();
            } else if (command.equals("switch active engimon")) {
                int idx = sc.nextInt();
                plyr.viewAllEngimon();
                plyr.changeEngimonActive(idx);
                System.out.println("Engimon Active Switched");
                System.out.println("Name: " + plyr.getActiveEngimonPlayer().getName());
                System.out.println("Coordinate: " + "(" + plyr.getActiveEngimonPlayer().getX() + "," + plyr.getActiveEngimonPlayer().getY() + ")");
                System.out.println("Species: " + plyr.getActiveEngimonPlayer().getSpecies());
                System.out.print("Element: ");
                System.out.println(plyr.getActiveEngimonPlayer().getElement());
                System.out.println("Life: " + plyr.getActiveEngimonPlayer().getLife());
                System.out.println("Level: " + plyr.getActiveEngimonPlayer().getLevel());
                System.out.println("Active: " + plyr.getActiveEngimonPlayer().getActive());
                System.out.print("Skill: ");
                int c = 0;
                for (Skill skill : plyr.getActiveEngimonPlayer().getSkills()) {
                    System.out.println("No. " + c++);
                    System.out.println("Skill: " + skill.getSkill());
                    System.out.println("Base Power: " + skill.getPower());
                    System.out.print("Element Required: ");
                    for (int i=0; i < skill.getElements().size()-1; i++) {
                        System.out.print(skill.getElements().get(i));
                        System.out.print(", ");
                    }
                    System.out.println(skill.getElements().get(skill.getElements().size()-1));
                    System.out.println("Mastery Level: " + skill.getMasteryLevel());
                    System.out.println();
                }
                System.out.println();
                sc.nextLine();
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

    // public void learn(Skill s, EngimonPlayer a){
    public static void learn(Skill skill, Player player) {
        Skill skill_n = new Skill(skill);
        EngimonPlayer ePlayer = player.getActiveEngimonPlayer();
        System.out.println("Learn");
		if (ePlayer.isSkillFull()){
            System.out.println("Your skills are full");
            // throw (LearnExp(SLOT_SKILL_FULL));
			replaceSkillEngimon(skill_n, ePlayer);
		} else {
			// if (a.isThereSameElmt(s.elements)){
            if (skill_n.getElements().contains(ePlayer.getElement())) {
                // if (isThereSameSkill(s.skill)){
                if (ePlayer.getSkills().contains(skill_n)) {
                    // throw (LearnExp(THERE_IS_SAME_SKILL));
                    System.out.println("You have already learn this skill.");
                } else {
                    if (Inventory.getCountItem() < Inventory.maxCap()) {
                        // a.addSkill(S, E);
                        System.out.println("Skill Added.");
                        ePlayer.addSkill(skill_n);
                        player.addSkill(skill_n);
                    } else {
                        System.out.println(Inventory.getCountItem());
                        System.out.println(Inventory.maxCap());
                        System.out.println("Your Inventory is full.");
                    }
                }
			} else {
                System.out.println("You are not required to learn this skill.");
				// throw (LearnExp(NOT_SAME_ELMT));
			}
		}
    }
	public static void replaceSkillEngimon(Skill skill, EngimonPlayer ePlayer){
		System.out.print("Replace? (Y/N) : ");
		Scanner sc = new Scanner(System.in);
		String confirm = sc.next();
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

    public static void initSkill(ArrayList<Skill> skills) {
        skills.add(new Skill("Punch", 400, 1, 1, new Element[] {Element.Fire, Element.Ice, Element.Water, Element.Electric, Element.Ground, Element.Fire_Electric, Element.Water_Ice, Element.Water_Ground}));
        skills.add(new Skill("JudoKick", 400, 1, 1, new Element[] {Element.Fire, Element.Ice, Element.Water, Element.Electric, Element.Ground, Element.Fire_Electric, Element.Water_Ice, Element.Water_Ground}));
        skills.add(new Skill("Fireball", 500, 1, 1, new Element[] {Element.Fire}));
        skills.add(new Skill("Flamethrower", 600, 1, 1, new Element[] {Element.Fire}));
        skills.add(new Skill("NuclearHit", 700, 1, 1, new Element[] {Element.Fire_Electric}));
        skills.add(new Skill("Wave", 500, 1, 1, new Element[] {Element.Water}));
        skills.add(new Skill("Tsunami", 600, 1, 1, new Element[] {Element.Water_Ground}));
        skills.add(new Skill("Storm", 700, 1, 1, new Element[] {Element.Water}));
        skills.add(new Skill("FrostNova", 500, 1, 1, new Element[] {Element.Ice}));
        skills.add(new Skill("CryogenicFreeze", 600, 1, 1, new Element[] {Element.Ice}));
        skills.add(new Skill("IceSpikes", 700, 1, 1, new Element[] {Element.Water_Ice}));
        skills.add(new Skill("Earthquake", 500, 1, 1, new Element[] {Element.Ground}));
        skills.add(new Skill("MeteorStrike", 600, 1, 1, new Element[] {Element.Ground}));
        skills.add(new Skill("Asteroid", 700, 1, 1, new Element[] {Element.Ground}));
        skills.add(new Skill("Lightning", 500, 1, 1, new Element[] {Element.Electric}));
        skills.add(new Skill("ElectroBall", 600, 1, 1, new Element[] {Element.Fire_Electric}));
        skills.add(new Skill("TeslaRay", 700, 1, 1, new Element[] {Element.Electric}));
    }

}
