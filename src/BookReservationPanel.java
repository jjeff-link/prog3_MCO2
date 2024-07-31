import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BookReservationPanel extends JPanel {


    private CardLayout bookReservationCardLayout = new CardLayout();
    private JPanel bookReservationCard = new JPanel();

    private JPanel promptPanel;
    private JPanel confirmPanel;
    JPanel breakdownPanel;
    private JPanel infoPanel;

    private JTextField tfGuestNameField;
    private JComboBox<String> cbHotels;
    private JComboBox<String> cbRoomTypes;
    private JComboBox<Integer> cbCheckIn;
    private JComboBox<Integer> cbCheckOut;
    private JButton searchRoomsBtn;
    private JButton nextBtn;
    private JPanel calendarPanel;
    private JPanel datesAvailPanel;
    private JScrollPane datesScroller;

    private int roomIndex;
    private ArrayList<JLabel> priceBreakdown;
    private JTextField tfDiscountCode;
    private JButton confirmBookBtn;
    private JButton cancelBookBtn;

    private JLabel roomName;
    private JButton returnBtn;


    public BookReservationPanel() {
        setLayout(new BorderLayout());

        init();
        setVisible(true);
    }



    public void init() {
        String[] roomTypes = {"Standard", "Deluxe", "Executive"};
        Integer[] checkInDates = new Integer[30];
        Integer[] checkOutDates = new Integer[30];
        for(int i = 1; i <= 31; i++) {
            if(i < 31)
                checkInDates[i - 1] = i;
            if(i > 1)
                checkOutDates[i - 2] = i;
        }
        cbHotels = new JComboBox<String>();
        cbRoomTypes = new JComboBox<String>(roomTypes);
        cbCheckIn = new JComboBox<Integer>(checkInDates);
        cbCheckOut = new JComboBox<Integer>(checkOutDates);
        priceBreakdown = new ArrayList<JLabel>();
        roomName = new JLabel();
        confirmBookBtn = new JButton("Confirm Booking");
        cancelBookBtn = new JButton("Cancel Booking");


        JLabel lblBookReservation = new JLabel("Book Reservation");
        lblBookReservation.setHorizontalAlignment(SwingConstants.CENTER);
        lblBookReservation.setFont(new Font("Arial", Font.BOLD, 24));
        this.add(lblBookReservation, BorderLayout.NORTH);

        bookReservationCardLayout = new CardLayout();
        bookReservationCard = new JPanel(bookReservationCardLayout);

        promptPanel = promptPanel();
        infoPanel = infoPanel();
        breakdownPanel = breakDownPanel();

        bookReservationCard.add(promptPanel, "promptPanel");


       bookReservationCardLayout.show(bookReservationCard, "promptPanel");
        this.add(bookReservationCard, BorderLayout.CENTER);

    }


    public JPanel promptPanel(){
        JPanel panel = new JPanel(new BorderLayout());

        // Main form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Font labelFont = new Font("Arial", Font.PLAIN, 14);

        // Name panel
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblEnterName = new JLabel("Enter Guest Name: ");
        lblEnterName.setFont(labelFont);
        tfGuestNameField = new JTextField(20);
        namePanel.add(lblEnterName);
        namePanel.add(tfGuestNameField);
        formPanel.add(namePanel);

        // Hotel panel
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

        // Calendar panel
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

    public void showDates(ArrayList<String> dates){
        datesAvailPanel.removeAll();
        datesAvailPanel.repaint();
        datesAvailPanel.revalidate();
        for(String line : dates){
            JLabel dateOcc = new JLabel(line);
            dateOcc.setFont(new Font("Arial", Font.PLAIN, 12));
            datesAvailPanel.add(dateOcc);
        }

        datesAvailPanel.repaint();
        datesAvailPanel.revalidate();

        calendarPanel.repaint();
        calendarPanel.revalidate();
        calendarPanel.setVisible(true);
    }

    public JPanel confirmPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Info panel
        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.Y_AXIS));
        infoPanel = infoPanel();
        JScrollPane scrollDetails = new JScrollPane(infoPanel);
        scrollDetails.setPreferredSize(new Dimension(400, 100));
        details.add(scrollDetails);
        panel.add(details);

        // Confirm prompt
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

    public JPanel infoPanel(){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        Font infoFont = new Font("Arial", Font.PLAIN, 14);
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
        breakdownPanel = breakDownPanel();
        infoPanel.add(breakdownPanel);

        return infoPanel;
    }




    public void updateRoomBreakList(ArrayList<String> breakList){
        priceBreakdown.clear();
        for(String line : breakList){
            this.priceBreakdown.add(new JLabel(line));
        }

        refreshConfirmPanel();
    }


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
