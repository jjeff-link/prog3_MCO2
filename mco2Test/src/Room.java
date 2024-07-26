/** Represents a Room
 * @author Cumti
 * @author Escano
 */
abstract class Room {
    /**
     * represents the room name
     */
    private final String roomName;
    /**
     * represents the price of the room
     */
   protected double price;
    /**
     * refers to the occupancies on a specific date index. Occupied (true) or vacant (false)
     */
    private boolean[] dates = new boolean[31];

    /**
     * Creates new Room with given parameters
     * @param roomName given name of the Room
     */
    public Room(String roomName) {
        this.roomName = roomName; //initialized room name from parameter
        this.price = 1299.0;
        for (int i = 0; i < 31; i++) {
            dates[i] = false;     //initializes all dates to false (referring to occupancy)
        }

    }

    /**
     * Getter method for the roomName
     * @return roomName
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Getter method for the room Price
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter method for dates (Boolean array for all the date vacancies in the room)
     * @return dates
     */
    public boolean[] getDates() {
        return dates;
    }

    /**
     * Setter method to update the room price
     * @param price new update price of the room
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Setter method to update the date availability in a room
     * @param dateIndex index/date
     * @param value boolean value if occupied (true) or not (false)
     */
    public void setDate(int dateIndex,  boolean value) {
        dates[dateIndex] = value;
    }



}
