import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Represents the GUI for a Booking Simulation
 */
public class BookReservationPanel extends JPanel {

    /**
     * Represents the main card layout of the panel
     */
    private CardLayout bookReservationCardLayout = new CardLayout();
    /**
     * The cardPanel for the class
     */
    private JPanel bookReservationCard = new JPanel();

    /**
     * Panel shown for asking for input
     */
    private JPanel promptPanel;
    /**
     * Panel shown for checking booking details
     */
    private JPanel confirmPanel;

    /**
     * Part of the infoPanel, will present the price breakdown per night
     */
    private JPanel breakdownPanel;
    /**
     * Presents the complete details of a reservation before finalizing a booking
     */
    private JPanel infoPanel;

    /**
     * Text field for the guest name
     */
    private JTextField tfGuestNameField;
    /**
     * JComboBox with hotel choices
     */
    private JComboBox<String> cbHotels;
    /**
     * JComboBox with room type choices
     */
    private JComboBox<String> cbRoomTypes;
    /**
     * JComboBox for check in dates
     */
    private JComboBox<Integer> cbCheckIn;
    /**
     * JComboBox for check out dates
     */
    private JComboBox<Integer> cbCheckOut;
    /**
     * JButton to search for room availability
     */
    private JButton searchRoomsBtn;
    /**
     * JButton to proceed to the next steps in booking
     */
    private JButton nextBtn;
    /**
     * Presents the date availability in a hotel
     */
    private JPanel calendarPanel;
    /**
     * Part of the calendarPanel. Specifically presents the dates
     */
    private JPanel datesAvailPanel;
    /**
     * ScrollPane in case booking details have long breakdowns
     */
    private JScrollPane datesScroller;

    /**
     * chosen index of a room
     */
    private int roomIndex;
    /**
     * Arraylist of JLabels containigg the price breakdown
     */
    private ArrayList<JLabel> priceBreakdown;
    /**
     * Textfield if user inputs a discount code
     */
    private JTextField tfDiscountCode;
    /**
     * JButton to confirm final booking details
     */
    private JButton confirmBookBtn;
    /**
     * Jbutton to cancel booking
     */
    private JButton cancelBookBtn;

    /**
     * JLabel which stores the chosen room name upon booking
     */
    private JLabel roomName;

    /**
     * Creates a BookReservationPanel
     */
    public BookReservationPanel() {
        setLayout(new BorderLayout());

        init();
        setVisible(true);
    }

    /**
     * Initializer method to fill details of panel
     */
    public void init() {
        String[] roomTypes = {"Standard", "Deluxe", "Executive"}; //type of rooms
        Integer[] checkInDates = new Integer[30];
        Integer[] checkOutDates = new Integer[30];
        for(int i = 1; i <= 31; i++) {
            if(i < 31)
                checkInDates[i - 1] = i; //will be entered to the jcomboboxes
            if(i > 1)
                checkOutDates[i - 2] = i;//will be entered to the jcomboboxes
        }
        cbHotels = new JComboBox<String>(); //initialization of JComboBoxes
        cbRoomTypes = new JComboBox<String>(roomTypes);
        cbCheckIn = new JComboBox<Integer>(checkInDates);
        cbCheckOut = new JComboBox<Integer>(checkOutDates);
        priceBreakdown = new ArrayList<JLabel>();
        roomName = new JLabel();
        confirmBookBtn = new JButton("Confirm Booking"); //initialization of Jbuttons
        cancelBookBtn = new JButton("Cancel Booking");


        JLabel lblBookReservation = new JLabel("Book Reservation"); //header label
        lblBookReservation.setHorizontalAlignment(SwingConstants.CENTER);
        lblBookReservation.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(lblBookReservation, BorderLayout.NORTH);

        bookReservationCardLayout = new CardLayout(); //initialization of CardLayouts
        bookReservationCard = new JPanel(bookReservationCardLayout);

        //panels which will be part of the CardLayout
        promptPanel = promptPanel();
        infoPanel = infoPanel();
        breakdownPanel = breakDownPanel();

        bookReservationCard.add(promptPanel, "promptPanel");

       bookReservationCardLayout.show(bookReservationCard, "promptPanel");
        this.add(bookReservationCard, BorderLayout.CENTER);

    }

