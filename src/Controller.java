import java.util.Scanner;

/**
 * Represents a Controller for the system
 * @author Cumti
 * @author Escano
 */
public class Controller {
    /**
     * represents the System which will be controlled
     */
    private HotelSystem hotelSystem;
    /**
     * represents the viewing class of the program
     */
    private Viewer viewer = new Viewer();

    /**
     * Creates a Controller Object given a hotelSystem
     * @param hotelSystem Model/system class. Stores hotels database
     */
    public Controller(HotelSystem hotelSystem) {
        this.hotelSystem = hotelSystem;
    }

    /**
     * Asks the user for information to add a new hotel to the system
     */
    public void createHotel(){
        Scanner scanner = new Scanner(System.in);
        String hotelName;
        System.out.println("Enter hotel name: ");
        hotelName = scanner.nextLine();

        hotelSystem.addHotel(hotelName);
    }

    /**
     * Finds and displays booked and available rooms on a specifies date
     * @param hotel hotel which will be checked
     */
    public void roomsOnDate(Hotel hotel){
        Scanner scanner = new Scanner(System.in);
        int date, bookedRooms, availableRooms;

        System.out.print("Enter Date: July: ");
        date = scanner.nextInt();

        bookedRooms = hotelSystem.bookedRooms(hotel, date);
        availableRooms = hotel.getRooms().size() - bookedRooms;

        viewer.viewRoomsOnDate(date, bookedRooms, availableRooms); //calls method viewer object to display items
    }

    /**
     * Finds and displays specific room information and availability throughout the month
     * @param hotel hotel which will be checked
     */
    public void roomInfo(Hotel hotel){
        Scanner scanner = new Scanner(System.in);
        int roomOpt;
        int i;
        Room room;

        System.out.println("Rooms in: " + hotel.getHotelName());
        for(i = 0; i < hotel.getRooms().size(); i++)
            System.out.printf("(%d) - %s\n", i + 1, hotel.getRooms().get(i).getRoomName());
        System.out.println("Select a room: ");
        roomOpt = scanner.nextInt(); //asks for index based in the displayed prompt
        if(roomOpt < 0 || roomOpt > hotel.getRooms().size())
            System.out.println("Invalid option");
        else {
            room = hotel.getRooms().get(roomOpt - 1); //finds room to display
            viewer.viewRoomInfo(room); //displays information from viewer object
        }
    }

    /**
     * Searches and displays specific reservation information
     * @param hotel hotel which will be checked
     */
    public void reservationInfo(Hotel hotel){
        Scanner scanner = new Scanner(System.in);
        String guestName;
        Reservation reservationInfo = null;

        System.out.println("Enter Guest Name: ");
        guestName = scanner.nextLine(); //asks for the guestName under the reservation
        for(Reservation reservation: hotel.getReservations()) //searches for the entered name
            if(reservation.getGuestName().equalsIgnoreCase(guestName))
                reservationInfo = reservation;  //assigns found reservation to temp attribute

        if(reservationInfo == null)
            System.out.println("No reservation found\n"); //error message if no reservation was found
        else {
            System.out.println("Hotel Name: " + hotel.getHotelName());
            viewer.viewReservationInfo(reservationInfo); //displays the found reservation
        }
    }

    /**
     * Method to View Hotel Information
     */
    public void viewHotel(){
        Scanner scanner = new Scanner(System.in);
        String hotelName;
        int index, choice;
        Hotel hotel;

        viewer.displayHotels(hotelSystem.getHotels()); //displays list of current Hotels in the system
        System.out.println("Enter hotel name: "); //asks for hotel name
        hotelName = scanner.nextLine();
        index = hotelSystem.searchHotel(hotelName); //searches and returns index of hotel

        if(index == -1)
            System.out.println("Hotel not found\n"); //error message if no hotel was found
        else {
            hotel = hotelSystem.getHotels().get(index);
            viewer.viewHotelBasic(hotel); //displays basic hotel information

            //option to view more specific details in the hotel
            System.out.println("Further information: ");
            System.out.println("(1) - Available and Booked Rooms on a given date");
            System.out.println("(2) - Specified Room Info");
            System.out.println("(3) - Specified Reservation Info");
            System.out.println("(4) - Exit");
            System.out.println("Select option: ");
            choice = scanner.nextInt(); //asks for option
            switch (choice) { //switch to call function based on entered choice
                case 1:
                    roomsOnDate(hotel);
                    break;
                case 2:
                    roomInfo(hotel);
                    break;
                case 3:
                    reservationInfo(hotel);
                    break;
                case 4:
                    System.out.println("Returning to Main Menu...\n"); //if user does not need to see further info
                    break;
                default:
                    System.out.println("Invalid choice\n"); //error message for invalid input
            }
        }
    }

