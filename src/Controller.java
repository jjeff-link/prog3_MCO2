import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class Controller implements ActionListener, DocumentListener, ItemListener {
    private HotelSystem hotelSystem;
    private View view;
    private int index;
    private int bookedRoomIndex;
    private int bookedCheckInDate;
    private int bookedCheckOutDate;
    private ArrayList<String> breakdownList = new ArrayList<>();

    public Controller(View view, HotelSystem hotelSystem) {
        this.view = view;
        this.hotelSystem = hotelSystem;
        view.setActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Back":
                view.getMainCardLayout().show(view.getMainCardPanel(), "main");
                break;

            case "Create Hotel":
                view.getMainCardLayout().show(view.getMainCardPanel(), "create");
                break;

            case "View Hotel":
                view.getMainCardLayout().show(view.getMainCardPanel(), "searchHotel");
                view.setCurrView(2);
                break;

            case "Manage Hotel":
                view.getMainCardLayout().show(view.getMainCardPanel(), "searchHotel");
                view.setCurrView(3);
                break;

            case "Book Reservation":
                view.getMainCardLayout().show(view.getMainCardPanel(), "bookReservation");
                view.setCurrView(4);
                break;

            case "Add Hotel":
                if (!hotelSystem.addHotel(view.getHotelName())) {
                    view.setErrorMessage("Hotel already exists");
                } else {
                    view.setConfirmMessage(view.getHotelName() + " has been added");
                    updateCbHotels();
                    updateCbRooms();
                }
                break;

            case "Search Hotel":
                if (view.getCurrView() == 2) {
                    hiLevel();
                } else if (view.getCurrView() == 3) {
                    view.getMainCardLayout().show(view.getMainCardPanel(), "manageHotel");
                    view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "manageOptionPanel");
                }
                break;

            case "View Available Rooms on Date":
                view.getViewHotelPanel().getViewHotelCardLayout().show(view.getViewHotelPanel().getViewPanel(), "roomsOnDate");
                break;

            case "Select Date":
                viewRoomsOnDate();
                break;

            case "View Room Info":
                view.getViewHotelPanel().getViewHotelCardLayout().show(view.getViewHotelPanel().getViewPanel(), "roomInfo");
                break;

            case "Select Room":
                viewRoomInfo();
                break;

            case "View Reservation Info":
                view.getViewHotelPanel().getViewHotelCardLayout().show(view.getViewHotelPanel().getViewPanel(), "reservation");
                break;

            case "Search Reservation":
                if (view.getCurrView() == 2) {
                    viewReservationInfo();
                } else {
                    showBasicReservation();
                }
                break;

            case "Confirm Remove Reservation":
                removeReservation();
                break;

            case "Change Name":
                view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "changeNamePanel");
                break;

            case "Confirm Name Change":
                changeName();
                break;

            case "Add Room":
                updateCbRmToAdd();
                view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "addRoomsPanel");
                break;

            case "Confirm Add Room":
                addRoom();
                break;

            case "Remove Room":
                view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "removeRoomsPanel");
                break;

            case "Select Room Type":
                updateCbRmToRemove();
                break;

            case "Confirm Remove Room":
                removeRoom();
                break;

            case "Update Base Price":
                view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "updatePricePanel");
                break;

            case "Confirm Base Price":
                updateBasePrice();
                break;

            case "Update Price Modifier":
                view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "updatePriceModPanel");
                break;

            case "Remove Reservation":
                view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "removeReservationPanel");
                break;

            case "Remove Hotel":
                view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "removeHotelPanel");
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
                view.getBookReservationPanel().getBookReservationCardLayout().show(view.getBookReservationPanel().getBookReservationCard(), "promptPanel");
                view.getMainCardLayout().show(view.getMainCardPanel(), "main");
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

    public void hiLevel(){
        index = hotelSystem.searchHotel(view.getCbHotelNames().getSelectedItem().toString());
        Hotel hotel = hotelSystem.getHotels().get(index);

        view.getViewHotelPanel().updateViewHiLevel(hotel.getHotelName(), hotel.getRooms().size(), hotel.getTotalIncome());

        view.getMainCardLayout().show(view.getMainCardPanel(), "viewHotel");
        view.getViewHotelPanel().getViewHotelCardLayout().show(view.getViewHotelPanel().getViewPanel(), "hiLevel");
    }

    public void viewRoomsOnDate(){
        int bookedRooms;
        int date;
        Hotel hotel;

        date = (int) view.getViewHotelPanel().getCbDates().getSelectedItem();
        hotel = hotelSystem.getHotels().get(index);
        bookedRooms = hotelSystem.bookedRooms(hotel, date);

        view.getViewHotelPanel().setBookedRooms(bookedRooms);

        view.getViewHotelPanel().updateROD(date, bookedRooms);

        view.getViewHotelPanel().getViewRODInfo().setVisible(true);
    }

    public void viewRoomInfo(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String roomName = view.getViewHotelPanel().getCbRoomNames().getSelectedItem().toString();
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

        view.getViewHotelPanel().updateRoomInfo(roomName, roomType, roomPrice, dates);
    }

    public void viewReservationInfo(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String guestName = view.getViewHotelPanel().getTfGuestName();
        ArrayList<String> reserveInfo = new ArrayList<>();

         reserveInfo = hotelSystem.reservationInfo(hotel, guestName);
         if(reserveInfo == null){
             System.out.println("no reservation info");
             view.getViewHotelPanel().updateInfoPanel();
         }
         else{
             view.getViewHotelPanel().updateInfoPanel(reserveInfo);
         }

    }

    public void changeName(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String newName = view.getManageHotelPanel().getTfNewName();

        if(!hotelSystem.changeHotelName(hotel, newName))
            view.setErrorMessage("Hotel name already exists");
        else{
            view.setConfirmMessage("Hotel name updated");
            updateCbHotels();
        }

    }

    public void addRoom(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String roomType = view.getManageHotelPanel().getCbRoomTypeAdd().getSelectedItem().toString();
        int roomTypeChoice;

        int roomCount = (int) view.getManageHotelPanel().getCbRmToAdd().getSelectedItem();

        if(roomType.equals("Standard"))
            roomTypeChoice = 1;
        else if(roomType.equals("Deluxe"))
            roomTypeChoice = 2;
        else
            roomTypeChoice = 3;

        if(!hotelSystem.addRoom(hotel, roomCount, roomTypeChoice))
            view.setErrorMessage("Cannot Add More");
        else {
            updateCbRmToAdd();
            updateCbRooms();
            view.setConfirmMessage(roomCount + " " + roomType + " Rooms Added");
        }
    }

    public void removeRoom(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        int roomCount = (int) view.getManageHotelPanel().getCbRmToRemove().getSelectedItem();
        String roomType = view.getManageHotelPanel().getCbRoomTypeRemove().getSelectedItem().toString();

        if(roomType.equals("Standard"))
            hotelSystem.removeRoom(hotel, roomCount, 1);
        else if(roomType.equals("Deluxe"))
            hotelSystem.removeRoom(hotel, roomCount, 2);
        else
            hotelSystem.removeRoom(hotel, roomCount, 3);
        updateCbRmToRemove();
    }

    public void updateBasePrice(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        double newPrice = Double.parseDouble(view.getManageHotelPanel().getTfNewPrice());
        if(!hotelSystem.updatePrice(hotel, newPrice))
            view.setErrorMessage("Cannot Update Price. Either a room is reserved or price is less than P100");
        else
            view.setConfirmMessage("Price updated to " + newPrice);
    }

    public void updatePriceMod(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        int startDate = (int) view.getManageHotelPanel().getCbStartDateMod().getSelectedItem();
        int endDate = (int) view.getManageHotelPanel().getCbEndDateMod().getSelectedItem();
        double modifier = Double.parseDouble(view.getManageHotelPanel().getTfNewPriceMod());

        if(!hotelSystem.changeDateModifier(hotel, startDate, endDate, modifier))
            view.setErrorMessage("Invalid input or Hotel has reservations");
        else
            view.setConfirmMessage("Price modifier updated to " + modifier + " from June " + startDate + " to June " + endDate);

    }
    public void showBasicReservation(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        ArrayList<String> reservationInfo = new ArrayList<>();
        String guestName = view.getManageHotelPanel().getTfGuestName();

        if(hotelSystem.searchReservation(hotel, guestName, reservationInfo))
            view.getManageHotelPanel().updateRsrvPanel(reservationInfo);
        else
            view.getManageHotelPanel().updateRsrvPanel();
    }

    public void removeReservation(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String guestName = view.getManageHotelPanel().getTfGuestName();
        if(!hotelSystem.removeReservation(hotel, guestName))
            view.setErrorMessage("Cannot Remove this reservation");
        else
            view.setConfirmMessage("Reservation by " + guestName + " Removed");
    }

    public void removeHotel(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        hotelSystem.removeHotel(hotel);
        view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "hotelRemovedPanel");
        updateCbHotels();
    }



    public void updateCbHotels(){
        view.getCbHotelNames().removeAllItems();
        view.getBookReservationPanel().getCbHotels().removeAllItems();
        for(Hotel hotel : hotelSystem.getHotels()){
            view.getCbHotelNames().addItem(hotel.getHotelName());
            view.getBookReservationPanel().getCbHotels().addItem(hotel.getHotelName());
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

        view.getViewHotelPanel().getCbRoomNames().removeAllItems();
        for(String roomName : roomList){
            view.getViewHotelPanel().getCbRoomNames().addItem(roomName);
        }
    }


    public void updateCbRmToAdd(){
        view.getManageHotelPanel().getCbRmToAdd().removeAllItems();
        Hotel hotel = hotelSystem.getHotels().get(index);
        for(int i = 1; i <= 50 - hotel.getRooms().size(); i++){
            view.getManageHotelPanel().getCbRmToAdd().addItem(i);
        }
    }

    public void updateCbRmToRemove(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String roomType = view.getManageHotelPanel().getCbRoomTypeRemove().getSelectedItem().toString();
        int roomCount = 0;
        boolean removable = hotel.getRooms().size() > 1;
        view.getManageHotelPanel().getCbRmToRemove().removeAllItems();


        for(int i = 0; i < hotel.getRooms().size() && removable; i++){
            if(roomType.equals("Standard")){
                if(hotel.getRooms().get(i) instanceof Standard) {
                    roomCount++;
                    view.getManageHotelPanel().getCbRmToRemove().addItem(roomCount);
                }
            }
            if(roomType.equals("Deluxe")){
                if(hotel.getRooms().get(i) instanceof Deluxe) {
                    roomCount++;
                    view.getManageHotelPanel().getCbRmToRemove().addItem(roomCount);
                }
            }
            if(roomType.equals("Executive")){
                if(hotel.getRooms().get(i) instanceof Executive){
                    roomCount++;
                    view.getManageHotelPanel().getCbRmToRemove().addItem(roomCount);
                }
            }
        }
        if(roomCount == hotel.getRooms().size())
            view.getManageHotelPanel().getCbRmToRemove().removeItemAt(roomCount - 1);
        view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "removeRoom");
    }

    public void searchAvailRooms(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String roomType = view.getBookReservationPanel().getCbRoomTypes().getSelectedItem().toString();
        ArrayList<String> datesAvail = new ArrayList<>();

        if(roomType.equals("Standard"))
            datesAvail = hotelSystem.getHotelDates(hotel, 1);
        if(roomType.equals("Deluxe"))
            datesAvail = hotelSystem.getHotelDates(hotel, 2);
        if(roomType.equals("Executive"))
            datesAvail = hotelSystem.getHotelDates(hotel, 3);
        view.getBookReservationPanel().showDates(datesAvail);
    }

    public boolean validateBook(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        int checkInDate = (int) view.getBookReservationPanel().getCbCheckIn().getSelectedItem();
        int checkOutDate = (int) view.getBookReservationPanel().getCbCheckOut().getSelectedItem();
        String roomType = view.getBookReservationPanel().getCbRoomTypes().getSelectedItem().toString();
        int roomTypePar = 0;
        boolean valid = checkInDate < checkOutDate;

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

        view.setErrorMessage("Invalid Dates!");
        view.getBookReservationPanel().setTfGuestName("");
        view.getBookReservationPanel().setTfDiscountCode("");
        view.getBookReservationPanel().getCalendarPanel().setVisible(false);
        view.getBookReservationPanel().getBookReservationCardLayout().show(view.getBookReservationPanel().getBookReservationCard(), "promptPanel");
        return false;
    }

    public void updateConfirmInfo(int bookedIndex){
        breakdownList.clear();
        Hotel hotel = hotelSystem.getHotels().get(index);
        bookedCheckInDate = (int) view.getBookReservationPanel().getCbCheckIn().getSelectedItem();
        bookedCheckOutDate = (int) view.getBookReservationPanel().getCbCheckOut().getSelectedItem();
        String discCode = view.getBookReservationPanel().getTfDiscountCode();


        if(discCode.isEmpty())
                breakdownList = hotelSystem.breakdownList(hotel, bookedIndex, bookedCheckInDate, bookedCheckOutDate);
        else
                breakdownList = hotelSystem.breakdownList(hotel, bookedIndex, bookedCheckInDate, bookedCheckOutDate, discCode);

        view.getBookReservationPanel().updateRoomBreakList(breakdownList);
        view.getBookReservationPanel().getBookReservationCardLayout().show(view.getBookReservationPanel().getBookReservationCard(), "confirmPanel");
    }

    public void confirmBook(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        bookedCheckInDate = (int) view.getBookReservationPanel().getCbCheckIn().getSelectedItem();
        bookedCheckOutDate = (int) view.getBookReservationPanel().getCbCheckOut().getSelectedItem();
        String guestName = view.getBookReservationPanel().getTfGuestName();
        String discCode = view.getBookReservationPanel().getTfDiscountCode();

        hotelSystem.book(hotel, guestName, bookedRoomIndex, bookedCheckInDate, bookedCheckOutDate, discCode, breakdownList);
        System.out.println("Booking Added!");
        view.setConfirmMessage("Booking Added! Your Room No is: " + hotel.getRooms().get(bookedRoomIndex).getRoomName());
        view.getBookReservationPanel().setTfGuestName("");
        view.getBookReservationPanel().setTfDiscountCode("");
        view.getBookReservationPanel().getCalendarPanel().setVisible(false);
        view.getBookReservationPanel().getBookReservationCardLayout().show(view.getBookReservationPanel().getBookReservationCard(), "promptPanel");
    }

    public void cancelBook(){
        view.setConfirmMessage("Booking Cancelled");
        view.getBookReservationPanel().setTfGuestName("");
        view.getBookReservationPanel().setTfDiscountCode("");
        view.getBookReservationPanel().getCalendarPanel().setVisible(false);
        view.getBookReservationPanel().getBookReservationCardLayout().show(view.getBookReservationPanel().getBookReservationCard(), "promptPanel");
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
