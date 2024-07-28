import java.util.ArrayList;

/** Represents a Hotel System
 * @author Cumti
 * @author Escano
 */
public class HotelSystem implements Discounts{
    /**
     * Represents the list of hotels in a system
     */
    public ArrayList<Hotel> hotels = new ArrayList<Hotel>();

    /**
     * Creates a HotelSystem. Default constructor
     */
    public HotelSystem() {}

    /**
     * Searches for a hotel from a name and returns its index in hotels if found
     * @param hotelName name of the hotel to be searched
     * @return index of hotel in hotels
     */
    public int searchHotel(String hotelName){
        for(Hotel hotel : hotels){
            if(hotel.getHotelName().equalsIgnoreCase(hotelName)){
                return hotels.indexOf(hotel);       //returns index is found in hotels
            }
        }
        return -1; //returns -1 if index is not found
    }

    /**
     * Searches for a room from its name and returns its index in rooms if found
     * @param hotel Specific hotel to search in
     * @param roomName unique name of Room in hotel
     * @return index of the room in rooms of a hotel
     */
    public int searchRoom(Hotel hotel, String roomName){
        for(Room room : hotel.getRooms()){
            if(room.getRoomName().equals(roomName)){
                return hotel.getRooms().indexOf(room); //returns index if name is found
            }
        }
        return -1; //returns -1 if no room is found
    }

    /**
     * Method to check if a number is within a range
     * @param n number to be checked
     * @param low minimum value in range
     * @param high maximum value in range
     * @return boolean value (true/false) if n is within the range
     */
    private boolean inRange(int n, int low, int high){
        return n >= low && n <= high;
    }

    /**
     * Assigns the name of a room to a new hotel
     * @param hotel specific hotel where a new room is made
     * @return name of room
     */
    public String assignRoomName(Hotel hotel, int roomType) {
        int currStandard = 0;
        int currDeluxe = 0;
        int currExecutive = 0;

        for (Room room : hotel.getRooms()) {
            if (room instanceof Standard)
                currStandard++;
            if (room instanceof Deluxe)
                currDeluxe++;
            if (room instanceof Executive)
                currExecutive++;
        }

        if (roomType == 1) {
            if (currStandard < 9)
                return "S0" + (currStandard + 1);
            return "S" + (currStandard + 1);
        }
        if (roomType == 2){
            if (currDeluxe < 9)
                return "D0" + (currDeluxe + 1);
            return "D" + (currDeluxe + 1);
        }
        if(currExecutive < 9)
            return "E0" + (currExecutive + 1);
        return "E" + (currExecutive + 1);

    }

    /**
     * Finds the first vacant room in given dates
     * @param hotel hotel to search in
     * @param checkInDate date of checkIn
     * @param checkOutDate date of checkOut
     * @return index of first vacant room
     */
    public int findVacantRoom(Hotel hotel, int checkInDate, int checkOutDate, int roomType) {
        // Validate the dates
        if (checkInDate >= checkOutDate || checkInDate < 1) {
            throw new IllegalArgumentException("Invalid check-in or check-out date");
        }

        for (int i = 0; i < hotel.getRooms().size(); i++) {
            Room room = hotel.getRooms().get(i);

            // Check if the room matches the specified room type
            if ((roomType == 1 && room instanceof Standard) ||
                    (roomType == 2 && room instanceof Deluxe) ||
                    (roomType == 3 && room instanceof Executive)) {

                boolean isVacant = true;

                // Iterate over the dates to check if the room is available for the entire period
                for (int j = checkInDate - 1; j < checkOutDate - 1; j++) {
                    if (room.getDates()[j]) { // If any date is occupied, the room is not vacant
                        isVacant = false;
                        break;
                    }
                }

                if (isVacant) {
                    return i; // Return index of first vacant room of the specified type
                }
            }
        }

        return -1; // No vacant room of the specified type found
    }


