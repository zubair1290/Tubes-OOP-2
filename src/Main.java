import java.util.Scanner;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class Main {

    public static void main(String[] args) {
        // Map
        Map map = new Map("Map/map.txt");
        Coordinate mapBound = map.getBound();

        // Player
        Player plyr = new Player(mapBound, 8, 12);

        // Engimon Player and Unique Skill
        EngimonPlayer ePlayer = new EngimonPlayer("Cturty", Element.Electric, mapBound, false, plyr);
        plyr.addEngimonPlayer(ePlayer);
        ePlayer.addSkill(new Skill("Lightning", 500, 1, 1, new Element[] { Element.Electric }));

        ePlayer = new EngimonPlayer("Podqer", Element.Fire, mapBound, false, plyr);
        plyr.addEngimonPlayer(ePlayer);
        ePlayer.addSkill(new Skill("Flamethrower", 600, 1, 1, new Element[] { Element.Fire }));

        ePlayer = new EngimonPlayer("Fergty", Element.Ground, mapBound, false, plyr);
        plyr.addEngimonPlayer(ePlayer);
        ePlayer.addSkill(new Skill("MeteorStrike", 600, 1, 1, new Element[] { Element.Ground }));

        ePlayer = new EngimonPlayer("Klesrfy", Element.Ground, mapBound, false, plyr);
        plyr.addEngimonPlayer(ePlayer);
        ePlayer.addSkill(new Skill("Earthquake", 500, 1, 1, new Element[] { Element.Ground }));

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
        do {
            for (EngimonWild eWild : eWilds) {
                map.updateEngimonWildMap(eWild);
            }
            isCanBattle = false;

            time = LocalTime.now();
            if (!isProcessingCommand) {
                for (EngimonWild eWild : eWilds) {
                    Coordinate eWildCoordinate = plyr.minusCo(eWild);
                    if (eWildCoordinate.isEqual(0, 1) || eWildCoordinate.isEqual(1, 0) || eWildCoordinate.isEqual(0, -1)
                            || eWildCoordinate.isEqual(-1, 0)) {
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
                    if (command.equals("w"))
                        plyr.MoveUp();
                    else if (command.equals("a"))
                        plyr.MoveLeft();
                    else if (command.equals("s"))
                        plyr.MoveDown();
                    else if (command.equals("d"))
                        plyr.MoveRight();
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
                    myEngimonWildThread.setCommand("sleep");
                    ArrayList<Coordinate> listAllCoordinatesEWildToBattle = new ArrayList<>();
                    ArrayList<EngimonWild> listAllEWildToBattle = new ArrayList<>();
                    String[] eWildPos = { "left", "right", "up", "down" };
                    boolean[] eWildBoolPos = { false, false, false, false };
                    Coordinate[] eWildPossibleCo = { new Coordinate(1, 0), new Coordinate(-1, 0), new Coordinate(0, 1),
                            new Coordinate(0, -1) };
                    for (EngimonWild eWild : eWilds) {
                        Coordinate eWildCo = plyr.minusCo(eWild);
                        for (int i = 0; i < 4; i++) {
                            if (eWildCo.isEqual(eWildPossibleCo[i].getX(), eWildPossibleCo[i].getY())) {
                                eWildBoolPos[i] = true;
                                listAllEWildToBattle.add(eWild);
                                listAllCoordinatesEWildToBattle.add(eWildCo);
                                if (i < 2) {
                                    System.out.println("Engimon Wild is on the " + eWildPos[i]);
                                } else if (i == 3) {
                                    System.out.println("Engimon Wild is below");
                                } else {
                                    System.out.println("Engimon Wild is above");
                                }
                                System.out.println("Engimon Wild details :");
                                System.out.println("Name :" + eWild.getName());
                                System.out.println("Species :" + eWild.getSpecies());
                                System.out.println("Level :" + eWild.getLevel());
                                System.out.println("Element :" + eWild.getElement());
                                System.out.println("SumTotalSkillPower :" + eWild.sumTotalSkillPower());
                                System.out.println();
                                break;
                            }
                        }
                    }
                    System.out.println("Engimon Active details :");
                    System.out.println("Name :" + plyr.getActiveEngimonPlayer().getName());
                    System.out.println("Species :" + plyr.getActiveEngimonPlayer().getSpecies());
                    System.out.println("Level :" + plyr.getActiveEngimonPlayer().getLevel());
                    System.out.println("Element :" + plyr.getActiveEngimonPlayer().getElement());
                    System.out.println(
                            "SumTotalSkillPower :" + plyr.getActiveEngimonPlayer().sumTotalSkillPower());
                    System.out.println();

                    System.out.println("Choose enemies: left, right, up, or down");
                    System.out.print("=> ");
                    command = sc.next();

                    for (int i = 0; i < 4; i++) {
                        if (command.equals(eWildPos[i])) {
                            if (eWildBoolPos[i]) {
                                for (EngimonWild engimonWild : listAllEWildToBattle) {
                                    Coordinate minusCo = plyr.minusCo(engimonWild);
                                    if (minusCo.getX() == eWildPossibleCo[i].getX()
                                            && minusCo.getY() == eWildPossibleCo[i].getY()) {
                                        System.out.println("Fight!!");
                                        boolean fight = plyr.fight(engimonWild);
                                        try {
                                            if (fight) { // win fight
                                                // add new engimon player
                                                ePlayer = new EngimonPlayer(engimonWild.getName(),
                                                        engimonWild.getElement(), mapBound, false, plyr);
                                                plyr.addEngimonPlayer(ePlayer);
                                                // ePlayer.getSkills().add(engimonWild.getSkills().get(0));
                                                // plyr.getInventorySkill().add.(engimonWild.getSkills().get(0));
                                                ePlayer.addSkill(engimonWild.getSkills().get(0));
                                                plyr.addSkill(engimonWild.getSkills().get(0));

                                                // remove engimon wild
                                                map.updateMap(engimonWild.getX(), engimonWild.getY());
                                                eWilds.remove(engimonWild);

                                                // exp up
                                                plyr.getActiveEngimonPlayer().expUp();
                                                if (plyr.getActiveEngimonPlayer().isLevelUp()) {
                                                    plyr.getActiveEngimonPlayer().levelUp();
                                                }
                                                // skill mastery level up
                                                for (Skill s : plyr.getActiveEngimonPlayer().getSkills())
                                                    if (s.getMasteryLevel() < 3) {
                                                        s.setSkillLevel(1 + s.getMasteryLevel());
                                                    }
                                            } else {
                                                plyr.getActiveEngimonPlayer().decrease1Life();
                                            }
                                        } catch (Exception e) {
                                            System.out.println("error fight");
                                        }

                                        if (plyr.getActiveEngimonPlayer().getLife() <= 0) {
                                            plyr.removeEngimon(plyr.getActiveEngimonPlayer());
                                            plyr.changeEngimonActive(0);
                                        }

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
                        // System.out.println(eWildBoolPos[i]);
                    }
                    myEngimonWildThread.interrupt();
                    sc.nextLine();
                } else {
                    System.out.println("There is not any engimon wild to battle!");
                }
                sc.nextLine();
            } else if (command.equals("learn")) {
                int c = 0;
                for (Skill skill : skills) {
                    System.out.println("No. " + c++);
                    System.out.println("Skill: " + skill.getSkill());
                    System.out.println("Base Power: " + skill.getPower());
                    System.out.print("Element Required: ");
                    for (int i = 0; i < skill.getElements().size() - 1; i++) {
                        System.out.print(skill.getElements().get(i));
                        System.out.print(", ");
                    }
                    System.out.println(skill.getElements().get(skill.getElements().size() - 1));
                    System.out.println("Mastery Level: " + skill.getMasteryLevel());
                    System.out.println();
                }
                System.out.print("Learn Skill Number: ");
                try {
                    int idx = sc.nextInt();
                    learn(skills.get(idx), plyr);
                } catch (InputMismatchException e) {
                    System.out.println("Type number");
                }
                sc.nextLine();
            } else if (command.equals("breed")) {
                System.out.println("Select Engimon that you want to breed.");
                plyr.breed();
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
            } else if (command.equals("view inventory skill")) {
                System.out.println("Skill in inventory: ");
                plyr.showInventorySkill();
                sc.nextLine();
            } else if (command.equals("switch active engimon")) {
                try {
                    int idx = sc.nextInt();
                    plyr.viewAllEngimon();
                    plyr.changeEngimonActive(idx);
                    System.out.println("Engimon Active Switched");
                    System.out.println("Name: " + plyr.getActiveEngimonPlayer().getName());
                    System.out.println("Coordinate: " + "(" + plyr.getActiveEngimonPlayer().getX() + ","
                            + plyr.getActiveEngimonPlayer().getY() + ")");
                    System.out.println("Species: " + plyr.getActiveEngimonPlayer().getSpecies());
                    System.out.print("Element: ");
                    System.out.println(plyr.getActiveEngimonPlayer().getElement());
                    System.out.println("Life: " + plyr.getActiveEngimonPlayer().getLife());
                    System.out.println("Level: " + plyr.getActiveEngimonPlayer().getLevel());
                    System.out.println("Active: " + plyr.getActiveEngimonPlayer().getActive());
                    System.out.print("Skill: ");
                    if (plyr.getActiveEngimonPlayer().getSkills().size() > 0)
                        plyr.getActiveEngimonPlayer().showSkills();
                    else
                        System.out.println("Skill empty");
                } catch (InputMismatchException e) {
                    System.out.println("Type number");
                } finally {
                    sc.nextLine();
                    System.out.println();
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

    // public void learn(Skill s, EngimonPlayer a){
    public static void learn(Skill skill, Player player) {
        Skill skill_n = new Skill(skill);
        EngimonPlayer ePlayer = player.getActiveEngimonPlayer();
        System.out.println("Learn");
        if (ePlayer.isSkillFull()) {
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

    public static void replaceSkillEngimon(Skill skill, EngimonPlayer ePlayer) {
        System.out.print("Replace? (Y/N) : ");
        Scanner sc = new Scanner(System.in);
        String confirm = sc.next();
        if (confirm == "Y") {
            // showEngimonSkills(e);
            ePlayer.showSkills();
            System.out.println("Skill yang ingin direposisi (1/2/3/4):");
            int x = sc.nextInt();
            // e.skill[x-1] = s;
            ePlayer.skills.set(x - 1, skill);
            System.out.println("Reposisi Skill selesai ...");
        }
        sc.close();
    }

    public static void initSkill(ArrayList<Skill> skills) {
        skills.add(new Skill("Punch", 400, 1, 1, new Element[] { Element.Fire, Element.Ice, Element.Water,
                Element.Electric, Element.Ground, Element.Fire_Electric, Element.Water_Ice, Element.Water_Ground }));
        skills.add(new Skill("JudoKick", 400, 1, 1, new Element[] { Element.Fire, Element.Ice, Element.Water,
                Element.Electric, Element.Ground, Element.Fire_Electric, Element.Water_Ice, Element.Water_Ground }));
        skills.add(new Skill("Fireball", 500, 1, 2, new Element[] { Element.Fire, Element.Fire_Electric }));
        skills.add(new Skill("Flamethrower", 600, 1, 2, new Element[] { Element.Fire, Element.Fire_Electric }));
        skills.add(new Skill("NuclearHit", 700, 1, 3,
                new Element[] { Element.Fire_Electric, Element.Fire, Element.Electric }));
        skills.add(
                new Skill("Wave", 500, 1, 3, new Element[] { Element.Water, Element.Water_Ground, Element.Water_Ice }));
        skills.add(new Skill("Tsunami", 600, 1, 4,
                new Element[] { Element.Water_Ground, Element.Water, Element.Ground, Element.Water_Ice }));
        skills.add(new Skill("Storm", 700, 1, 4,
                new Element[] { Element.Water, Element.Electric, Element.Water_Ground, Element.Water_Ice }));
        skills.add(new Skill("FrostNova", 500, 1, 2, new Element[] { Element.Ice, Element.Water_Ice }));
        skills.add(new Skill("CryogenicFreeze", 600, 1, 2, new Element[] { Element.Ice, Element.Water_Ice }));
        skills.add(new Skill("IceSpikes", 700, 1, 3, new Element[] { Element.Water_Ice, Element.Water, Element.Ice }));
        skills.add(new Skill("Earthquake", 500, 1, 2, new Element[] { Element.Ground, Element.Water_Ground }));
        skills.add(new Skill("MeteorStrike", 600, 1, 3,
                new Element[] { Element.Ground, Element.Fire, Element.Water_Ground }));
        skills.add(new Skill("Asteroid", 700, 1, 2, new Element[] { Element.Ground, Element.Water_Ground }));
        skills.add(new Skill("Lightning", 500, 1, 2, new Element[] { Element.Electric, Element.Fire_Electric }));
        skills.add(new Skill("ElectroBall", 600, 1, 3,
                new Element[] { Element.Fire_Electric, Element.Fire, Element.Electric }));
        skills.add(new Skill("TeslaRay", 700, 1, 2, new Element[] { Element.Electric, Element.Fire_Electric }));
    }

}
