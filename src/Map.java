import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Map {
    private StringBuilder[] map_display;
    private StringBuilder[] map;
    private Coordinate bound;

    public Map(String file) {
        // read file to map
        try {
            File myFile = new File(file);
            BufferedReader br = new BufferedReader(new FileReader(myFile));
            String line;
            map = new StringBuilder[30];
            int count = 0;
            while ((line = br.readLine()) != null) {
                map[count++] = new StringBuilder(line);
            }
            br.close();

            map_display = new StringBuilder[30];
            for (int i=0; i < map.length; i++) {
                map_display[i] = new StringBuilder(map[i]);
            }
            
            int x = 0;
            for (int i=map[1].length()-1; i >= 0; i--) {
                if (map[1].charAt(i) == 'o' || map[1].charAt(i) == '-') {
                    x = i/2;
                    break;
                }
            }
            bound = new Coordinate(x, map.length-2);
            System.out.println("Coordinate: "+bound.getX() + ','+ bound.getY());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public Coordinate getBound() {
        return new Coordinate(this.bound.getX(), this.bound.getY());
    }

    public boolean isInMap(int x, int y) {
        return x >= 1 && y >= 1 && x <= bound.getX() && y <= bound.getY();
    }

    public void updateMap(int x, int y) {
        this.set(x, y, this.get(x, y));
    }

    public void updatePlayerMap(Player plyr) {
        this.set(plyr.getX(), plyr.getY(), 'P');
    }

    public void updateEngimonMap(Engimon engimon) {
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
        this.set(x, y, ch);
    }

    public void Show() {
        for (StringBuilder line : map_display) {
            System.out.println(line);
        }
    }

    public void set(int x, int y, char ch) {
        map_display[y].setCharAt(x*2, ch);
    }

    public char get(int x, int y) {
        return map[y].charAt(x*2);
    }




    
}