    /**
     * Updates the date occupancies of a specific room
     * @param room room which will update its dates
     * @param checkInDate date of checkIn
     * @param checkOutDate date of checkOut
     */
    public void updateDates(Room room, int checkInDate, int checkOutDate){
        int i;
        for(i = checkInDate - 1; i < checkOutDate - 1; i++){
            room.setDate(i, true); //calls the date setter in a room class. Updates it to true (occupied)
        }
    }

    /**
     * Adds a new hotel to hotels Arraylist given a unique name
     * @param hotelName unique hotel name for new hotel
     */
    public void addHotel(String hotelName) {
        if(searchHotel(hotelName) == -1) { //if no hotel has the same name
            Hotel hotel = new Hotel(hotelName); //instantiates a new hotel with the given name
            hotels.add(hotel);                     //adds this new hotel to hotels Arraylist
            System.out.println("Hotel added: " + hotelName + "\n"); //Confirmation message
        }
        else
            System.out.println("Hotel already exists\n"); //if an existing hotel has the same name

    }

    /**
     * Returns total number of booked rooms on a given date
     * @param hotel hotel to search in
     * @param date date to check
     * @return total number of bookedRooms in a hotel
     */
    public int bookedRooms(Hotel hotel, int date){
        int bookedRooms = 0;
        for(Room room : hotel.getRooms()){
            if(room.getDates()[date - 1]){
                bookedRooms++;      //incremented by 1 for every room occupied on the date
            }
        }
        return bookedRooms;
    }

    /**
     * Changes the name of a hotel
     * @param hotel hotel which will update its name
     * @param newHotelName new name of the hotel
     */
    public void changeHotelName(Hotel hotel, String newHotelName) {
        if (searchHotel(newHotelName) == -1) { //checks if no hotel has the same name
            hotel.setHotelName(newHotelName);   //uses setter method to change name of hotel
            System.out.println("Hotel changed to " + newHotelName + "\n"); //confirmation message
        }
        else
            System.out.println("Hotel already exists\n"); //returns error message if hotelName is taken
    }


    /**
     * Adds a new room to a hotel. nRooms is checked before calling, thus is assumed valid upon calling the method.
     * @param hotel hotel which a new room will be added
     * @param nRooms amount of rooms to add
     */
    public void addRoom(Hotel hotel, int nRooms, int roomType){
        int i;

        if(hotel.getRooms().size() + nRooms > 50) //checks if the added rooms will exceed 50 (maximum rooms in hotel)
            System.out.println("Maximum number of rooms exceeded\n"); //error message
        else if(nRooms <= 0)
            System.out.println("Invalid input\n");
        else {
            for (i = 0; i < nRooms; i++) {
                if(roomType == 1) {
                    Standard room = new Standard(assignRoomName(hotel, 1)); //calls assignRoomName to find name to the new room
                    hotel.getRooms().add(room);
                }
                else if (roomType == 2) {
                    Deluxe room = new Deluxe(assignRoomName(hotel, 2));
                    hotel.getRooms().add(room);
                }
                else{
                    Executive room = new Executive(assignRoomName(hotel, 3));
                    hotel.getRooms().add(room);
                }
            }
            System.out.println("Rooms added: " + nRooms + "\n"); //confirmation message
        }
    }

