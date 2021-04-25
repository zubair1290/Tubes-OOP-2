import java.util.ArrayList;

public class Inventory<T extends InventoryParent> {
    private ArrayList<T> invent;
    static private int countItem;
    static private int max = 5;

    public Inventory() {
        invent = new ArrayList<>();
    }

    public void addSkill(T sekil){
        boolean ada = false;
        for (InventoryParent t : invent) {
            if (sekil == t) {
                ada = true;
                break;
            }
        }
        if(!ada && !isFull()){
            invent.add(sekil);
            countItem++;
        }
    }

    public void addEngimonPlayer(T engi){
        if(!isFull()){
            invent.add(engi);
            countItem++;
        }
    }

    public ArrayList<T> get(){
        return invent;
    }

    public static int getCountItem(){
        return countItem;
    }

    public static int maxCap(){
        return max;
    }

    public static void addCountItem() { countItem++; }
    public static void reduceCountItem() { countItem++; }

    public boolean isFull(){
        return getCountItem() == maxCap();
    }
};

