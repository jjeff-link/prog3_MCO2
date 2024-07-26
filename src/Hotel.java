import java.util.ArrayList;

/** Represents a Hotel
 * @author Cumti
 * @author Escano
 */
public class Hotel {
    /**
     * represents the name of the hotel
     */
    private String hotelName;
    /**
     * represents the list of rooms in the hotel
     */
    private ArrayList<Room> rooms = new ArrayList<Room>(50);
    /**
     * represents the list of reservations in a hotel
     */
    private ArrayList<Reservation> reservations = new ArrayList<>();

    private double[] dateMultiplier = new double[31];


    /**
     * Creates a Hotel with a specified hotelName
     * @param hotelName unique name assigned to a Hotel
     */
    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.rooms.add(new Standard("S1")); //initializes the first room to a hotel
        for(int i = 0; i < 31; i++) {
            dateMultiplier[i] = 1;
        }
    }

    /**
     * Getter method for the hotelName
     * @return hotelName
     */
    public String getHotelName() {
        return hotelName;
    }

    /**
     * Getter method for totalIncome
     * @return totalIncome
     */
    public double getTotalIncome() {
        double totalIncome = 0;

        for(Reservation reservation : this.reservations){
            totalIncome += reservation.getGuestPrice();
        }
        return totalIncome;
    }

    /**
     * Getter method for rooms (ArrayList of all rooms in the hotel)
     * @return rooms
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * Getter method for reservations (ArrayList of all reservations in the hotel)
     * @return reservations
     */
    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public double[] getDateMultiplier() {
        return dateMultiplier;
    }

    /**
     * Setter method to change hotelName
     * @param hotelName new name to be assigned to hotelName attribute
     */
    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    /**
     * Adds a new reservation to reservations ArrayList
     * @param reservation new reservation to be added to the list
     */
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void setDateMultiplier(int index, double multiplier) {
        this.dateMultiplier[index] = multiplier;
    }
}

