public class Driver {
    public static void main(String[] args) {
        HotelSystem hotelSystem = new HotelSystem();
        View gui = new View();
        tempCtr ctr = new tempCtr(gui, hotelSystem);

        gui.setVisible(true);
//        HotelSystem hotelSystem = new HotelSystem(); //instantiated system
//        Controller controller = new Controller(hotelSystem); //instantiated controller (given the system)

//        System.out.println("---HOTEL RESERVATION SYSTEM---");
//        System.out.println("  --By: Cumti & Escano-- \n");
//        controller.menu(); //calls controller object
//        System.out.println("Program Shut down!"); //display once the program ends
    }
}
