// import java.time.LocalTime;
import java.util.ArrayList;

public class ThreadEngimonWild extends Thread {
    private ArrayList<EngimonWild> eWilds;
    private String command;
    private Coordinate bound;

    public ThreadEngimonWild(Coordinate bound, ArrayList<EngimonWild> eWilds) {
        super();
        this.eWilds = eWilds;
        this.command = "";
        this.bound = bound;
    }

    public void run() {
        do {
            try {
                // setiap 10 detik spawn engimon wild
                sleep(10*1000);
                this.eWilds.add(new EngimonWild(bound));
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        } while (!command.equals("q"));
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
