import java.util.ArrayList;

/**
 * View Class. For displaying requested info from the Controller Class.
 * @author Cumti
 * @author Escano
 */
public class Viewer {

    /**
     * Default Constructor. Does not have any required parameters
     */
    public Viewer(){}

    /**
     * This method displays the available dates of a room in a Calendar format.
     * @param room Specific room in a certain hotel
     */
    public void displayRoomCalendar(Room room) {
        System.out.println("____ JULY  2024 ____");
        System.out.println("Su Mo Tu We Th Fr Sa");
        System.out.print("   ");
        for(int i = 0; i < 31; i++){
            if(!room.getDates()[i]) {
                System.out.printf("%2d ", i + 1);
            }
            else
                System.out.print("   ");

            if((i + 1 + 1) % 7 == 0){
                System.out.println();
            }
            if(i == 30)
                System.out.println();
        }
    }



    /**
     * Displays Basic hotel information
     * @param hotel specific hotel called
     */
    public void viewHotelBasic(Hotel hotel) {
        System.out.println("Hotel Name: " + hotel.getHotelName());
        System.out.println("Total Rooms: " + hotel.getRooms().size());
        System.out.println("Total Income: " + hotel.getTotalIncome());
        System.out.println();
    }

    /**
     * Displays room availability based on information entered
     * @param date date inputted
     * @param bookedRooms number of booked rooms
     * @param availableRooms number of rooms available
     */
    public void viewRoomsOnDate(int date, int bookedRooms, int availableRooms){
        System.out.println("Date: June " + date + ", 2024");
        System.out.println("Booked Rooms: " + bookedRooms);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println();
    }

    /**
     * Displays specified Room information in a hotel
     * @param room inputted room
     */
    public void viewRoomInfo(Room room){
        System.out.println("Room Name: " + room.getRoomName());
        System.out.println("Price per Night: " + room.getPrice());
        System.out.println("Dates available: ");
        displayRoomCalendar(room);
        System.out.println();
    }

    /**
     * Displays Price Breakdown before booking is confirmed
     * @param hotel chosen hotel
     * @param guestName inputted guest name
     * @param checkinDate date of check in
     * @param checkOutDate date of check out
     */
    public void displayPriceBreakdown(Hotel hotel, String guestName, int checkinDate, int checkOutDate){
        int nights = checkOutDate - checkinDate;
        double price = hotel.getRooms().get(0).getPrice();
        System.out.println("Room Price per Night: P " + price);
        System.out.println("                     x " + nights + " nights");
        System.out.println("___________________________________");
        System.out.println("                    Total: P " + price * nights);
        System.out.println();
    }

    /**
     * Displays Price Breakdown of current reservation in a system
     * @param reservation inputted reservation
     */
    public void displayPriceBreakdown(Reservation reservation){
        int nights = reservation.getCheckOutDate() - reservation.getCheckInDate();
        System.out.println("Room Price per Night: P " + reservation.getRoom().getPrice());
        System.out.println("                     x " + nights + " nights");
        System.out.println("___________________________________");
        System.out.println("                    Total: P " + reservation.getGuestPrice());
        System.out.println();
    }

    /**
     * Displays information of a specific entered reservation
     * @param reservation inputted reservation
     */
    public void viewReservationInfo(Reservation reservation){
        System.out.println("Guest Name: " + reservation.getGuestName());
        System.out.println("Room Booked: " + reservation.getRoom().getRoomName());
        System.out.println("Check-in Date: " + reservation.getCheckInDate());
        System.out.println("Check-Out Date: " + reservation.getCheckOutDate());
        System.out.println();
        displayPriceBreakdown(reservation);
        System.out.println();
    }

    /**
     * Prompt displayed before a booking is confirmed
     * @param hotel hotel chosen
     * @param guestName guest name entered
     * @param checkinDate date of check in
     * @param checkOutDate date of check out
     */
    public void confirmDetails(Hotel hotel, String guestName, int checkinDate, int checkOutDate){
        System.out.println("Booking Details: ");
        System.out.println("Hotel Name: " + hotel.getHotelName());
        System.out.println("Guest Name: " + guestName);
        System.out.println("Checkin Date: July " + checkinDate + ", 2024");
        System.out.println("Checkout Date: July " + checkOutDate + ", 2024");
        System.out.println();
        displayPriceBreakdown(hotel, guestName, checkinDate, checkOutDate);
        System.out.print("Confirm Booking(Y/N): ");
    }

    /**
     * Displays all current hotels (arrayList) in a system
     * @param hotels arrayList of hotels
     */
    public void displayHotels(ArrayList<Hotel> hotels){
        System.out.println("Hotel List: ");
        for(Hotel hotel : hotels)
            System.out.println(hotel.getHotelName());
        System.out.println();
    }

    /**
     * Displays manager options for a hotel
     */
    public void manageHotelPrompt(){
        System.out.println("Manage Hotel ");
        System.out.println("(1) - Change Hotel Name");
        System.out.println("(2) - Add Room(s)");
        System.out.println("(3) - Remove Room(s)");
        System.out.println("(4) - Update Base price for Rooms");
        System.out.println("(5) - Change Date Multiplier");
        System.out.println("(6) - Remove reservation");
        System.out.println("(7) - Remove Hotel");
        System.out.println("Select option: ");
    }

    /**
     *  Displays the Main Menu options
     */
    public void displayMenu(){
        System.out.println("Main Menu");
        System.out.println("(1) - Register New Hotel");
        System.out.println("(2) - View Hotel");
        System.out.println("(3) - Manage Hotel");
        System.out.println("(4) - Book Reservation");
        System.out.println("(5) - Shutdown");
        System.out.println("Select option: ");
    }
}
