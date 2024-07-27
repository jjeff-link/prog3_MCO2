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

public class tempCtr implements ActionListener, DocumentListener, ItemListener {
    private HotelSystem hotelSystem;
    private View view;
    int index;

    public tempCtr(View view, HotelSystem hotelSystem) {
        this.view = view;
        this.hotelSystem = hotelSystem;
        view.setActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Back")){
            view.getMainCardLayout().show(view.getMainCardPanel(), "main");
        }
        if(e.getActionCommand().equals("Create Hotel")){
            view.getMainCardLayout().show(view.getMainCardPanel(), "create");
        }

        if(e.getActionCommand().equals("View Hotel")){
            view.getMainCardLayout().show(view.getMainCardPanel(), "searchHotel");
            view.setCurrView(2);

        }

        if(e.getActionCommand().equals("Manage Hotel")){
            view.getMainCardLayout().show(view.getMainCardPanel(), "searchHotel");
            view.setCurrView(3);
        }

        if(e.getActionCommand().equals("Book Reservation")){
            view.getMainCardLayout().show(view.getMainCardPanel(), "searchHotel");
            view.setCurrView(4);
        }

        if(e.getActionCommand().equals("Add Hotel")){
            hotelSystem.addHotel(view.getHotelName());
            updateCbHotels();
            updateCbRooms();
        }

        if(e.getActionCommand().equals("Search Hotel")){
            if(view.getCurrView() == 2){
                hiLevel();
            }
            else if(view.getCurrView() == 3){
                view.getMainCardLayout().show(view.getMainCardPanel(), "manageHotel");
                view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "manageOptionPanel");
            }
        }
        if(e.getActionCommand().equals("View Available Rooms on Date")){
            view.getViewHotelPanel().getViewHotelCardLayout().show(view.getViewHotelPanel().getViewPanel(), "roomsOnDate");
        }

        if(e.getActionCommand().equals("Select Date")){
            viewRoomsOnDate();
        }
        if(e.getActionCommand().equals("View Room Info")){
            view.getViewHotelPanel().getViewHotelCardLayout().show(view.getViewHotelPanel().getViewPanel(), "roomInfo");
        }
        if(e.getActionCommand().equals("Change Name")){
            view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "changeNamePanel");
        }
        if(e.getActionCommand().equals("Confirm Name Change")){
            changeName();
        }
        if(e.getActionCommand().equals("Add Room")){
            updateCbRmToAdd();
            view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "addRoomsPanel");
        }
        if(e.getActionCommand().equals("Confirm Add Room")){
            addRoom();
        }
        if(e.getActionCommand().equals("Remove Room")){
            view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "removeRoomsPanel");
        }
        if(e.getActionCommand().equals("Select Room Type")){
            updateCbRmToRemove();
        }
        if(e.getActionCommand().equals("Confirm Remove Room")){
            removeRoom();
        }
        if(e.getActionCommand().equals("Update Base Price")){
            view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "updatePricePanel");
        }
        if(e.getActionCommand().equals("Confirm Base Price")){
            updateBasePrice();
        }
        if(e.getActionCommand().equals("Update Price Modifier")){
            view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "updatePriceModPanel");
        }
        if(e.getActionCommand().equals("Remove Reservation")){
            view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "removeReservationPanel");
        }
        if(e.getActionCommand().equals("Remove Hotel")){
            view.getManageHotelPanel().getManageCardLayout().show(view.getManageHotelPanel().getManagePanel(), "removeHotelPanel");
        }
        if(e.getActionCommand().equals("Book Reservation")){
            view.getMainCardLayout().show(view.getMainCardPanel(), "bookReservation");
        }
        if(e.getActionCommand().equals("Confirm Price Modifier")){
            updatePriceMod();
        }
        if(e.getActionCommand().equals("Next")){
            view.getBookReservationPanel().getBookReservationCardLayout().show(view.getBookReservationPanel().getBookReservationCard(), "confirmPanel");
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    public void hiLevel(){
        index = hotelSystem.searchHotel(view.cbHotelNames.getSelectedItem().toString());
        Hotel hotel = hotelSystem.getHotels().get(index);

        view.getViewHotelPanel().setHotelName(hotel.getHotelName());
        System.out.println(view.getViewHotelPanel().getHotelName());
        view.getViewHotelPanel().setRoomCount(hotel.getRooms().size());
        view.getViewHotelPanel().setIncome(hotel.getTotalIncome());
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

    public void changeName(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String newName = view.getManageHotelPanel().getTfNewName();

        hotelSystem.changeHotelName(hotel, newName);
        updateCbHotels();
    }

    public void addRoom(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        String roomType = view.getManageHotelPanel().getCbRoomTypeAdd().getSelectedItem().toString();
        int roomTypeChoice;
        int roomCount = (int) view.getManageHotelPanel().getCbRmToAdd().getSelectedItem();
        System.out.println(roomCount);

        if(roomType.equals("Standard"))
            roomTypeChoice = 1;
        else if(roomType.equals("Deluxe"))
            roomTypeChoice = 2;
        else
            roomTypeChoice = 3;

        hotelSystem.addRoom(hotel, roomCount, roomTypeChoice);
        updateCbRmToAdd();
        updateCbRooms();
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
        hotelSystem.updatePrice(hotel, newPrice);
    }

    public void updatePriceMod(){
        Hotel hotel = hotelSystem.getHotels().get(index);
        int startDate = (int) view.getManageHotelPanel().getCbStartDateMod().getSelectedItem();
        int endDate = (int) view.getManageHotelPanel().getCbEndDateMod().getSelectedItem();
        double modifier = Double.parseDouble(view.getManageHotelPanel().getTfNewPriceMod());

        hotelSystem.changeDateModifier(hotel, startDate, endDate, modifier);

    }



    public void updateCbHotels(){
        view.cbHotelNames.removeAllItems();
        view.getBookReservationPanel().getCbHotels().removeAllItems();
        for(Hotel hotel : hotelSystem.getHotels()){
            view.cbHotelNames.addItem(hotel.getHotelName());
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
