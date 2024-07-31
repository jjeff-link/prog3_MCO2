import java.util.ArrayList;

/**
 * Represents a reservation
 * @author Cumti
 * @author Escano
 */
public class Reservation{
    /**
     * name of the guest under the reservation
     */
    private final String guestName;
    /**
     * date of check in
     */
    private final int checkInDate;
    /**
     * date of check out
     */
    private final int checkOutDate;
    /**
     * total computed price of reservation
     */
    private final double guestPrice;
    /**
     * represents room assigned to the reservation
     */
    private final Room room;

    /**
     * represents the price breakdown string/receipt for the reservation
     */
    private final ArrayList<String> breakdown;

    /**
     * Creates a reservation
     * @param guestName name of guest
     * @param checkInDate date of check in
     * @param checkOutDate date of check out
     * @param room room which is assigned to the reservation
     */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room, double guestPrice, ArrayList<String> breakdown){
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.guestPrice = guestPrice;
        this.breakdown = breakdown;
    }

    /**
     * Getter method to get name of guest
     * @return guestName
     */
    public String getGuestName(){
        return guestName;
    }

    /**
     * Getter method for the check in date
     * @return checkInDate
     */
    public int getCheckInDate(){
        return checkInDate;
    }

    /**
     * Getter method for the check-out date
     * @return chechOutDate
     */
    public int getCheckOutDate(){
        return checkOutDate;
    }

    /**
     * Getter method for the price of the created reservation
     * @return guestPrice
     */
    public double getGuestPrice(){
        return guestPrice;
    }

    /**
     * Getter method to get Room assigned to reservation
     * @return room
     */
    public Room getRoom(){
        return room;
    }

    /**
     * Getter method to get the price breakdown string of the reservation
     * @return breakdown
     * **/
    public ArrayList<String> getBreakdown(){
        return breakdown;
    }
}