    /**
     * Removes a room from a hotel
     * @param hotel hotel which a room will be removed
     * @param nRooms number of rooms to be removed
     */
    public void removeRoom(Hotel hotel, int nRooms, int roomType) {
        int i;
        boolean isEmpty = true; //boolean to check if room has no active reservation

        for (i = hotel.getRooms().size() - 1; i > 0 && nRooms > 0; i--) {
            if(roomType == 1 && hotel.getRooms().get(i) instanceof Standard) {
                for (Reservation reservation : hotel.getReservations()) {
                    if (reservation.getRoom().getRoomName().equals(hotel.getRooms().get(i).getRoomName())) {
                        isEmpty = false;    //checks if room has no reservation
                    }
                }
                if (isEmpty) {
                    System.out.println(hotel.getRooms().get(i).getRoomName() + " removed\n"); //confirmation message
                    hotel.getRooms().remove(i);     //room is removed if empty
                    nRooms--;
                }
            }
            if(roomType == 2 && hotel.getRooms().get(i) instanceof Deluxe) {
                for (Reservation reservation : hotel.getReservations()) {
                    if (reservation.getRoom().getRoomName().equals(hotel.getRooms().get(i).getRoomName())) {
                        isEmpty = false;    //checks if room has no reservation
                    }
                }
                if (isEmpty) {
                    System.out.println(hotel.getRooms().get(i).getRoomName() + " removed\n"); //confirmation message
                    hotel.getRooms().remove(i);     //room is removed if empty
                    nRooms--;
                }
            }
            if(roomType == 3 && hotel.getRooms().get(i) instanceof Executive) {
                for (Reservation reservation : hotel.getReservations()) {
                    if (reservation.getRoom().getRoomName().equals(hotel.getRooms().get(i).getRoomName())) {
                        isEmpty = false;    //checks if room has no reservation
                    }
                }
                if (isEmpty) {
                    System.out.println(hotel.getRooms().get(i).getRoomName() + " removed\n"); //confirmation message
                    hotel.getRooms().remove(i);     //room is removed if empty
                    nRooms--;
                }
            }
        }


//        if (hotel.getRooms().size() == 1 && nRooms > 0)
//            System.out.println("Cannot remove more rooms.\n"); //if no more rooms can be removed
//
//        for (i = hotel.getRooms().size() - 1; i > 0 && nRooms > 0; i--) {
//            for (Reservation reservation : hotel.getReservations()) {
//                if (reservation.getRoom().getRoomName().equals(hotel.getRooms().get(i).getRoomName())) {
//                    isEmpty = false;    //checks if room has no reservation
//                }
//            }
//            if (isEmpty) {
//                System.out.println(hotel.getRooms().get(i).getRoomName() + " removed\n"); //confirmation message
//                hotel.getRooms().remove(i);     //room is removed if empty
//                nRooms--;
//            }
//
//            if (i == 1 && nRooms > 0)
//                System.out.println("Cannot remove more rooms.\n"); //if no more rooms can be removed
//
//        }
    }

    /**
     * Updates price of rooms in a hotel
     * @param hotel hotel to update room prices
     * @param newPrice newly set price to the rooms
     */
    public void updatePrice(Hotel hotel, double newPrice){
        boolean validPrice = newPrice >= 100;

        if (hotel.getReservations().isEmpty() && validPrice){ //checks if hotel has no reservations
            for(Room room : hotel.getRooms()) { //adds price for all rooms in the hotel
                room.setPrice(newPrice); //adds price to room
            }
            System.out.println("Price updated\nPrice: P " + hotel.getRooms().get(0).getPrice() + "\n"); //confirmation message
        }
        else {
            if(!validPrice)
                System.out.println("Invalid price\n");
            else
                System.out.println("A room is reserved. Price change is unavailable.\n"); //if hotel has a reservation
        }
    }

    public void changeDateModifier(Hotel hotel, int startDate, int endDate, double modifier){
        boolean valid = endDate > startDate;

        if(modifier >= 0.5 && modifier <= 1.50 && valid) {
            for (int i = startDate - 1; i < endDate - 1; i++) {
                hotel.setDateMultiplier(i, modifier);
            }
        }
        else
            System.out.println("Invalid input\n");
    }


