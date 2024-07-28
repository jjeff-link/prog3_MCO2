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
    private JPanel bookedDetailsPanel;
    JPanel breakdownPanel;
    private JPanel infoPanel;

    private JTextField tfGuestNameField;
    private JComboBox<String> cbHotels;
    private JComboBox<String> cbRoomTypes;
    private JComboBox<Integer> cbCheckIn;
    private JComboBox<Integer> cbCheckOut;
    private JButton searchRoomsBtn;
    private JButton nextBtn;

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


        JLabel lblBookReservation = new JLabel("Book Reservation");
        this.add(lblBookReservation, BorderLayout.NORTH);

        bookReservationCardLayout = new CardLayout();
        bookReservationCard = new JPanel(bookReservationCardLayout);

        promptPanel = promptPanel();
        confirmPanel = confirmPanel();
        bookedDetailsPanel = bookedDetailsPanel();
        infoPanel = infoPanel();
        breakdownPanel = breakDownPanel();

        bookReservationCard.add(promptPanel, "promptPanel");
        bookReservationCard.add(confirmPanel, "confirmPanel");
        bookReservationCard.add(bookedDetailsPanel, "bookedDetailsPanel");


       bookReservationCardLayout.show(bookReservationCard, "promptPanel");
        this.add(bookReservationCard, BorderLayout.CENTER);

    }


    public JPanel promptPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //namePanel
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JLabel lblEnterName = new JLabel("Enter Guest Name: ");
        namePanel.add(lblEnterName);
        tfGuestNameField = new JTextField(20);
        namePanel.add(tfGuestNameField);
        panel.add(namePanel);

        //hotelPanel
        JPanel hotelPanel = new JPanel();
        hotelPanel.setLayout(new FlowLayout());
        JLabel lblHotelName = new JLabel("Select Hotel Name ");
        hotelPanel.add(lblHotelName);
        hotelPanel.add(cbHotels);
        JLabel lblRoomType = new JLabel("Select Room Type ");
        hotelPanel.add(lblRoomType);
        hotelPanel.add(cbRoomTypes);
        searchRoomsBtn = new JButton("Search for Rooms");
        hotelPanel.add(searchRoomsBtn);
        panel.add(hotelPanel);

        //calendar Panel

        //datesPanel
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());
        JLabel lblCheckInDate = new JLabel("Select Check In Date ");
        datePanel.add(lblCheckInDate);
        datePanel.add(cbCheckIn);
        JLabel lblCheckOutDate = new JLabel("Select Check Out Date ");
        datePanel.add(lblCheckOutDate);
        datePanel.add(cbCheckOut);
        panel.add(datePanel);

        //discount prompt
        JPanel discountPanel = new JPanel();
        discountPanel.setLayout(new FlowLayout());
        JLabel lblDiscountPrompt = new JLabel("Discount Code (Optional): ");
        discountPanel.add(lblDiscountPrompt);
        tfDiscountCode = new JTextField(10);
        discountPanel.add(tfDiscountCode);
        panel.add(discountPanel);

        nextBtn = new JButton("Next");
        panel.add(nextBtn);

        return panel;
    }

    public JPanel confirmPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //info panel
        JPanel infoPanel = infoPanel();
        panel.add(infoPanel);

        //confirmPrompt
        JPanel confirmBookPanel = new JPanel();
        confirmBookPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblConfirmBooking = new JLabel("Confirm?");
        confirmBookPanel.add(lblConfirmBooking);
        confirmBookBtn = new JButton("Confirm Booking");
        confirmBookPanel.add(confirmBookBtn);
        cancelBookBtn = new JButton("Cancel Booking");
        confirmBookPanel.add(cancelBookBtn);
        panel.add(confirmBookPanel);


        return panel;
    }

    public JPanel bookedDetailsPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblSuccess = new JLabel("Booking Successful!");
        panel.add(lblSuccess);

        //details
        JPanel infoPanel = infoPanel();
        panel.add(infoPanel);
        panel.add(roomName);

        returnBtn = new JButton("Return to Main");
        panel.add(returnBtn);


        return panel;
    }

    public JPanel infoPanel(){
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        JLabel lblGuestName  = new JLabel("Guest Name: " + tfGuestNameField.getText());
        infoPanel.add(lblGuestName);
        JLabel hotelName = new JLabel("Hotel Name: " + cbHotels.getSelectedItem());
        infoPanel.add(hotelName);
        JLabel roomType = new JLabel("Room Type: " + cbRoomTypes.getSelectedItem());
        infoPanel.add(roomType);
        JLabel lblBreakdownHeader = new JLabel("Price Breakdown: ");
        infoPanel.add(lblBreakdownHeader);
        breakdownPanel = breakDownPanel();
        infoPanel.add(breakdownPanel);

        return infoPanel;
    }




    public void updateRoomBreakList(ArrayList<String> breakList){
        priceBreakdown.clear();
        for(String line : breakList){
            System.out.println(line);
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
        confirmPanel.remove(infoPanel);
        confirmPanel.add(infoPanel());
        confirmPanel.revalidate();
        confirmPanel.repaint();
    }



    public void setActionListener(ActionListener actionListener) {
        cbHotels.addActionListener(actionListener);
        cbRoomTypes.addActionListener(actionListener);
        cbCheckIn.addActionListener(actionListener);
        cbCheckOut.addActionListener(actionListener);
        searchRoomsBtn.addActionListener(actionListener);
        nextBtn.addActionListener(actionListener);
        cancelBookBtn.addActionListener(actionListener);
        returnBtn.addActionListener(actionListener);
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

    public void setRoomName(String roomName) {
        this.roomName.setText("Your room is: " + roomName);
    }
}
