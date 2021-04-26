import java.util.*;

public class Player extends Organism {
    private Inventory<Skill> skills;
    private Inventory<EngimonPlayer> engimons;
    private EngimonPlayer engimonPlayerActive;

    public Player(Coordinate bound, int x, int y) {
        super(bound, x, y);
        skills = new Inventory<>();
        engimons = new Inventory<>();
    }

    public EngimonPlayer getActiveEngimonPlayer() {
        return this.engimonPlayerActive;
    }

    public Inventory<Skill> getInventorySkill() {
        return this.skills;
    }

    public Inventory<EngimonPlayer> getInventoryEngimon() {
        return this.engimons;
    }

    public int getXActiveEngimon() {
        return this.engimonPlayerActive.getX();
    }

    public int getYActiveEngimon() {
        return this.engimonPlayerActive.getY();
    }

    public void removeEngimon(EngimonPlayer ePlayer) {
        InventoryUtility.removeEngimonPlayer(engimons, ePlayer);
    }

    public void addEngimonPlayer(EngimonPlayer ePlayer) {
        if (Inventory.getCountItem() <= 0)
            engimonPlayerActive = ePlayer;
        ePlayer.active = true;
        engimons.addEngimonPlayer(ePlayer);
    }

    public void addSkill(Skill skill) {
        skills.addSkill(skill);
    }

    public void changeEngimonActive(int idx) {
        InventoryUtility.setDeactive(engimonPlayerActive);
        engimonPlayerActive = InventoryUtility.setActive(engimons, idx);
    }

    public void viewAllEngimon() {
        System.out.println("List All Engimon Player:");
        InventoryUtility.viewAll(engimons);
    }

