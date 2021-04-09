import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Map {
    private StringBuilder[] map;

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
        } catch (IOException e) {
            System.out.println(e);
            // TODO: handle exception
        }
    }


    public boolean isInMap() {
        return true;
    }

    public void Show() {
        for (StringBuilder line : map) {
            System.out.println(line);
        }
    }

    public void set(int x, int y, char ch) {
        map[y].setCharAt(x*2, ch);
    }




    
}
