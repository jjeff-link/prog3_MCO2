import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewHotelPanel extends Panel {
    private JLabel lblHotelName;
    private JLabel lblRooms;
    private JLabel lblIncome;

    private JButton viewRoomsOnDate;
    private JButton viewRoomInfo;
    private JButton viewReservation;
    private JButton selectDate;

    private JComboBox<Integer> cbDates;

    private String hotelName;
    private int roomCount;
    private double income;


    private JLabel lblDateInfo;
    private JLabel lblBookedRooms;
    private JLabel lblAvailableRooms;
    private int chosenDate;
    private int bookedRooms;

    private JComboBox<String> cbRoomNames;
    private JButton selectRoom;
    private JLabel lblRoomName;
    private JLabel lblRoomType;


    private JPanel viewPanel;
    private CardLayout viewHotelCardLayout;


    private JPanel viewRODInfo;

    public ViewHotelPanel() {
        super(new BorderLayout());

        init();
        setVisible(true);
    }

    public void init() {
        Integer[] dateList = new Integer[31];
        for(int i = 1; i <= 31; i++)
            dateList[i-1] = i;
        cbDates = new JComboBox<Integer>(dateList);
        cbRoomNames = new JComboBox<String>();


        JLabel lblViewHotel = new JLabel("View Hotel");
        this.add(lblViewHotel, BorderLayout.NORTH);

        viewHotelCardLayout = new CardLayout();
        viewPanel = new JPanel(viewHotelCardLayout);


        JPanel hiLevel = viewHiLevel();
        JPanel roomsOnDate = viewRoomsOnDate();
        JPanel roomInfo = viewRoomInfo();

        viewPanel.add(hiLevel, "hiLevel");
        viewPanel.add(roomsOnDate, "roomsOnDate");
        viewPanel.add(roomInfo, "roomInfo");

        viewHotelCardLayout.show(viewPanel, "hiLevel");
        this.add(viewPanel, BorderLayout.CENTER);
    }

    public JPanel viewHiLevel(){
        JPanel viewHiLevelPanel = new JPanel();
        viewHiLevelPanel.setLayout(new BoxLayout(viewHiLevelPanel, BoxLayout.Y_AXIS));
        lblHotelName = new JLabel("Hotel Name: " + hotelName);
        viewHiLevelPanel.add(lblHotelName);
        lblRooms = new JLabel("No of Rooms: " + roomCount);
        viewHiLevelPanel.add(lblRooms);
        lblIncome = new JLabel("Total Income: " + income);
        viewHiLevelPanel.add(lblIncome);

        viewRoomsOnDate = new JButton("View Available Rooms on Date");
        viewHiLevelPanel.add(viewRoomsOnDate);
        viewRoomInfo = new JButton("View Room Info");
        viewHiLevelPanel.add(viewRoomInfo);
        viewReservation = new JButton("View Reservation Info");
        viewHiLevelPanel.add(viewReservation);


        return viewHiLevelPanel;
    }

    public void updateViewHiLevel(String hotelName, int roomCount, double income) {
        lblHotelName.setText("Hotel Name: " + hotelName);
        lblRooms.setText("No of Rooms: " + roomCount);
        lblIncome.setText("Total Income: " + income);
    }

    public JPanel viewRoomsOnDate(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        //FlowGroup
        JPanel panelFirst = new JPanel(new FlowLayout());
        JLabel lblPrompt = new JLabel("Select a Date: ");
        panelFirst.add(lblPrompt);
        JLabel lblDate = new JLabel("June");
        panelFirst.add(lblDate);

        panelFirst.add(cbDates);

        panel.add(panelFirst);

        //selectButton
        selectDate = new JButton("Select Date");
        panel.add(selectDate);

        //InfoGroup
        viewRODInfo = new JPanel();
        viewRODInfo.setLayout(new BoxLayout(viewRODInfo, BoxLayout.Y_AXIS));
        lblDateInfo = new JLabel("Date: June " + chosenDate + ", 2024");
        viewRODInfo.add(lblDateInfo);
        lblBookedRooms = new JLabel("Booked Rooms: " + bookedRooms);
        viewRODInfo.add(lblBookedRooms);
        lblAvailableRooms = new JLabel("Available Rooms: " + (roomCount - bookedRooms));
        viewRODInfo.add(lblAvailableRooms);
        panel.add(viewRODInfo);

        viewRODInfo.setVisible(false);

        return panel;
    }

    public void updateROD(int chosenDate, int bookedRooms) {
        lblDateInfo.setText("Date: June " + chosenDate + ", 2024");
        lblBookedRooms.setText("Booked Rooms: " + bookedRooms);
        lblAvailableRooms.setText("Available Rooms: " + (roomCount - bookedRooms));
    }

    public JPanel viewRoomInfo(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //FlowGroup
        JPanel panelFirst = new JPanel(new FlowLayout());
        JLabel lblPrompt = new JLabel("Select a Room: ");
        panelFirst.add(lblPrompt);
        panelFirst.add(cbRoomNames);
        selectRoom = new JButton("Select Room");
        panelFirst.add(selectRoom);
        panel.add(panelFirst);



        return panel;

    }


    public void setActionListener(ActionListener listener) {
        viewRoomsOnDate.addActionListener(listener);
        viewRoomInfo.addActionListener(listener);
        viewReservation.addActionListener(listener);

        selectDate.addActionListener(listener);
    }


    public JLabel getLblHotelName() {
        return lblHotelName;
    }
    public JLabel getLblRooms() {
        return lblRooms;
    }
    public JLabel getLblIncome() {
        return lblIncome;
    }
    public JButton getViewRoomsOnDate() {
        return viewRoomsOnDate;
    }
    public JButton getViewRoomInfo() {
        return viewRoomInfo;
    }
    public JButton getViewReservation() {
        return viewReservation;
    }
    public JButton getSelectDate() {
        return selectDate;
    }
    public JComboBox getCbDates() {
        return cbDates;
    }
    public String getHotelName() {
        return hotelName;
    }
    public int getRoomCount() {
        return roomCount;
    }
    public double getIncome() {
        return income;
    }
    public int getChosenDate() {
        return chosenDate;
    }
    public int getBookedRooms() {
        return bookedRooms;
    }
    public CardLayout getViewHotelCardLayout() {
        return viewHotelCardLayout;
    }
    public JPanel getViewPanel() {
        return viewPanel;
    }

    public JPanel getViewRODInfo() {
        return viewRODInfo;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }
    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }
    public void setIncome(double income) {
        this.income = income;
    }
    public void setChosenDate(int chosenDate) {
        this.chosenDate = chosenDate;
    }
    public void setBookedRooms(int bookedRooms) {
        this.bookedRooms = bookedRooms;
    }

    public JComboBox<String> getCbRoomNames() {
        return cbRoomNames;
    }

    public JButton getSelectRoom() {
        return selectRoom;
    }
}
