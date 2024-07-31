public class Driver {
    public static void main(String[] args) {
        HotelSystem hotelSystem = new HotelSystem();
        View gui = new View();
        Controller ctr = new Controller(gui, hotelSystem);

        gui.setVisible(true);

    }
}
