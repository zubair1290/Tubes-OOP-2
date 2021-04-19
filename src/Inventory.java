import java.util.ArrayList;

public class Inventory<T> {
    private ArrayList<T> invent;
    static int countItem;
    static int max = 5;

    public Inventory(){
        ArrayList<T> invent = new ArrayList<>();
    }

    public void addSkill(T sekil){
        boolean ada = false;
        for (T t : invent) {
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

    public int getCountItem(){
        return countItem;
    }

    public int maxCap(){
        return max;
    }

    public boolean isFull(){
        return getCountItem() == maxCap();
    }
};