    /**
     * Removes a reservation in a hotel
     * @param hotel hotel which will have a reservation removed
     * @param guestName name of guest under a reservation
     */
    public void removeReservation(Hotel hotel, String guestName){
        int checkInDate = 0;
        int checkOutDate = 0;
        int i;
        int roomIndex = 0;
        Reservation reservationToRemove = null; //temporary reservation

            for(Reservation reservation : hotel.getReservations()) { //gets reservation information from guestName
                if (reservation.getGuestName().equalsIgnoreCase(guestName)) {
                    checkInDate = reservation.getCheckInDate();
                    checkOutDate = reservation.getCheckOutDate();
                    roomIndex = searchRoom(hotel, reservation.getRoom().getRoomName());
                    reservationToRemove = reservation;
                }
            }

            if(reservationToRemove != null) { //if reservation is found
                hotel.getReservations().remove(reservationToRemove); //removes reservation
                System.out.println(reservationToRemove.getGuestName() + " removed\n"); //confirmation message
                for (i = checkInDate; i < checkOutDate; i++) //frees up occupancy on dates of the room assigned to the deleted reservation
                    hotel.getRooms().get(roomIndex).getDates()[i] = false;
            }
            else
                System.out.println("Guest: " + guestName + " not found."); //error message if no reservation was found from guestName

    }

    /**
     * Removes a hotel
     * @param hotel hotel to be removed
     */
    public void removeHotel(Hotel hotel) {
        if (hotel.getReservations().isEmpty()) { //checks if no active reservations are in the hotel
            System.out.println("Hotel " + hotel.getHotelName() + " removed\n"); //confirmation message
            hotels.remove(hotel); //hotel is removed from list
        }
        else
            System.out.println("Hotel cannot be removed. There are reservations.\n"); //error message if a reservation is present
    }

    /**
     * Simulates booking
     * @param hotel hotel wherein a reservation will be made
     * @param guestName name of guest under the reservation
     * @param checkInDate date of checkIn
     * @param checkOutDate date of checkOut
     */
    public void Book(Hotel hotel, String guestName, int checkInDate, int checkOutDate, int roomType){
        int roomIndex = findVacantRoom(hotel, checkInDate, checkOutDate, roomType);
        double guestPrice = 0;
        if(roomIndex == -1){ //checks in a vacant room is found
            System.out.println("Unavailable Room\n"); //error message if not vacant rooms are available
        }
        else {
            for(int i = checkInDate; i < checkOutDate; i++){
                guestPrice += hotel.getDateMultiplier()[i - 1] * hotel.getRooms().get(roomIndex).getPrice();

            }

            Reservation reservation = new Reservation(guestName, checkInDate, checkOutDate, hotel.getRooms().get(roomIndex), guestPrice); //instantiates a reservation with given infro
            hotel.addReservation(reservation);  //reservation is added to the hotel
            updateDates(hotel.getRooms().get(roomIndex), checkInDate, checkOutDate); //occupancy dates of room will be updated
            System.out.println("Confirmed. Your Room is: " + hotel.getRooms().get(roomIndex).getRoomName() + "\n"); //confirmation message
        }
    }

    public ArrayList<String> breakdownList(Hotel hotel, int roomIndex, int checkInDate, int checkOutDate){
        String roomItem;
        ArrayList<String> roomBreakList = new ArrayList<>();
        int percentage;
        double roomPrice = hotel.getRooms().get(roomIndex).getPrice();
        double totalPrice;


        for(int i = checkInDate; i < checkOutDate; i++){
            percentage = (int) (hotel.getDateMultiplier()[i - 1] * 100);
            totalPrice = roomPrice * hotel.getDateMultiplier()[i - 1];
            roomItem = String.format("June %d - June %d: P%.2f * %d%% --> P%.2f", i, (i + 1), roomPrice, percentage, totalPrice);
            roomBreakList.add(roomItem);
        }
        totalPrice = getSubtotal(hotel, checkInDate, checkOutDate, roomIndex);
        roomBreakList.add("Subtotal: " + totalPrice);
        roomBreakList.add("Total: " + totalPrice);

        return roomBreakList;
    }

