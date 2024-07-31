import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Represents the Controller Class
 * @author Cumti
 * @author Escano
 * **/
public class Controller implements ActionListener, DocumentListener, ItemListener {
    private HotelSystem hotelSystem;
    private MainGUI mainGUI;
    private int index;
    private int bookedRoomIndex;
    private int bookedCheckInDate;
    private int bookedCheckOutDate;
    private ArrayList<String> breakdownList = new ArrayList<>();

    public Controller(MainGUI mainGUI, HotelSystem hotelSystem) {
        this.mainGUI = mainGUI;
        this.hotelSystem = hotelSystem;
        mainGUI.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Back":
                mainGUI.getMainCardLayout().show(mainGUI.getMainCardPanel(), "main");
                break;

            case "Create Hotel":
                mainGUI.getMainCardLayout().show(mainGUI.getMainCardPanel(), "create");
                break;

            case "View Hotel":
                mainGUI.getMainCardLayout().show(mainGUI.getMainCardPanel(), "searchHotel");
                mainGUI.setCurrView(2);
                break;

            case "Manage Hotel":
                mainGUI.getMainCardLayout().show(mainGUI.getMainCardPanel(), "searchHotel");
                mainGUI.setCurrView(3);
                break;

            case "Book Reservation":
                mainGUI.getMainCardLayout().show(mainGUI.getMainCardPanel(), "bookReservation");
                mainGUI.setCurrView(4);
                break;

            case "Add Hotel":
                addHotel();
                break;

            case "Search Hotel":
                if (mainGUI.getCurrView() == 2) {
                    hiLevel();
                } else if (mainGUI.getCurrView() == 3) {
                    mainGUI.getMainCardLayout().show(mainGUI.getMainCardPanel(), "manageHotel");
                    mainGUI.getManageHotelPanel().getManageCardLayout().show(mainGUI.getManageHotelPanel().getManagePanel(), "manageOptionPanel");
                }
                break;

            case "View Available Rooms on Date":
                mainGUI.getViewHotelPanel().getViewHotelCardLayout().show(mainGUI.getViewHotelPanel().getViewPanel(), "roomsOnDate");
                break;

            case "Select Date":
                viewRoomsOnDate();
                break;

            case "View Room Info":
                mainGUI.getViewHotelPanel().getViewHotelCardLayout().show(mainGUI.getViewHotelPanel().getViewPanel(), "roomInfo");
                break;

            case "Select Room":
                viewRoomInfo();
                break;

            case "View Reservation Info":
                mainGUI.getViewHotelPanel().getViewHotelCardLayout().show(mainGUI.getViewHotelPanel().getViewPanel(), "reservation");
                break;

            case "Search Reservation":
                if (mainGUI.getCurrView() == 2) {
                    viewReservationInfo();
                } else {
                    showBasicReservation();
                }
                break;

            case "Confirm Remove Reservation":
                removeReservation();
                break;

            case "Change Name":
                mainGUI.getManageHotelPanel().getManageCardLayout().show(mainGUI.getManageHotelPanel().getManagePanel(), "changeNamePanel");
                break;

            case "Confirm Name Change":
                changeName();
                break;

            case "Add Room":
                updateCbRmToAdd();
                mainGUI.getManageHotelPanel().getManageCardLayout().show(mainGUI.getManageHotelPanel().getManagePanel(), "addRoomsPanel");
                break;

            case "Confirm Add Room":
                addRoom();
                break;

            case "Remove Room":
                mainGUI.getManageHotelPanel().getManageCardLayout().show(mainGUI.getManageHotelPanel().getManagePanel(), "removeRoomsPanel");
                break;

            case "Select Room Type":
                updateCbRmToRemove();
                break;

            case "Confirm Remove Room":
                removeRoom();
                break;

            case "Update Base Price":
                mainGUI.getManageHotelPanel().getManageCardLayout().show(mainGUI.getManageHotelPanel().getManagePanel(), "updatePricePanel");
                break;

            case "Confirm Base Price":
                updateBasePrice();
                break;

            case "Update Price Modifier":
                mainGUI.getManageHotelPanel().getManageCardLayout().show(mainGUI.getManageHotelPanel().getManagePanel(), "updatePriceModPanel");
                break;

            case "Remove Reservation":
                mainGUI.getManageHotelPanel().getManageCardLayout().show(mainGUI.getManageHotelPanel().getManagePanel(), "removeReservationPanel");
                break;

            case "Remove Hotel":
                mainGUI.getManageHotelPanel().getManageCardLayout().show(mainGUI.getManageHotelPanel().getManagePanel(), "removeHotelPanel");
                break;

            case "Confirm Remove Hotel":
                removeHotel();
                break;

            case "Confirm Price Modifier":
                updatePriceMod();
                break;

            case "Next":
                if(validateBook())
                    updateConfirmInfo(bookedRoomIndex);
                break;

            case "Search for Rooms":
                searchAvailRooms();
                break;

            case "Confirm Booking":
                confirmBook();
                break;

            case "Cancel Booking":
                cancelBook();

            case "Return to Main":
                mainGUI.getBookReservationPanel().getBookReservationCardLayout().show(mainGUI.getBookReservationPanel().getBookReservationCard(), "promptPanel");
                mainGUI.getMainCardLayout().show(mainGUI.getMainCardPanel(), "main");
                break;

            case "Shutdown":
                System.out.println("Program Shutting down");
                System.exit(0);
                break;

            default:
                System.err.println("Unknown command: " + command);
                break;
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    public void addHotel(){
        if(mainGUI.getHotelName().equals(""))
            mainGUI.setErrorMessage("Please enter hotel name");
        else {
            if (!hotelSystem.addHotel(mainGUI.getHotelName())) {
                mainGUI.setErrorMessage("Hotel already exists");
            } else {
                mainGUI.setConfirmMessage(mainGUI.getHotelName() + " has been added");
                updateCbHotels();
                updateCbRooms();
            }
        }
    }

    public void hiLevel(){
        index = hotelSystem.searchHotel(mainGUI.getCbHotelNames().getSelectedItem().toString());
        Hotel hotel = hotelSystem.getHotels().get(index);

        mainGUI.getViewHotelPanel().updateViewHiLevel(hotel.getHotelName(), hotel.getRooms().size(), hotel.getTotalIncome());

        mainGUI.getMainCardLayout().show(mainGUI.getMainCardPanel(), "viewHotel");
        mainGUI.getViewHotelPanel().getViewHotelCardLayout().show(mainGUI.getViewHotelPanel().getViewPanel(), "hiLevel");
    }

    public void viewRoomsOnDate(){
        int bookedRooms, roomCount;
        int date;
        Hotel hotel;
        String line1, line2, line3;

        date = (int) mainGUI.getViewHotelPanel().getCbDates().getSelectedItem();
        hotel = hotelSystem.getHotels().get(index);
        roomCount = hotelSystem.getHotels().get(index).getRooms().size();
        bookedRooms = hotelSystem.bookedRooms(hotel, date);

        line1 = "Date: June " + date + ", 2024";
        line2 = "Booked Rooms: " + bookedRooms;
        line3 = "Available Rooms: " + (roomCount - bookedRooms);
        mainGUI.setConfirmMessage("Hotel Name: "+ hotel.getHotelName() + "\n" + line1 + "\n" + line2 + "\n" + line3);
    }

    public void viewRoomInfo(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String roomName = mainGUI.getViewHotelPanel().getCbRoomNames().getSelectedItem().toString();
        String roomType = "";
        double roomPrice;
        int roomIndex = hotelSystem.searchRoom(hotel, roomName);
        Room room = hotel.getRooms().get(roomIndex);
        ArrayList<String> dates = hotelSystem.getRoomDates(hotel, roomIndex);

        if(room instanceof Standard)
            roomType = "Standard";
        if(room instanceof Deluxe)
            roomType = "Deluxe";
        if(room instanceof Executive)
            roomType = "Executive";
        roomPrice = room.getPrice();

        mainGUI.getViewHotelPanel().updateRoomInfo(roomName, roomType, roomPrice, dates);
    }

    public void viewReservationInfo(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String guestName = mainGUI.getViewHotelPanel().getTfGuestName();
        ArrayList<String> reserveInfo = new ArrayList<>();

         reserveInfo = hotelSystem.reservationInfo(hotel, guestName);
         if(reserveInfo == null){
             System.out.println("no reservation info");
             mainGUI.setErrorMessage("No reservation found");
         }
         else{
             mainGUI.getViewHotelPanel().updateInfoPanel(reserveInfo);
         }

    }

    public void changeName(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String newName = mainGUI.getManageHotelPanel().getTfNewName();

        if(newName.isEmpty())
            mainGUI.setErrorMessage("Please enter a name");
        else if(!hotelSystem.changeHotelName(hotel, newName))
            mainGUI.setErrorMessage("Hotel name already exists");
        else{
            mainGUI.setConfirmMessage("Hotel name updated");
            updateCbHotels();
        }

    }

    public void addRoom(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String roomType = mainGUI.getManageHotelPanel().getCbRoomTypeAdd().getSelectedItem().toString();
        int roomTypeChoice;

        int roomCount = (int) mainGUI.getManageHotelPanel().getCbRmToAdd().getSelectedItem();

        if(roomType.equals("Standard"))
            roomTypeChoice = 1;
        else if(roomType.equals("Deluxe"))
            roomTypeChoice = 2;
        else
            roomTypeChoice = 3;

        if(!hotelSystem.addRoom(hotel, roomCount, roomTypeChoice))
            mainGUI.setErrorMessage("Cannot Add More");
        else {
            updateCbRmToAdd();
            updateCbRooms();
            mainGUI.setConfirmMessage(roomCount + " " + roomType + " Rooms Added");
        }
    }

    public void removeRoom(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        int roomCount = 0;
        String roomType = mainGUI.getManageHotelPanel().getCbRoomTypeRemove().getSelectedItem().toString();
        boolean valid = true;

        try{
            roomCount = (int) mainGUI.getManageHotelPanel().getCbRmToRemove().getSelectedItem();
        } catch (NullPointerException e){
            mainGUI.setErrorMessage("No Rooms can be removed right now!");
            valid = false;
        }
        if(valid) {
            if (roomType.equals("Standard"))
                hotelSystem.removeRoom(hotel, roomCount, 1);
            else if (roomType.equals("Deluxe"))
                hotelSystem.removeRoom(hotel, roomCount, 2);
            else
                hotelSystem.removeRoom(hotel, roomCount, 3);
            updateCbRmToRemove();
        }
    }

    public void updateBasePrice(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        double newPrice = 0;
        boolean valid = true;
        try{
           newPrice = Double.parseDouble(mainGUI.getManageHotelPanel().getTfNewPrice());
        }catch (NumberFormatException e){
            mainGUI.setErrorMessage("Please enter a valid price");
            valid = false;
        }
        if(valid) {
            if (!hotelSystem.updatePrice(hotel, newPrice))
                mainGUI.setErrorMessage("Cannot Update Price. Either a room is reserved or price is less than P100");
            else
                mainGUI.setConfirmMessage("Price updated to " + newPrice);
        }
    }

    public void updatePriceMod(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        int startDate = (int) mainGUI.getManageHotelPanel().getCbStartDateMod().getSelectedItem();
        int endDate = (int) mainGUI.getManageHotelPanel().getCbEndDateMod().getSelectedItem();
        double modifier = 0;
        boolean valid = true;

        try{
            modifier = Double.parseDouble(mainGUI.getManageHotelPanel().getTfNewPriceMod());
        } catch (NumberFormatException e){
            mainGUI.setErrorMessage("Please enter a valid price");
            valid = false;
        }

        if(valid) {
            if (!hotelSystem.changeDateModifier(hotel, startDate, endDate, modifier))
                mainGUI.setErrorMessage("Invalid input or Hotel has reservations");
            else
                mainGUI.setConfirmMessage("Price modifier updated to " + modifier + " from June " + startDate + " to June " + endDate);
        }

    }
    public void showBasicReservation(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        ArrayList<String> reservationInfo = new ArrayList<>();
        String guestName = mainGUI.getManageHotelPanel().getTfGuestName();

        if(hotelSystem.searchReservation(hotel, guestName, reservationInfo))
            mainGUI.getManageHotelPanel().updateRsrvPanel(reservationInfo);
        else
            mainGUI.getManageHotelPanel().updateRsrvPanel();
    }

    public void removeReservation(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String guestName = mainGUI.getManageHotelPanel().getTfGuestName();
        if(!hotelSystem.removeReservation(hotel, guestName))
            mainGUI.setErrorMessage("Cannot Remove this reservation");
        else
            mainGUI.setConfirmMessage("Reservation by " + guestName + " Removed");
    }

    public void removeHotel(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        hotelSystem.removeHotel(hotel);
        mainGUI.getManageHotelPanel().getManageCardLayout().show(mainGUI.getManageHotelPanel().getManagePanel(), "hotelRemovedPanel");
        updateCbHotels();
    }



    public void updateCbHotels(){
        mainGUI.getCbHotelNames().removeAllItems();
        mainGUI.getBookReservationPanel().getCbHotels().removeAllItems();
        for(Hotel hotel : hotelSystem.getHotels()){
            mainGUI.getCbHotelNames().addItem(hotel.getHotelName());
            mainGUI.getBookReservationPanel().getCbHotels().addItem(hotel.getHotelName());
        }
    }

    public void updateCbRooms(){
        ArrayList<String> roomList = new ArrayList<>();
        Hotel hotel = hotelSystem.getHotels().get(index);
        for(Room room: hotel.getRooms()){
            roomList.add(room.getRoomName());
        }
        Collator collator = Collator.getInstance(Locale.US);
        collator.setStrength(Collator.PRIMARY);
        Collections.sort(roomList, collator::compare);

        mainGUI.getViewHotelPanel().getCbRoomNames().removeAllItems();
        for(String roomName : roomList){
            mainGUI.getViewHotelPanel().getCbRoomNames().addItem(roomName);
        }
    }


    public void updateCbRmToAdd(){
        mainGUI.getManageHotelPanel().getCbRmToAdd().removeAllItems();
        Hotel hotel = hotelSystem.getHotels().get(index);
        for(int i = 1; i <= 50 - hotel.getRooms().size(); i++){
            mainGUI.getManageHotelPanel().getCbRmToAdd().addItem(i);
        }
    }

    public void updateCbRmToRemove(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String roomType = mainGUI.getManageHotelPanel().getCbRoomTypeRemove().getSelectedItem().toString();
        int roomCount = 0;
        boolean removable = hotel.getRooms().size() > 1;
        mainGUI.getManageHotelPanel().getCbRmToRemove().removeAllItems();


        for(int i = 0; i < hotel.getRooms().size() && removable; i++){
            if(roomType.equals("Standard")){
                if(hotel.getRooms().get(i) instanceof Standard) {
                    roomCount++;
                    mainGUI.getManageHotelPanel().getCbRmToRemove().addItem(roomCount);
                }
            }
            if(roomType.equals("Deluxe")){
                if(hotel.getRooms().get(i) instanceof Deluxe) {
                    roomCount++;
                    mainGUI.getManageHotelPanel().getCbRmToRemove().addItem(roomCount);
                }
            }
            if(roomType.equals("Executive")){
                if(hotel.getRooms().get(i) instanceof Executive){
                    roomCount++;
                    mainGUI.getManageHotelPanel().getCbRmToRemove().addItem(roomCount);
                }
            }
        }
        if(roomCount == hotel.getRooms().size())
            mainGUI.getManageHotelPanel().getCbRmToRemove().removeItemAt(roomCount - 1);
        mainGUI.getManageHotelPanel().getManageCardLayout().show(mainGUI.getManageHotelPanel().getManagePanel(), "removeRoom");
    }

    public void searchAvailRooms(){
        Hotel hotel = null;
        String roomType = mainGUI.getBookReservationPanel().getCbRoomTypes().getSelectedItem().toString();
        ArrayList<String> datesAvail = new ArrayList<>();
        boolean valid = true;

        try{
            hotel = hotelSystem.getHotels().get(index);
        }catch (IndexOutOfBoundsException e){
            mainGUI.setErrorMessage("No Hotel Selected");
            valid = false;
        }
        if(valid) {
            if (roomType.equals("Standard"))
                datesAvail = hotelSystem.getHotelDates(hotel, 1);
            if (roomType.equals("Deluxe"))
                datesAvail = hotelSystem.getHotelDates(hotel, 2);
            if (roomType.equals("Executive"))
                datesAvail = hotelSystem.getHotelDates(hotel, 3);
            mainGUI.getBookReservationPanel().showDates(datesAvail);
        }
    }

    public boolean validateBook(){
        Hotel hotel;
        int checkInDate = (int) mainGUI.getBookReservationPanel().getCbCheckIn().getSelectedItem();
        int checkOutDate = (int) mainGUI.getBookReservationPanel().getCbCheckOut().getSelectedItem();
        String roomType = mainGUI.getBookReservationPanel().getCbRoomTypes().getSelectedItem().toString();
        int roomTypePar = 0;
        String guestName = mainGUI.getBookReservationPanel().getTfGuestName();
        boolean valid = checkInDate < checkOutDate;

        if(guestName.isEmpty()){
            mainGUI.setErrorMessage("Enter Guest Name!");
            return false;
        }

        try {
            hotel = hotelSystem.getHotels().get(index);
        } catch (IndexOutOfBoundsException e) {
            mainGUI.setErrorMessage("No Hotel Selected");
            return false;
        }

        if(roomType.equals("Standard"))
            roomTypePar = 1;
        if(roomType.equals("Deluxe"))
            roomTypePar = 2;
        if(roomType.equals("Executive"))
            roomTypePar = 3;

        if(valid) {
            bookedRoomIndex = hotelSystem.findVacantRoom(hotel, checkInDate, checkOutDate, roomTypePar);
            if(bookedRoomIndex > -1)
                return true;
        }

        mainGUI.setErrorMessage("Invalid Dates!");
        mainGUI.getBookReservationPanel().setTfGuestName("");
        mainGUI.getBookReservationPanel().setTfDiscountCode("");
        mainGUI.getBookReservationPanel().getCalendarPanel().setVisible(false);
        mainGUI.getBookReservationPanel().getBookReservationCardLayout().show(mainGUI.getBookReservationPanel().getBookReservationCard(), "promptPanel");
        return false;
    }

    public void updateConfirmInfo(int bookedIndex){
        breakdownList.clear();
        Hotel hotel = hotelSystem.getHotels().get(index);
        bookedCheckInDate = (int) mainGUI.getBookReservationPanel().getCbCheckIn().getSelectedItem();
        bookedCheckOutDate = (int) mainGUI.getBookReservationPanel().getCbCheckOut().getSelectedItem();
        String discCode = mainGUI.getBookReservationPanel().getTfDiscountCode();


        if(discCode.isEmpty())
                breakdownList = hotelSystem.breakdownList(hotel, bookedIndex, bookedCheckInDate, bookedCheckOutDate);
        else
                breakdownList = hotelSystem.breakdownList(hotel, bookedIndex, bookedCheckInDate, bookedCheckOutDate, discCode);

        mainGUI.getBookReservationPanel().updateRoomBreakList(breakdownList);
        mainGUI.getBookReservationPanel().getBookReservationCardLayout().show(mainGUI.getBookReservationPanel().getBookReservationCard(), "confirmPanel");
    }

    public void confirmBook(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        bookedCheckInDate = (int) mainGUI.getBookReservationPanel().getCbCheckIn().getSelectedItem();
        bookedCheckOutDate = (int) mainGUI.getBookReservationPanel().getCbCheckOut().getSelectedItem();
        String guestName = mainGUI.getBookReservationPanel().getTfGuestName();
        String discCode = mainGUI.getBookReservationPanel().getTfDiscountCode();

        hotelSystem.book(hotel, guestName, bookedRoomIndex, bookedCheckInDate, bookedCheckOutDate, discCode, breakdownList);
        System.out.println("Booking Added!");
        mainGUI.setConfirmMessage("Booking Added! Your Room No is: " + hotel.getRooms().get(bookedRoomIndex).getRoomName());
        mainGUI.getBookReservationPanel().setTfGuestName("");
        mainGUI.getBookReservationPanel().setTfDiscountCode("");
        mainGUI.getBookReservationPanel().getCalendarPanel().setVisible(false);
        mainGUI.getBookReservationPanel().getBookReservationCardLayout().show(mainGUI.getBookReservationPanel().getBookReservationCard(), "promptPanel");
    }

    public void cancelBook(){
        mainGUI.setConfirmMessage("Booking Cancelled");
        mainGUI.getBookReservationPanel().setTfGuestName("");
        mainGUI.getBookReservationPanel().setTfDiscountCode("");
        mainGUI.getBookReservationPanel().getCalendarPanel().setVisible(false);
        mainGUI.getBookReservationPanel().getBookReservationCardLayout().show(mainGUI.getBookReservationPanel().getBookReservationCard(), "promptPanel");
    }




    @Override
    public void insertUpdate(DocumentEvent e) {

    }

    @Override
    public void removeUpdate(DocumentEvent e) {

    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }
}