    /**
     * Asks the user for necessary booking information
     * @return promptPanel
     */
    public JPanel promptPanel(){
        JPanel panel = new JPanel(new BorderLayout());

        // Main panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font labelFont = new Font("Arial", Font.PLAIN, 14);

        // Panel asking for guest name
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblEnterName = new JLabel("Enter Guest Name: ");
        lblEnterName.setFont(labelFont);
        tfGuestNameField = new JTextField(20);
        namePanel.add(lblEnterName);
        namePanel.add(tfGuestNameField);
        formPanel.add(namePanel);

        // Hotel Panel
        JPanel hotelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblHotelName = new JLabel("Select Hotel Name: ");
        lblHotelName.setFont(labelFont);
        JLabel lblRoomType = new JLabel("Select Room Type: ");
        lblRoomType.setFont(labelFont);
        hotelPanel.add(lblHotelName);
        hotelPanel.add(cbHotels);
        hotelPanel.add(lblRoomType);
        hotelPanel.add(cbRoomTypes);
        formPanel.add(hotelPanel);

        // Date panel
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCheckInDate = new JLabel("Select Check-In Date: ");
        lblCheckInDate.setFont(labelFont);
        JLabel lblCheckOutDate = new JLabel("Select Check-Out Date: ");
        lblCheckOutDate.setFont(labelFont);
        datePanel.add(lblCheckInDate);
        datePanel.add(cbCheckIn);
        datePanel.add(lblCheckOutDate);
        datePanel.add(cbCheckOut);
        formPanel.add(datePanel);

        // Discount panel
        JPanel discountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblDiscountPrompt = new JLabel("Discount Code (Optional): ");
        lblDiscountPrompt.setFont(labelFont);
        tfDiscountCode = new JTextField(10);
        discountPanel.add(lblDiscountPrompt);
        discountPanel.add(tfDiscountCode);
        formPanel.add(discountPanel);

        // Search button panel
        JPanel searchButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        searchRoomsBtn = new JButton("Search for Rooms");
        searchButtonPanel.add(searchRoomsBtn);
        formPanel.add(searchButtonPanel);

        panel.add(formPanel, BorderLayout.NORTH);

        // Calendar/Date Availability Panel
        calendarPanel = new JPanel();
        calendarPanel.setLayout(new BoxLayout(calendarPanel, BoxLayout.Y_AXIS));
        datesAvailPanel = new JPanel();
        datesAvailPanel.setLayout(new BoxLayout(datesAvailPanel, BoxLayout.Y_AXIS));
        datesScroller = new JScrollPane(datesAvailPanel);
        datesScroller.setPreferredSize(new Dimension(400, 100));  // Adjusted size
        calendarPanel.add(datesScroller);
        calendarPanel.setVisible(false);
        panel.add(calendarPanel, BorderLayout.CENTER);

        // Next button panel
        JPanel nextButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nextBtn = new JButton("Next");
        nextButtonPanel.add(nextBtn);
        panel.add(nextButtonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * updates the calendar panel to show date availability of a chosen hotel
     * @param dates ArrayList of strings which contains their availability status
     */
    public void showDates(ArrayList<String> dates){
        //clearing if ever there were previous calls
        datesAvailPanel.removeAll();
        datesAvailPanel.repaint();
        datesAvailPanel.revalidate();
        //inputting the new data based on the user's chosen hotel and room types
        for(String line : dates){
            JLabel dateOcc = new JLabel(line);
            dateOcc.setFont(new Font("Arial", Font.PLAIN, 12));
            datesAvailPanel.add(dateOcc);
        }
        //refreshing the panel
        datesAvailPanel.repaint();
        datesAvailPanel.revalidate();

        calendarPanel.repaint();
        calendarPanel.revalidate();
        calendarPanel.setVisible(true);
    }

    /**
     * JPanel for confirming if entered booking details are correct
     * @return JPanel of booking details
     */
    public JPanel confirmPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Info panel (Based from entered input in prompt panel)
        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        infoPanel = infoPanel(); //instantiation of infopanel
        JScrollPane scrollDetails = new JScrollPane(infoPanel); //placing it in a scroll pane for longer data
        scrollDetails.setPreferredSize(new Dimension(400, 100));
        details.add(scrollDetails);
        panel.add(details);

        // Confirm prompt (contains choice of buttons weather to confirm or cancel booking)
        JPanel confirmBookPanel = new JPanel();
        confirmBookPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblConfirmBooking = new JLabel("Confirm?");
        lblConfirmBooking.setFont(new Font("Arial", Font.BOLD, 16));
        confirmBookPanel.add(lblConfirmBooking);
        confirmBookPanel.add(confirmBookBtn);
        confirmBookPanel.add(cancelBookBtn);
        panel.add(confirmBookPanel);

        return panel;
    }

    /**
     * Jpanel which contains the user's inputted information on the prompt Panel
     * @return JPanel summarizing the booking information
     */
    public JPanel infoPanel(){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        Font infoFont = new Font("Arial", Font.PLAIN, 14);

        //summarizing the entered booking details
        JLabel lblGuestName = new JLabel("Guest Name: " + tfGuestNameField.getText());
        lblGuestName.setFont(infoFont);
        infoPanel.add(lblGuestName);

        JLabel hotelName = new JLabel("Hotel Name: " + cbHotels.getSelectedItem());
        hotelName.setFont(infoFont);
        infoPanel.add(hotelName);

        JLabel roomType = new JLabel("Room Type: " + cbRoomTypes.getSelectedItem());
        roomType.setFont(infoFont);
        infoPanel.add(roomType);

        JLabel lblBreakdownHeader = new JLabel("Price Breakdown: ");
        lblBreakdownHeader.setFont(infoFont);
        infoPanel.add(lblBreakdownHeader);

        breakdownPanel = breakDownPanel(); //instantiation of the breakdownPanel
        infoPanel.add(breakdownPanel);

        return infoPanel;
    }

    /**
     * Updates the Price breakdown based on the booked room and dates from the user
     * @param breakList ArrayList of strings containing the computed per night
     */
    public void updateRoomBreakList(ArrayList<String> breakList){
        priceBreakdown.clear(); //clears any previous data
        for(String line : breakList){
            this.priceBreakdown.add(new JLabel(line)); //places it into an arraylist of JLabels
        }

        refreshConfirmPanel(); //will refresh the panel to show updated info
    }

    /**
     *
     * @return
     */
    public JPanel breakDownPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        for(JLabel label : priceBreakdown){
            panel.add(label);
        }
        return panel;
    }