    public ArrayList<String> breakdownList(Hotel hotel, int roomIndex, int checkInDate, int checkOutDate, String discCode){
        String roomItem;
        String discountMessage;
        ArrayList<String> roomBreakList = new ArrayList<>();
        int percentage;
        double roomPrice = hotel.getRooms().get(roomIndex).getPrice();
        double itemPrice, subTotal, totalPrice;
        double lessDiscount = 0;

        subTotal = getSubtotal(hotel, checkInDate, checkOutDate, roomIndex);

        if(discCode.equals("I_WORK_HERE")){
            lessDiscount = iWorkHere(subTotal);
            discountMessage = String.format("Discount: I_WORK_HERE (less 10%%)  - P%.2f", lessDiscount);
        }
        else if(discCode.equals("STAY4_GET1")){
            lessDiscount = stay4Get1(roomPrice * hotel.getDateMultiplier()[checkInDate- 1], checkInDate, checkOutDate);
            if(lessDiscount == 0)
                discountMessage = "Cannot Apply Discount Code";
            else
                discountMessage = String.format("Discount: STAY4_GET1 (first night free)  - P%.2f", lessDiscount);
        }
        else if(discCode.equals("PAYDAY")){
            lessDiscount = payday(subTotal, checkInDate, checkOutDate);
            if(lessDiscount == 0)
                discountMessage = "Cannot Apply Discount Code";
            else
                discountMessage = String.format("Discount: PAYDAY (less 7%% on 15/30)  - P%.2f", lessDiscount);
        }
        else
            discountMessage = "Discount code not found";

        totalPrice = subTotal - lessDiscount;


        for(int i = checkInDate; i < checkOutDate; i++){
            percentage = (int) (hotel.getDateMultiplier()[i - 1] * 100);
            itemPrice = roomPrice * hotel.getDateMultiplier()[i - 1];
            roomItem = String.format("June %d - June %d: P%.2f * %d%% --> P%.2f", i, (i + 1), roomPrice, percentage, itemPrice);
            roomBreakList.add(roomItem);
        }
        roomBreakList.add("Subtotal:                                P" + subTotal);
        roomBreakList.add(discountMessage);
        roomBreakList.add("Total:                                   P" + totalPrice);

        return roomBreakList;
    }


    public double getSubtotal(Hotel hotel, int checkInDate, int checkOutDate, int roomIndex){
        double subtotal = 0;

        for(int i = checkInDate; i < checkOutDate; i++) {
            subtotal += hotel.getDateMultiplier()[i - 1] * hotel.getRooms().get(roomIndex).getPrice();
        }
        return subtotal;
    }

    public double getTotalPrice(Hotel hotel, int roomIndex, int checkInDate, int checkOutDate, String discCode){
        double subtotal, total;
        double lessDisc = 0;
        double roomPrice = hotel.getRooms().get(roomIndex).getPrice();

        subtotal = getSubtotal(hotel, checkInDate, checkOutDate, roomIndex);
        if(discCode.equals("I_WORK_HERE"))
            lessDisc = iWorkHere(subtotal);
        if(discCode.equals("STAY4_GET1"))
            lessDisc = stay4Get1(roomPrice * hotel.getDateMultiplier()[checkInDate- 1], checkInDate, checkOutDate);
        if(discCode.equals("PAYDAY"))
            lessDisc = payday(subtotal, checkInDate, checkOutDate);

        total = subtotal - lessDisc;

        return total;
    }

    public void book(Hotel hotel, String guestName, int roomIndex, int checkInDate, int checkOutDate, String discCode){
        double totalPrice = getTotalPrice(hotel, roomIndex, checkInDate, checkOutDate, discCode);
        Reservation newBooking = new Reservation(guestName, checkInDate, checkOutDate, hotel.getRooms().get(roomIndex), totalPrice);
        hotel.addReservation(newBooking);
    }



    /**
     * Getter method for hotels ArrayList
     * @return hotels
     */
    public ArrayList<Hotel> getHotels() {
        return hotels;
    }

    public double iWorkHere(double price) {
        return price * 0.10;
    }

    public double stay4Get1(double price, int checkInDate, int checkOutDate) {
        if(checkOutDate - checkInDate >= 5)
            return price;
        return 0;
    }

    public double payday(double price, int checkInDate, int checkOutDate) {
        if(inRange(15, checkInDate, checkOutDate - 1))
            return price * 0.07;
        if(inRange(30, checkInDate, checkOutDate - 1))
            return price * 0.07;
        return 0;
    }
}
