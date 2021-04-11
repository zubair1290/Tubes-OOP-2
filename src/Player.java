import java.util.*;

public class Player extends Organism {
    private Inventory<Skill> skills;
    private Inventory<EngimonPlayer> engimons;
    private EngimonPlayer engimonPlayerActive;

    public Player(Coordinate Bound, int x, int y) {
        super(Bound, x, y);
    }

    public void changeEngimonActive(EngimonPlayer ePlayer, int idx) {
        ePlayer = this.setActiveEngimon(idx);
    }

    private EngimonPlayer setActiveEngimon(int idx) {
        return null;
        // return engimons.setActive(idx);
    }

    public void viewAllEngimon() {
        // engimons.viewAll();
    }

    public void MoveUp() throws CoordinateMapOutOFBoundException {
        try {
            super.MoveUp();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

    public void MoveDown() throws CoordinateMapOutOFBoundException {
        try {
            super.MoveDown();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

    public void MoveLeft() throws CoordinateMapOutOFBoundException {
        try {
            super.MoveLeft();
        } catch (CoordinateMapOutOFBoundException e) {
            throw new CoordinateMapOutOFBoundException(this.getBound());
        }
    }

    public void MoveRight() throws CoordinateMapOutOFBoundException {
        try {
            super.MoveRight();
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
            // int sumTotalSkillPowerEngPlayer=
            // this.engimonPlayerActive.sumTotalSkillPower();
            double totalPowerEngPlayer = this.engimonPlayerActive.getLevel() * multiplierEngPlayer;// +sumSkillEngPlayer;

            double multiplierEngLiar = compareElement(engLiar.getElement(), this.engimonPlayerActive.getElement());
            // int sumTotalSkillPoweEngLiar= engLiar.sumTotalSkillPower();
            double totalPowerEngLiar = engLiar.getLevel() * multiplierEngLiar; // +sumSkillEngLiar;

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

    public void breed(EngimonPlayer engParentA, EngimonPlayer engParentB) {
        try {
            if (engParentA.getLevel() > 30 && engParentB.getLevel() > 30) {

                Element elementChild;
                String speciesChild;
                // Get Element and species from parents
                elementChild = getElementChild(engParentA, engParentB);
                speciesChild = getSpeciesChild(engParentA, engParentB, elementChild);

                // Get skills from parents

                // Get child name
                Scanner scan = new Scanner(System.in); // Create a Scanner object
                System.out.println("Enter engimon child name");
                String nameChild = scan.nextLine(); // Read user input
                System.out.println("Engimon child name is: " + nameChild); // Output user input
                scan.close();
                // Create child
                if (elementChild != null && speciesChild != null && nameChild != null) {
                    // this.engimons.invent.add(new EngimonPlayer(this.bound,this));
                }

                // Decrease parent level
                // engParentA.setLevel(engParentA.getLevel()-30);
                // engParentB.setLevel(engParentB.getLevel()-30);
            } else {
                System.out.println("Level parent tidak cukup");
            }

        } catch (Exception e) {
            System.out.println("Error breed");
        }
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

    private String randomSpecies(Element elementChild) {
        String species = "";
        try {
            switch (elementChild) {
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
