import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Represents a Panel which contains options for viewing a hotel
 */
public class ViewHotelPanel extends Panel {
    /**
     * label for hotel name
     */
    private JLabel lblHotelName;
    /**
     * label containing number of rooms
     */
    private JLabel lblRooms;
    /**
     * label containing income of hotel
     */
    private JLabel lblIncome;

    /**
     * button for accessing viewRoomsOnDate panel
     */
    private JButton viewRoomsOnDate;
    /**
     * button for accessing viewRoomInfo panel
     */
    private JButton viewRoomInfo;
    /**
     * button for accessing viewReservation panel
     */
    private JButton viewReservation;
    /**
     * select date button for roomsOnDate
     */
    private JButton selectDate;
    /**
     * selection of dates in a month
     */
    private JComboBox<Integer> cbDates;

    /**
     * stores current hotel name
     */
    private String hotelName;
    /**
     * stores number of rooms
     */
    private int roomCount;
    /**
     * stores total income
     */
    private double income;

    /**
     * label containing date information
     */
    private JLabel lblDateInfo;
    /**
     * label containing number of booked rooms
     */
    private JLabel lblBookedRooms;
    /**
     * label containing number of available
     */
    private JLabel lblAvailableRooms;
    /**
     * stores chosen date to check
     */
    private int chosenDate;
    /**
     * stores number of booked rooms in a date
     */
    private int bookedRooms;
    /**
     * JPanel containing room information
     */
    private JPanel roomInfo;
    /**
     * option of rooms to view
     */
    private JComboBox<String> cbRoomNames;
    /**
     * button to select which room to view
     */
    private JButton selectRoom;
    /**
     * label containing room name
     */
    private JLabel lblRoomName;
    /**
     * label containing room type
     */
    private JLabel lblRoomType;
    /**
     * label containing base price of room
     */
    private JLabel lblBasePrice;
    /**
     * label containing list of availability in a month
     */
    private ArrayList<JLabel> lblDatesList;
    /**
     * scroll panel for lblDatesList
     */
    private JScrollPane dateScroll;
    /**
     * panel to store the scrollPane
     */
    private JPanel datesPanel;

    /**
     * text field that takes in guest name for viewing reservation
     */
    private JTextField tfGuestName;
    /**
     * label containing name of the hotel of the reservation
     */
    private JLabel lblRsrvHotelName;
    /**
     * button that searches for a reservation given the entered guest name
     */
    private JButton searchReservation;
    /**
     * panel containing reservation information
     */
    private JPanel reservationInfoPanel;
    /**
     * panel containing breakdown price of the reservation's stay
     */
    private JPanel breakdownPanel;
    /**
     * list of jlabels which stores breakdown information
     */
    private ArrayList<JLabel> lblInfoList;

    /**
     * main panel of the class
     */
    private JPanel viewPanel;
    /**
     * main card layout of this class
     */
    private CardLayout viewHotelCardLayout;

    /**
     * panel for viewing rooms on a chosen date
     */
    private JPanel viewRODInfo;

    /**
     * Creates a ViewHotelPanel
     */
    public ViewHotelPanel() {
        super(new BorderLayout());

        init();
        setVisible(true);
    }

    /**
     * initialization method
     */
    public void init() {
        //for dates
        Integer[] dateList = new Integer[31];
        for(int i = 1; i <= 31; i++)
            dateList[i-1] = i;
        cbDates = new JComboBox<Integer>(dateList);
        cbRoomNames = new JComboBox<String>();
        lblInfoList = new ArrayList<JLabel>();
        lblDatesList = new ArrayList<JLabel>();
        datesPanel = new JPanel();
        hotelName = "";

        //header of main panel
        JLabel lblViewHotel = new JLabel("View Hotel");
        lblViewHotel.setFont(new Font("Arial", Font.BOLD, 18));
        lblViewHotel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblViewHotel, BorderLayout.NORTH);

        //initialization of card layout
        viewHotelCardLayout = new CardLayout();
        viewPanel = new JPanel(viewHotelCardLayout);
        breakdownPanel = new JPanel();
        breakdownPanel.setLayout(new BoxLayout(breakdownPanel, BoxLayout.Y_AXIS));


        //initialization of panels under viewHotel
        JPanel hiLevel = viewHiLevel();
        JPanel roomsOnDate = viewRoomsOnDate();
        JPanel roomInfo = viewRoomInfo();
        JPanel viewReservation = viewReservation();

        //adding these panels to the cardPanel
        viewPanel.add(hiLevel, "hiLevel");
        viewPanel.add(roomsOnDate, "roomsOnDate");
        viewPanel.add(roomInfo, "roomInfo");
        viewPanel.add(viewReservation, "reservation");

