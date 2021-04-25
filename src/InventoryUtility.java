
public class InventoryUtility {
    public static EngimonPlayer setActive(Inventory<EngimonPlayer> engimons, int idx) {
        EngimonPlayer ePlayer = engimons.get().get(idx);
        ePlayer.active = true;
        return ePlayer;
    }

    public static void setDeactive(EngimonPlayer ePlayer) {
        ePlayer.active = false;
    }

    public static void viewAll(Inventory<EngimonPlayer> engimons) {
        for (EngimonPlayer ePlayer : engimons.get()) {
            System.out.println("Name: " + ePlayer.getName());
            System.out.println("Coordinate: " + "(" + ePlayer.getX() + "," + ePlayer.getY() + ")");
            System.out.println("Species: " + ePlayer.getSpecies());
            System.out.print("Element: ");
            System.out.println(ePlayer.getElement());
            System.out.println("Life: " + ePlayer.getLife());
            System.out.println("Level: " + ePlayer.getLevel());
            System.out.println("Active: " + ePlayer.getActive());
            System.out.println();
        }
    }
}
