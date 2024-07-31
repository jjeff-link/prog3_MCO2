/**
 * Serves as the Main Driver Class for the program.
 */
public class Driver {
    public static void main(String[] args) {
        HotelSystem hotelSystem = new HotelSystem();
        MainGUI gui = new MainGUI();
        Controller ctr = new Controller(gui, hotelSystem);


    }
}