        viewHotelCardLayout.show(viewPanel, "hiLevel");
        this.add(viewPanel, BorderLayout.CENTER);
    }

    /**
     * Creates a panel that shows High level information of a chosen hotel
     * @return JPanel of high level information of a hotel
     */
    public JPanel viewHiLevel(){
        JPanel viewHiLevelPanel = new JPanel();
        viewHiLevelPanel.setLayout(new BoxLayout(viewHiLevelPanel, BoxLayout.Y_AXIS));
        viewHiLevelPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        //displaying high level information
        lblHotelName = new JLabel("Hotel Name: " + hotelName);
        setFontAndStyle(lblHotelName, 14, Font.PLAIN);
        lblRooms = new JLabel("No of Rooms: " + roomCount);
        setFontAndStyle(lblRooms, 14, Font.PLAIN);
        lblIncome = new JLabel("Total Income: " + income);
        setFontAndStyle(lblIncome, 14, Font.PLAIN);

        //displaying buttons for low level hotel information
        viewRoomsOnDate = new JButton("View Available Rooms on Date");
        viewRoomInfo = new JButton("View Room Info");
        viewReservation = new JButton("View Reservation Info");

        setFontAndStyle(viewRoomsOnDate, 12, Font.PLAIN);
        setFontAndStyle(viewRoomInfo, 12, Font.PLAIN);
        setFontAndStyle(viewReservation, 12, Font.PLAIN);

        //adding elements to the HiLevelPanel
        viewHiLevelPanel.add(lblHotelName);
        viewHiLevelPanel.add(lblRooms);
        viewHiLevelPanel.add(lblIncome);
        viewHiLevelPanel.add(Box.createVerticalStrut(10));
        viewHiLevelPanel.add(viewRoomsOnDate);
        viewHiLevelPanel.add(viewRoomInfo);
        viewHiLevelPanel.add(viewReservation);

        return viewHiLevelPanel;
    }

    /**
     * Updates viewHiLevelPanel based on chosen hotel
     * @param hotelName name of chosen hotel
     * @param roomCount total rooms of that hotel
     * @param income total income of the hotel
     */
    public void updateViewHiLevel(String hotelName, int roomCount, double income) {
        this.hotelName = hotelName;
        lblHotelName.setText("Hotel Name: " + this.hotelName);
        lblRooms.setText("No of Rooms: " + roomCount);
        lblIncome.setText("Total Income: " + income);
        //refreshing of panel
        lblHotelName.getParent().repaint();
        lblHotelName.getParent().revalidate();
    }

    /**
     * Creates a panel that views available rooms on a chosen date
     * @return JPanel which stores rooms on date info
     */
    public JPanel viewRoomsOnDate(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //asks for date to be checked
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

        //displays rooms on date information based on the selected option above
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
        //will be set to false until a date is chosen
        viewRODInfo.setVisible(false);

        gbc.gridy = 1;
        panel.add(viewRODInfo, gbc);

        return panel;
    }

    /**
     * Creates a panel that views a chosen room information
     * @return JPanel of room viewing options
     */
    public JPanel viewRoomInfo(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //promt panel. panel which asks which room to view
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
        //displays room information
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
        //displays room availability
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


    /**
     * Updates room information for viewRoomInfo panel
     * @param roomName name/number of room
     * @param roomType type of room
     * @param basePrice base price of room
     * @param dates list of availability per date
     */
    public void updateRoomInfo(String roomName, String roomType, double basePrice, ArrayList<String> dates){
        //setting new room info
        lblRoomName.setText("Room Name: " + roomName);
        lblRoomType.setText("Room Type: " + roomType);
        lblBasePrice.setText("Base Price: " + basePrice);
        //resets by clearing then adding new date availability
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
        //refreshes panel
        datesPanel.repaint();
        datesPanel.revalidate();

        //roomInfo.removeAll();
        roomInfo.repaint();
        roomInfo.revalidate();
        roomInfo.setVisible(true);
    }

    /**
     * Creates a panel that can view a specific reservation info in a hotel
     * @return JPanel which contains reservation information and options
     */
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

    /**
     * Updates the breakdownpanel under reservation information
     * @param infoList list of reservation information to display
     */
    public void updateInfoPanel(ArrayList<String> infoList){
        //refreshes information
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

        //refreshes panel
        reservationInfoPanel.add(breakdownPanel);
        reservationInfoPanel.repaint();
        reservationInfoPanel.revalidate();
        reservationInfoPanel.setVisible(true);
    }

    /**
     * Method to set the font and style of a component in the class
     * @param component type of Java swing component
     * @param fontSize size of font
     * @param fontStyle font style
     */
    private void setFontAndStyle(JComponent component, int fontSize, int fontStyle) {
        component.setFont(new Font("Arial", fontStyle, fontSize));
    }

    /**
     * ActionListener method
     * @param listener listener for performed action
     */
    public void setActionListener(ActionListener listener) {
        viewRoomsOnDate.addActionListener(listener);
        viewRoomInfo.addActionListener(listener);
        viewReservation.addActionListener(listener);

        selectRoom.addActionListener(listener);

        selectDate.addActionListener(listener);
        searchReservation.addActionListener(listener);
    }

    /**
     * getter method for cbDates
     * @return cbDates
     */
    public JComboBox getCbDates() {
        return cbDates;
    }
    public String getHotelName() {
        return hotelName;
    }
    /**
     * getter method for viewHotelCardLayout
     * @return viewHotelCardLayout
     */
    public CardLayout getViewHotelCardLayout() {
        return viewHotelCardLayout;
    }
    /**
     * getter method for viewPanel
     * @return viewPanel
     */
    public JPanel getViewPanel() {
        return viewPanel;
    }

    /**
     * Getter method for cbRoomNames
     * @return cbRoomNames
     */
    public JComboBox<String> getCbRoomNames() {
        return cbRoomNames;
    }

    /**
     * Getter method of tfGuestName as a string
     * @return String contained in the text field
     */
    public String getTfGuestName(){
        return  tfGuestName.getText();
    }
}