    public void MoveUp() throws CoordinateMapOutOFBoundException {
        try {
            super.MoveUp();
            this.engimonPlayerActive.expUp();
            if (this.engimonPlayerActive.isLevelUp())
                this.engimonPlayerActive.levelUp();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

    public void MoveDown() throws CoordinateMapOutOFBoundException {
        try {
            super.MoveDown();
            this.engimonPlayerActive.expUp();
            if (this.engimonPlayerActive.isLevelUp())
                this.engimonPlayerActive.levelUp();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

    public void MoveLeft() throws CoordinateMapOutOFBoundException {
        try {
            super.MoveLeft();
            this.engimonPlayerActive.expUp();
            if (this.engimonPlayerActive.isLevelUp())
                this.engimonPlayerActive.levelUp();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

    public void MoveRight() throws CoordinateMapOutOFBoundException {
        try {
            super.MoveRight();
            this.engimonPlayerActive.expUp();
            if (this.engimonPlayerActive.isLevelUp())
                this.engimonPlayerActive.levelUp();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

    public int getSurroundingWild(ArrayList<EngimonWild> eWilds) {
        boolean wildFound = false;
        int indWild = -1;
        try {
            for (EngimonWild eWild : eWilds) {
                if (eWild.getX() == this.getX() && eWild.getY() == this.getY() + 1) {
                    wildFound = true;
                    indWild = eWilds.indexOf(eWild);
                    break;
                } else if (eWild.getX() == this.getX() + 1 && eWild.getY() == this.getY()) {
                    wildFound = true;
                    indWild = eWilds.indexOf(eWild);
                    break;
                } else if (eWild.getX() == this.getX() && eWild.getY() == this.getY() - 1) {
                    wildFound = true;
                    indWild = eWilds.indexOf(eWild);
                    break;
                } else if (eWild.getX() == this.getX() - 1 && eWild.getY() == this.getY()) {
                    wildFound = true;
                    indWild = eWilds.indexOf(eWild);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error surrounding EngimonWild ");
        }
        if (!wildFound) {
            System.out.println("No surrounding EngimonWild found");
        }
        return indWild;

    }

    public boolean fight(EngimonWild engLiar) {
        try {
            double multiplierEngPlayer = compareElement(this.engimonPlayerActive.getElement(), engLiar.getElement());
            // int sumTotalSkillPowerEngPlayer =
            // this.engimonPlayerActive.sumTotalSkillPower();
            double totalPowerEngPlayer = this.engimonPlayerActive.getLevel() * multiplierEngPlayer
                    + this.engimonPlayerActive.sumTotalSkillPower();

            double multiplierEngLiar = compareElement(engLiar.getElement(), this.engimonPlayerActive.getElement());
            double totalPowerEngLiar = engLiar.getLevel() * multiplierEngLiar + engLiar.sumTotalSkillPower();

            if (totalPowerEngPlayer >= totalPowerEngLiar) {// if draw then assumed win
                System.out.println("You win the battle");
                return true;
            } else {
                System.out.println("You lose the battle");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error battle");
            return false;
        }
    }

    public void breed() {
        Scanner scan = new Scanner(System.in);
        if (this.engimons.getCountItem() > 1) {
            setBreedLevel();
            try {
                System.out.println("Choose 2 engimon :");
                for (Engimon en : this.engimons.get()) {
                    System.out.println(this.engimons.get().indexOf(en) + ". " + en.getSpecies());
                }
                int inEngParentA = scan.nextInt();
                int inEngParentB = scan.nextInt();
                tryBreed(this.engimons.get().get(inEngParentA), this.engimons.get().get(inEngParentB));
            } catch (Exception e) {
                System.out.println("Error breed()");
            }
        } else {
            System.out.println("Jumlah engimon dimiliki kurang dari 2");
        }
        // scan.close();
    }

    public void tryBreed(EngimonPlayer engParentA, EngimonPlayer engParentB) {
        // EngimonPlayer engParentA;
        // EngimonPlayer engParentB;
        Scanner scan = new Scanner(System.in); // Create a Scanner object
        try {
            if (engParentA.getLevel() >= 4 && engParentB.getLevel() >= 4) {

                // Get Element and species from parents
                Element elementChild = getElementChild(engParentA, engParentB);
                String speciesChild = getSpeciesChild(engParentA, engParentB, elementChild);

                // Get skills from parents
                ArrayList<Skill> listSkillChild = new ArrayList<Skill>(4);
                getListSkillChild(engParentA, engParentB, elementChild, listSkillChild);

                // Get child name
                System.out.println("Enter engimon child name");
                String nameChild = scan.nextLine(); // Read user input
                System.out.println("Engimon child name is: " + nameChild); // Output user input
                // Create child
                if (elementChild != null && speciesChild != null && nameChild != null) {
                    // this.engimons.invent.add(new EngimonPlayer(this.bound,this));
                    this.addEngimonPlayer(new EngimonPlayer(nameChild, elementChild, this.getBound(), false, this));
                }

                // Decrease parent level
                engParentA.decreaseLevelByBreeding();
                engParentB.decreaseLevelByBreeding();

            } else {
                System.out.println("Level parent tidak cukup");
            }

        } catch (Exception e) {
            System.out.println("Error breed");
        }
        // scan.close();
    }

    private String getSpeciesChild(EngimonPlayer engParentA, EngimonPlayer engParentB, Element elementChild) {
        String speciesChild = "";
        try {
            if (elementChild == engParentA.getElement()) {
                speciesChild = engParentA.getSpecies();
            } else if (elementChild == engParentB.getElement()) {
                speciesChild = engParentB.getSpecies();
            } else {
                speciesChild = randomSpecies(elementChild);
            }

        } catch (Exception e) {
            System.out.println("Error species child");

        }
        return speciesChild;
    }

    private String randomSpecies(Element el) {
        String species = "";
        try {
            switch (el) {
            case Fire:
                species = "Fire.Species";
            case Water:
                species = "Water.Species";
            case Electric:
                species = "Electric.Species";
            case Ground:
                species = "Ground.Species";
            case Ice:
                species = "Ice.Species";
            case Fire_Electric:
                species = "Fire_Electric.Species";
            case Water_Ice:
                species = "Water_Ice.Species";
            case Water_Ground:
                species = "Water_Ground.Species";
            }
        } catch (Exception e) {
            System.out.println("Error random species");
        }
        return species;
    }

    public void setBreedLevel() {
        for (Engimon en : this.engimons.get()) {
            en.setLevel(5);
        }
    }

    public void getListSkillChild(EngimonPlayer engParentA, EngimonPlayer engParentB, Element elementChild,
            ArrayList<Skill> listSkillChild) {
        try {
            ArrayList<Skill> listSkillEngParentA = engParentA.getSkills();
            ArrayList<Skill> listSkillEngParentB = engParentB.getSkills();
            // set skill child to skill parent A[0], possibly not compatible with element
            // child

            int indexEngParentA = 0;
            int indexEngParentB = 0;

            Skill skillChild = listSkillEngParentA.get(indexEngParentA);
            boolean fromparentA = true; // is true if from parent A

            int counter = 0;
            // iterasi parent A for skill compatible with element child
            // until found compatible skill or until >parent A skill size
            while (!isSkillCompatibleElement(skillChild, elementChild) && listSkillEngParentA.size() > counter) {
                skillChild = listSkillEngParentA.get(counter);
                counter++;
            }

            counter = 0;
            // iterasi parent B for skill compatible with element
            // until found compatible skill or until >parent B skill size
            while (!isSkillCompatibleElement(skillChild, elementChild) && listSkillEngParentB.size() > counter) {
                skillChild = listSkillEngParentB.get(counter);
                if (isSkillCompatibleElement(skillChild, elementChild)) {
                    fromparentA = false;
                }
                counter++;
            }

            counter = 0;
            while (listSkillChild.size() < 4 && listSkillEngParentA.size() + listSkillEngParentB.size() > counter) {

                // Get skill with maximum mastery level
                // iteration of list skill parent A
                while (indexEngParentA < listSkillEngParentA.size()) {
                    // priority skill parent A if skill level A equal skill level B
                    if (isSkillCompatibleElement(listSkillEngParentA.get(indexEngParentA), elementChild) && skillChild
                            .getMasteryLevel() <= listSkillEngParentA.get(indexEngParentA).getMasteryLevel()) {
                        skillChild = listSkillEngParentA.get(indexEngParentA);
                        fromparentA = true;
                    }
                    // iteration of list skill parent B
                    while (indexEngParentB < listSkillEngParentB.size()) {

                        // skill level A.get(indexEngParentA) < skill level B.get(indexEngParentB)
                        if (isSkillCompatibleElement(listSkillEngParentB.get(indexEngParentB), elementChild)
                                && skillChild.getMasteryLevel() < listSkillEngParentB.get(indexEngParentB)
                                        .getMasteryLevel()) {
                            skillChild = listSkillEngParentB.get(indexEngParentB);
                            fromparentA = false;
                        }
                        // else skill level A.get(indexEngParentA) > skill level B.get(indexEngParentB)
                        // else skill level A.get(indexEngParentA) = skill level B.get(indexEngParentB)
                        indexEngParentB++;
                    }
                    indexEngParentA++;
                }

                if (fromparentA) { // skill from parent A, check list skill B for equal skill names
                    for (int j = 0; j < listSkillEngParentB.size(); j++) {
                        // if skill name from parent A is in parent B
                        if (skillChild.getSkill() == listSkillEngParentB.get(j).getSkill()) {

                            // if mastery level A = mastery level B
                            if (skillChild.getMasteryLevel() == listSkillEngParentB.get(j).getMasteryLevel()) {
                                skillChild.setSkillLevel(skillChild.getMasteryLevel() + 1);
                            }
                            // if mastery level A < mastery level B
                            if (skillChild.getMasteryLevel() < listSkillEngParentB.get(j).getMasteryLevel()) {
                                skillChild.setSkillLevel(listSkillEngParentB.get(j).getMasteryLevel());
                            }
                            // else (mastery level A > mastery level B)
                        }
                    }
                } else { // skill is from parent B, check skill list parent A for equal skill names
                    for (int j = 0; j < listSkillEngParentA.size(); j++) {
                        // if skill name from parent A is in parent B
                        if (skillChild.getSkill() == listSkillEngParentA.get(j).getSkill()) {

                            // if mastery level A = mastery level B
                            if (skillChild.getMasteryLevel() == listSkillEngParentA.get(j).getMasteryLevel()) {
                                skillChild.setSkillLevel(skillChild.getMasteryLevel() + 1);
                            }
                            // if mastery level A < mastery level B
                            if (skillChild.getMasteryLevel() < listSkillEngParentA.get(j).getMasteryLevel()) {
                                skillChild.setSkillLevel(listSkillEngParentA.get(j).getMasteryLevel());
                            }
                            // else (mastery level B > mastery level A)
                        }
                    }
                }
                listSkillChild.add(skillChild);
                counter++;
            }
        } catch (Exception e) {
            System.out.println("Error getlistskillchild()");
        }
    }

    private boolean isSkillCompatibleElement(Skill skillEngimon, Element element) {
        boolean found = false;
        ArrayList<Element> elementSkill = skillEngimon.getElements();
        for (int i = 0; i < elementSkill.size(); i++) {
            if (elementSkill.get(i) == element) {
                found = true;
            }
        }
        return found;
    }

    private Element getElementChild(EngimonPlayer engParentA, EngimonPlayer engParentB) {
        Element getElement = Element.Fire;
        try {
            Element elementEngParentA = engParentA.getElement();
            Element elementEngParentB = engParentB.getElement();
            // get species child & element child
            int randomhalf = 1;// std::rand() %2 ;
            boolean isParentADual = false;
            boolean isParentBDual = false;

            if (elementEngParentA == Element.Fire_Electric || elementEngParentA == Element.Water_Ice
                    || elementEngParentA == Element.Water_Ground) {
                isParentADual = true;
            }
            if (elementEngParentB == Element.Fire_Electric || elementEngParentB == Element.Water_Ice
                    || elementEngParentB == Element.Water_Ground) {
                isParentBDual = false;
            }

            if (elementEngParentA == elementEngParentB) {
                // parent A= element parent B=element child
                getElement = elementEngParentA;
            } else {// A & B different element
                    // get elemental advantage of parent A & B
                double multiplierParentA = compareElement(elementEngParentA, elementEngParentB);
                double multiplierParentB = compareElement(elementEngParentA, elementEngParentB);
                if (multiplierParentA > multiplierParentB) {
                    getElement = elementEngParentA;
                } else if (multiplierParentA < multiplierParentB) {
                    getElement = elementEngParentB;
                } else {// A & B equal element advantage

                    // parent A or B is dual element or both A & B is dual element
                    // element child & species child random from element parent A or B
                    if (isParentADual || isParentBDual) {
                        if (isParentADual && isParentBDual) {
                            // breed is either fire electric x water ground or fire electric x water ice
                            // possible child element : fire,electric,fire electric ,
                            // water,ground,ice,water_ground, water_ice
                            // if element child is fire,electric ,water,ground, ice, species child is random
                            // if fire_electric , species child is parent with fire_electric element
                            // if water_ground, species child is parent with water_ground element
                            // if water_ice, species child is parent with water_ice element
                            int randomsix = 1;// std::rand() % 6;
                            switch (randomsix) {
                            case 0:
                                getElement = Element.Fire;
                                break;
                            case 1:
                                getElement = Element.Electric;
                                break;
                            case 2:
                                getElement = Element.Fire_Electric;
                                break;
                            case 3:
                                getElement = Element.Water;
                                break;
                            case 4:
                                if (elementEngParentA == Element.Water_Ground
                                        || elementEngParentB == Element.Water_Ground) {
                                    getElement = Element.Water_Ground;
                                } else if (elementEngParentA == Element.Water_Ice
                                        || elementEngParentB == Element.Water_Ice) {
                                    getElement = Element.Water_Ice;
                                }
                                break;
                            case 5:
                                if (elementEngParentA == Element.Water_Ground
                                        || elementEngParentB == Element.Water_Ground) {
                                    getElement = Element.Ground;
                                } else if (elementEngParentA == Element.Water_Ice
                                        || elementEngParentB == Element.Water_Ice) {
                                    getElement = Element.Ice;
                                }
                                break;
                            }

                        } else {
                            // breed is single element x dual element
                            switch (randomhalf) {
                            case 0:
                                getElement = elementEngParentA;
                                break;
                            case 1:
                                getElement = elementEngParentB;
                                break;
                            }
                        }
                    } else {
                        // breed single element x single element
                        // both parent's element advantage is equal
                        // breed is either fire x electric , water x ground , water x ice
                        // child element is dual element
                        // child species get random with compatible dual element
                        // case Fire_electric, Water_ground, Water_Ice not needed
                        switch (elementEngParentA) {
                        case Fire:
                            if (elementEngParentB == Element.Electric) {
                                getElement = Element.Fire_Electric;
                            }
                            break;
                        case Water:
                            if (elementEngParentB == Element.Ground) {
                                getElement = Element.Water_Ground;
                            } else {
                                getElement = Element.Water_Ice;
                            }
                            break;
                        case Electric:
                            if (elementEngParentB == Element.Fire) {
                                getElement = Element.Fire_Electric;
                            }
                            break;
                        case Ground:
                            if (elementEngParentB == Element.Water) {
                                getElement = Element.Water_Ground;
                            }
                            break;
                        case Ice:
                            if (elementEngParentB == Element.Water) {
                                getElement = Element.Water_Ice;
                            }
                            break;
                        // case Fire_Electric:
                        // getElement = Element.Fire_Electric;
                        // break;
                        // case Water_Ice:
                        // getElement = Element.Water_Ice;
                        // break;
                        // case Water_Ground:
                        // getElement = Element.Water_Ground;
                        // break;
                        //
                        }

                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error get element child");
        }
        return getElement;
    }

    public double compareElement(Element a, Element b) { // b = target
        double mult = 0;
        try {
            switch (a) {
            case Fire:
                switch (b) {
                case Fire:
                    mult = 1;
                    break;
                case Water:
                    mult = 0;
                    break;
                case Electric:
                    mult = 1;
                    break;
                case Ground:
                    mult = 0.5;
                    break;
                case Ice:
                    mult = 2;
                    break;
                case Fire_Electric:
                    mult = 1;
                    break;
                case Water_Ice:
                    mult = 2;
                    break;
                case Water_Ground:
                    mult = 0.5;
                    break;
                }
                break;
            case Water:
                switch (b) {
                case Fire:
                    mult = 2;
                    break;
                case Water:
                    mult = 1;
                    break;
                case Electric:
                    mult = 0;
                    break;
                case Ground:
                    mult = 1;
                    break;
                case Ice:
                    mult = 1;
                    break;
                case Fire_Electric:
                    mult = 2;
                    break;
                case Water_Ice:
                    mult = 1;
                    break;
                case Water_Ground:
                    mult = 1;
                    break;
                }
                break;
            case Electric:
                switch (b) {
                case Fire:
                    mult = 1;
                    break;
                case Water:
                    mult = 2;
                    break;
                case Electric:
                    mult = 1;
                    break;
                case Ground:
                    mult = 0;
                    break;
                case Ice:
                    mult = 1.5;
                    break;
                case Fire_Electric:
                    mult = 1;
                    break;
                case Water_Ice:
                    mult = 2;
                    break;
                case Water_Ground:
                    mult = 2;
                    break;
                }
                break;
            case Ground:
                switch (b) {
                case Fire:
                    mult = 1.5;
                    break;
                case Water:
                    mult = 1;
                    break;
                case Electric:
                    mult = 2;
                    break;
                case Ground:
                    mult = 1;
                    break;
                case Ice:
                    mult = 0;
                    break;
                case Fire_Electric:
                    mult = 2;
                    break;
                case Water_Ice:
                    mult = 1;
                    break;
                case Water_Ground:
                    mult = 1;
                    break;
                }
                break;
            case Ice:
                switch (b) {
                case Fire:
                    mult = 0;
                    break;
                case Water:
                    mult = 1;
                    break;
                case Electric:
                    mult = 0.5;
                    break;
                case Ground:
                    mult = 2;
                    break;
                case Ice:
                    mult = 1;
                    break;
                case Fire_Electric:
                    mult = 0.5;
                    break;
                case Water_Ice:
                    mult = 1;
                    break;
                case Water_Ground:
                    mult = 2;
                    break;
                }
                break;
            case Fire_Electric:
                switch (b) {
                case Fire:
                    mult = 1;
                    break;
                case Water:
                    mult = 2;
                    break;
                case Electric:
                    mult = 1;
                    break;
                case Ground:
                    mult = 0.5;
                    break;
                case Ice:
                    mult = 2;
                    break;
                case Fire_Electric:
                    mult = 1;
                    break;
                case Water_Ice:
                    mult = 2;
                    break;
                case Water_Ground:
                    mult = 2;
                    break;
                }
                break;
            case Water_Ice:
                switch (b) {
                case Fire:
                    mult = 2;
                    break;
                case Water:
                    mult = 1;
                    break;
                case Electric:
                    mult = 0.5;
                    break;
                case Ground:
                    mult = 2;
                    break;
                case Ice:
                    mult = 1;
                    break;
                case Fire_Electric:
                    mult = 2;
                    break;
                case Water_Ice:
                    mult = 1;
                    break;
                case Water_Ground:
                    mult = 2;
                    break;
                }
                break;
            case Water_Ground:
                switch (b) {
                case Fire:
                    mult = 2;
                    break;
                case Water:
                    mult = 1;
                    break;
                case Electric:
                    mult = 2;
                    break;
                case Ground:
                    mult = 1;
                    break;
                case Ice:
                    mult = 1;
                    break;
                case Fire_Electric:
                    mult = 2;
                    break;
                case Water_Ice:
                    mult = 1;
                    break;
                case Water_Ground:
                    mult = 1;
                    break;
                }
                break;
            }
        } catch (Exception e) {
            System.out.println("Element error");
        }
        return mult;

    }

}