    private void refreshConfirmPanel() {
        infoPanel.removeAll();
        infoPanel.add(infoPanel());
        infoPanel.revalidate();
        infoPanel.repaint();
        confirmPanel = confirmPanel();
        bookReservationCard.add(confirmPanel, "confirmPanel");
    }



    public void setActionListener(ActionListener actionListener) {
        cbHotels.addActionListener(actionListener);
        cbRoomTypes.addActionListener(actionListener);
        cbCheckIn.addActionListener(actionListener);
        cbCheckOut.addActionListener(actionListener);
        searchRoomsBtn.addActionListener(actionListener);
        nextBtn.addActionListener(actionListener);
        cancelBookBtn.addActionListener(actionListener);
        confirmBookBtn.addActionListener(actionListener);
    }
    public void setDocumentListener(DocumentListener documentListener) {
        tfGuestNameField.getDocument().addDocumentListener(documentListener);
        tfDiscountCode.getDocument().addDocumentListener(documentListener);
    }

    public CardLayout getBookReservationCardLayout() {
        return bookReservationCardLayout;
    }
    public JPanel getBookReservationCard() {
        return bookReservationCard;
    }
    public JComboBox<String> getCbHotels(){
        return cbHotels;
    }
    public JComboBox<String> getCbRoomTypes(){
        return cbRoomTypes;
    }
    public JComboBox<Integer> getCbCheckIn(){
        return cbCheckIn;
    }
    public JComboBox<Integer> getCbCheckOut(){
        return cbCheckOut;
    }
    public int getRoomIndex() {
        return roomIndex;
    }
    public void setRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
    }
    public String getTfGuestName() {
        return tfGuestNameField.getText();
    }
    public String getTfDiscountCode() {
        return tfDiscountCode.getText();
    }
    public void setTfGuestName(String tfGuestName) {
        this.tfGuestNameField.setText(tfGuestName);
    }
    public void setTfDiscountCode(String tfDiscountCode) {
        this.tfDiscountCode.setText(tfDiscountCode);
    }

    public void setRoomName(String roomName) {
        this.roomName.setText("Your room is: " + roomName);
    }
    public JPanel getCalendarPanel(){
        return calendarPanel;
    }
}