    /**
     * This asks the user which hotel do they want to change name
     * @param hotel hotel to be changed name
     */
    public void changeHotelName(Hotel hotel){
        Scanner scanner = new Scanner(System.in);
        String newHotelName;

        System.out.println("Enter new hotel name: ");
        newHotelName = scanner.nextLine(); //asks the user for the new hotel name

        hotelSystem.changeHotelName(hotel, newHotelName); //method that changes name of hotel
    }

    /**
     * Asks user how many rooms do they want to add to a specified hotel
     * @param hotel which hotel do they want to add rooms
     */
    public void addRoom(Hotel hotel){
        Scanner scanner = new Scanner(System.in);
        int rooms, roomType;

        System.out.println("Select Room type:");
        System.out.println("[1] - Standard");
        System.out.println("[2] - Deluxe");
        System.out.println("[3] - Executive");
        roomType = scanner.nextInt();


        System.out.println("Enter how many rooms to add: ");
        rooms = scanner.nextInt(); //asks user the number of rooms to add

        hotelSystem.addRoom(hotel, rooms, roomType); //method to successfully add rooms to hotel
    }

    /**
     * Asks user how many rooms do they want to remove to a specified hotel
     * @param hotel which hotel do they want to remove rooms
     */
    public void removeRoom(Hotel hotel){
        Scanner scanner = new Scanner(System.in);
        int rooms;

        System.out.println("Enter how many rooms to remove: ");
        rooms = scanner.nextInt(); //asks user the number of rooms to remove

        if(rooms <= 0)
            System.out.println("Invalid input\n"); //error message for invalid input
        else
            hotelSystem.removeRoom(hotel, rooms); //inputs info entered by user to method
    }

    /**
     * Asks user how much to add to a hotel's room base price
     * @param hotel hotel which will modify their rooms' prices
     */
    public void updateBasePrice(Hotel hotel){
        Scanner scanner = new Scanner(System.in);
        double addedPrice;

        System.out.println("Enter added price: ");
        addedPrice = scanner.nextDouble(); //asks user to input added price

        if(addedPrice < 100)
            System.out.println("Added price must be at least 100\n"); //error message for invalid input
        else
            hotelSystem.updatePrice(hotel, addedPrice); //inputs info entered by user to method
    }

    public void modifyMultiplier(Hotel hotel){
        Scanner scanner = new Scanner(System.in);
        double multiplier;
        int startDate, endDate;

        System.out.println("Enter start date: ");
        startDate = scanner.nextInt();
        System.out.println("Enter end date: ");
        endDate = scanner.nextInt();
        System.out.println("Enter modifier percentage: ");
        multiplier = scanner.nextDouble();

        hotelSystem.changeDateModifier(hotel, startDate, endDate, multiplier);

    }


    /**
     * Asks user to remove a certain reservation to a specified hotel
     * @param hotel hotel which contains the reservation
     */
    public void removeReservation(Hotel hotel){
        Scanner scanner = new Scanner(System.in);
        String guestName;

        System.out.println("Enter Reservation Guest Name: ");
        guestName = scanner.nextLine(); //asks user for guestName under the reservation

        hotelSystem.removeReservation(hotel, guestName); //inputs info entered by user to method
    }

    /**
     * Asks the user to remove a certain hotel from the system
     * @param hotel hotel which will be removed
     */
    public void removeHotel(Hotel hotel){
        hotelSystem.removeHotel(hotel);
    }

