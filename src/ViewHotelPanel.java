import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private JPanel roomInfo;
    private JComboBox<String> cbRoomNames;
    private JButton selectRoom;
    private JLabel lblRoomName;
    private JLabel lblRoomType;
    private JLabel lblBasePrice;
    private ArrayList<JLabel> lblDatesList;
    private JScrollPane dateScroll;
    private JPanel datesPanel;



    private JTextField tfGuestName;
    private JLabel lblRsrvHotelName;
    private JButton searchReservation;
    private JPanel reservationInfoPanel;
    private JPanel breakdownPanel;
    private ArrayList<JLabel> lblInfoList;


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
        lblInfoList = new ArrayList<JLabel>();
        lblDatesList = new ArrayList<JLabel>();
        datesPanel = new JPanel();
        hotelName = "";


        JLabel lblViewHotel = new JLabel("View Hotel");
        lblViewHotel.setFont(new Font("Arial", Font.BOLD, 18));
        lblViewHotel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblViewHotel, BorderLayout.NORTH);

        viewHotelCardLayout = new CardLayout();
        viewPanel = new JPanel(viewHotelCardLayout);
        breakdownPanel = new JPanel();
        breakdownPanel.setLayout(new BoxLayout(breakdownPanel, BoxLayout.Y_AXIS));



        JPanel hiLevel = viewHiLevel();
        JPanel roomsOnDate = viewRoomsOnDate();
        JPanel roomInfo = viewRoomInfo();
        JPanel viewReservation = viewReservation();

        viewPanel.add(hiLevel, "hiLevel");
        viewPanel.add(roomsOnDate, "roomsOnDate");
        viewPanel.add(roomInfo, "roomInfo");
        viewPanel.add(viewReservation, "reservation");

        viewHotelCardLayout.show(viewPanel, "hiLevel");
        this.add(viewPanel, BorderLayout.CENTER);
    }

    public JPanel viewHiLevel(){
        JPanel viewHiLevelPanel = new JPanel();
        viewHiLevelPanel.setLayout(new BoxLayout(viewHiLevelPanel, BoxLayout.Y_AXIS));
        viewHiLevelPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        lblHotelName = new JLabel("Hotel Name: " + hotelName);
        setFontAndStyle(lblHotelName, 14, Font.PLAIN);
        lblRooms = new JLabel("No of Rooms: " + roomCount);
        setFontAndStyle(lblRooms, 14, Font.PLAIN);
        lblIncome = new JLabel("Total Income: " + income);
        setFontAndStyle(lblIncome, 14, Font.PLAIN);

        viewRoomsOnDate = new JButton("View Available Rooms on Date");
        viewRoomInfo = new JButton("View Room Info");
        viewReservation = new JButton("View Reservation Info");

        setFontAndStyle(viewRoomsOnDate, 12, Font.PLAIN);
        setFontAndStyle(viewRoomInfo, 12, Font.PLAIN);
        setFontAndStyle(viewReservation, 12, Font.PLAIN);

        viewHiLevelPanel.add(lblHotelName);
        viewHiLevelPanel.add(lblRooms);
        viewHiLevelPanel.add(lblIncome);
        viewHiLevelPanel.add(Box.createVerticalStrut(10)); // Spacing between labels and buttons
        viewHiLevelPanel.add(viewRoomsOnDate);
        viewHiLevelPanel.add(viewRoomInfo);
        viewHiLevelPanel.add(viewReservation);

        return viewHiLevelPanel;
    }

    public void updateViewHiLevel(String hotelName, int roomCount, double income) {
        this.hotelName = hotelName;
        lblHotelName.setText("Hotel Name: " + this.hotelName);
        lblRooms.setText("No of Rooms: " + roomCount);
        lblIncome.setText("Total Income: " + income);

        lblHotelName.getParent().repaint();
        lblHotelName.getParent().revalidate();
    }

    public JPanel viewRoomsOnDate(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel panelFirst = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblPrompt = new JLabel("Select a Date: ");
        setFontAndStyle(lblPrompt, 14, Font.PLAIN);
        panelFirst.add(lblPrompt);
        panelFirst.add(cbDates);
        selectDate = new JButton("Select Date");
        setFontAndStyle(selectDate, 12, Font.PLAIN);
        panelFirst.add(selectDate);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(panelFirst, gbc);

        viewRODInfo = new JPanel();
        viewRODInfo.setLayout(new BoxLayout(viewRODInfo, BoxLayout.Y_AXIS));
        lblDateInfo = new JLabel("Date: June " + chosenDate + ", 2024");
        setFontAndStyle(lblDateInfo, 14, Font.PLAIN);
        lblBookedRooms = new JLabel("Booked Rooms: " + bookedRooms);
        setFontAndStyle(lblBookedRooms, 14, Font.PLAIN);
        lblAvailableRooms = new JLabel("Available Rooms: " + (roomCount - bookedRooms));
        setFontAndStyle(lblAvailableRooms, 14, Font.PLAIN);
        viewRODInfo.add(lblDateInfo);
        viewRODInfo.add(lblBookedRooms);
        viewRODInfo.add(lblAvailableRooms);

        viewRODInfo.setVisible(false);

        gbc.gridy = 1;
        panel.add(viewRODInfo, gbc);

        return panel;
    }

    public void updateROD(int chosenDate, int bookedRooms) {
        lblDateInfo.setText("Date: June " + chosenDate + ", 2024");
        lblBookedRooms.setText("Booked Rooms: " + bookedRooms);
        lblAvailableRooms.setText("Available Rooms: " + (roomCount - bookedRooms));
        viewRoomsOnDate.repaint();
        viewRoomsOnDate.revalidate();
    }

    public JPanel viewRoomInfo(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JPanel panelFirst = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblPrompt = new JLabel("Select a Room: ");
        setFontAndStyle(lblPrompt, 14, Font.PLAIN);
        panelFirst.add(lblPrompt);
        panelFirst.add(cbRoomNames);
        selectRoom = new JButton("Select Room");
        setFontAndStyle(selectRoom, 12, Font.PLAIN);
        panelFirst.add(selectRoom);
        panel.add(panelFirst);

        roomInfo = new JPanel();
        roomInfo.setLayout(new BoxLayout(roomInfo, BoxLayout.Y_AXIS));
        roomInfo.setBorder(BorderFactory.createTitledBorder("Room Information"));
        roomInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblRoomName = new JLabel("Room Name: ");
        setFontAndStyle(lblRoomName, 14, Font.PLAIN);
        roomInfo.add(lblRoomName);
        lblRoomType = new JLabel("Room Type: ");
        setFontAndStyle(lblRoomType, 14, Font.PLAIN);
        roomInfo.add(lblRoomType);
        lblBasePrice = new JLabel("Base Price: ");
        setFontAndStyle(lblBasePrice, 14, Font.PLAIN);
        roomInfo.add(lblBasePrice);
        JLabel lblAvailability = new JLabel("Dates Available: ");
        setFontAndStyle(lblAvailability, 14, Font.PLAIN);
        roomInfo.add(lblAvailability);

        datesPanel = new JPanel();
        datesPanel.setLayout(new BoxLayout(datesPanel, BoxLayout.Y_AXIS));
        datesPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        dateScroll = new JScrollPane(datesPanel);
        dateScroll.setBorder(BorderFactory.createEmptyBorder());
        dateScroll.setPreferredSize(new Dimension(300, 200));

        roomInfo.add(dateScroll);

        panel.add(roomInfo);

        roomInfo.setVisible(false);

        return panel;
    }



    public void updateRoomInfo(String roomName, String roomType, double basePrice, ArrayList<String> dates){
        lblRoomName.setText("Room Name: " + roomName);
        lblRoomType.setText("Room Type: " + roomType);
        lblBasePrice.setText("Base Price: " + basePrice);

        lblDatesList.clear();
        datesPanel.removeAll();
        datesPanel.repaint();
        datesPanel.revalidate();
        for(String line : dates){
            this.lblDatesList.add(new JLabel(line));
        }
        for(JLabel label : lblDatesList){
            datesPanel.add(label);
        }

        datesPanel.repaint();
        datesPanel.revalidate();

        //roomInfo.removeAll();
        roomInfo.repaint();
        roomInfo.revalidate();
        roomInfo.setVisible(true);
    }

    public JPanel viewReservation(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Prompt and input field
        JPanel panelFirst = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblPrompt = new JLabel("Enter Guest Name: ");
        setFontAndStyle(lblPrompt, 14, Font.PLAIN);
        panelFirst.add(lblPrompt);
        tfGuestName = new JTextField(20);
        panelFirst.add(tfGuestName);
        searchReservation = new JButton("Search Reservation");
        setFontAndStyle(searchReservation, 12, Font.PLAIN);
        panelFirst.add(searchReservation);

        gbc.gridy = 0;
        panel.add(panelFirst, gbc);

        // Reservation info panel
        reservationInfoPanel = new JPanel();
        reservationInfoPanel.setLayout(new BoxLayout(reservationInfoPanel, BoxLayout.Y_AXIS));
        reservationInfoPanel.setBorder(BorderFactory.createTitledBorder("Reservation Information"));
        reservationInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblRsrvHotelName = new JLabel("Hotel Name: " + this.hotelName);
        setFontAndStyle(lblHotelName, 14, Font.PLAIN);
        reservationInfoPanel.add(lblRsrvHotelName);
        reservationInfoPanel.add(breakdownPanel);

        gbc.gridy = 1;
        panel.add(reservationInfoPanel, gbc);

        reservationInfoPanel.setVisible(false);

        return panel;
    }


    public void updateInfoPanel(ArrayList<String> infoList){
        this.lblInfoList.clear();
        breakdownPanel.removeAll();
        reservationInfoPanel.removeAll();
        reservationInfoPanel.add(lblHotelName);
        for(String line : infoList){
            System.out.println(line);
            this.lblInfoList.add(new JLabel(line));
        }

        for(JLabel label : lblInfoList){
            breakdownPanel.add(label);
        }


        reservationInfoPanel.add(breakdownPanel);
        reservationInfoPanel.repaint();
        reservationInfoPanel.revalidate();
        reservationInfoPanel.setVisible(true);
    }

    private void setFontAndStyle(JComponent component, int fontSize, int fontStyle) {
        component.setFont(new Font("Arial", fontStyle, fontSize));
    }


    public void setActionListener(ActionListener listener) {
        viewRoomsOnDate.addActionListener(listener);
        viewRoomInfo.addActionListener(listener);
        viewReservation.addActionListener(listener);

        selectRoom.addActionListener(listener);

        selectDate.addActionListener(listener);
        searchReservation.addActionListener(listener);
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
    public String getTfGuestName(){
        return  tfGuestName.getText();
    }

    public JPanel getReservationInfoPanel() {
        return reservationInfoPanel;
    }
}