    /**
     * Method which displays and asks user for managing options for a specific hotel
     */
    public void manageHotel(){
        Scanner scanner = new Scanner(System.in);
        int option, index;
        String hotelName;
        Hotel hotel;

        viewer.displayHotels(hotelSystem.getHotels()); //displays current list of hotels in system
        System.out.println("Enter hotel name: ");
        hotelName = scanner.nextLine(); //asks user to input the hotel name
        index = hotelSystem.searchHotel(hotelName); //searches for the hotel's index in the system

        if(index == -1)
            System.out.println("Hotel not found\n"); //error message if no hotel was found
        else {
            hotel = hotelSystem.getHotels().get(index); //selects the hotel to manage
            viewer.manageHotelPrompt(); //displays managing options
            option = scanner.nextInt(); //asks user to enter option
            switch (option) {           //switch case depending on user's choice
                case 1:
                    changeHotelName(hotel);
                    break;
                case 2:
                    addRoom(hotel);
                    break;
                case 3:
                    removeRoom(hotel);
                    break;
                case 4:
                    updateBasePrice(hotel);
                    break;
                case 5:
                    modifyMultiplier(hotel);
                    break;
                case 6:
                    removeReservation(hotel);
                    break;
                case 7:
                    removeHotel(hotel);
                    break;
                default:
                    System.out.println("Invalid option\n"); //error message for invalid input
            }
        }
    }

    /**
     * Method to simulate booking. Interacts with user to book a reservation
     */
    public void booking(){
        Scanner scanner = new Scanner(System.in);
        String hotelName, guestName;
        int index;
        int roomType;
        int checkinDate;
        int checkoutDate = 0;
        boolean valid = true;
        Hotel hotel;

        viewer.displayHotels(hotelSystem.getHotels()); //displays list of current hotels
        System.out.println("Enter hotel name: ");
        hotelName = scanner.nextLine(); //asks for hotel name based on list
        index = hotelSystem.searchHotel(hotelName); //finds the index of hotel
        if(index == -1)
            System.out.println("Hotel not found\n");
        else {
            hotel = hotelSystem.getHotels().get(index); //assigns index to a hotel attribute

            //asks user for booking information
            System.out.println("Enter Guest Name: ");
            guestName = scanner.nextLine(); //asks for the guestName
            System.out.println("Enter Room Type");
            System.out.println("[1] - Standard");
            System.out.println("[2] - Deluxe");
            System.out.println("[3] - Executive");
            roomType = scanner.nextInt();
            System.out.println("Available Dates");

            System.out.println("Enter Check-in date: ");
            checkinDate = scanner.nextInt(); //asks for date of checkIn
            if(checkinDate < 0 || checkinDate > 30)
                valid = false;

            if (valid) { //if checkinDate is valid
                System.out.println("Enter Check-out date: ");
                checkoutDate = scanner.nextInt(); //asks user for checkOut date
                if (checkoutDate <= 1 || checkoutDate > 31 || checkinDate >= checkoutDate) { //validates input
                    valid = false;
                    System.out.println("Invalid date\n"); //error message if invalid
                }
            }

            if (valid) { //if checkoutDate is valid
                viewer.confirmDetails(hotel, guestName, checkinDate, checkoutDate); //displays booking information
                if (scanner.next().equalsIgnoreCase("y")) //asks user to confirm if booking will push through
                    hotelSystem.Book(hotelSystem.getHotels().get(index), guestName, checkinDate, checkoutDate, roomType); //punches reservation to the system
                else
                    System.out.println("Booking Cancelled.\n"); //if booking is cancelled
            }
        }
    }

    /**
     * Main menu. Displays and ask user for options in the system
     */
    public void menu(){
        Scanner scanner = new Scanner(System.in);
        int option;
        boolean exit = false;
        while(!exit) { //while system does not shut down
            viewer.displayMenu(); //displays main menu options
            option = scanner.nextInt(); //asks user to choose option

            switch (option) { //switch case based on user's option
                case 1:
                    createHotel();
                    break;
                case 2:
                    viewHotel();
                    break;
                case 3:
                    manageHotel();
                    break;
                case 4:
                    booking();
                    break;
                case 5:
                    System.out.println("Shutting Down.......\n\n"); //will exit the loop and program
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again."); //if invalid input
                    break;
            }
        }
    }
}
