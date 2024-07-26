import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class BookReservationPanel extends JPanel {


    CardLayout bookReservationCardLayout = new CardLayout();
    JPanel bookReservationCard = new JPanel();

    JTextField tfGuestNameField;
    JComboBox<String> cbHotels;
    JComboBox<String> cbRoomTypes;
    JComboBox<Integer> cbCheckIn;
    JComboBox<Integer> cbCheckOut;
    JButton searchRoomsBtn;
    JButton nextBtn;

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


        JLabel lblBookReservation = new JLabel("Book Reservation");
        this.add(lblBookReservation, BorderLayout.NORTH);

        bookReservationCardLayout = new CardLayout();
        bookReservationCard = new JPanel(bookReservationCardLayout);

        JPanel promptPanel = promptPanel();

        bookReservationCard.add(promptPanel, "promptPanel");


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

        nextBtn = new JButton("Next");
        panel.add(nextBtn);

        return panel;
    }

    public JPanel confirmPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        return panel;
    }

    public void setActionListener(ActionListener actionListener) {
        cbHotels.addActionListener(actionListener);
        cbRoomTypes.addActionListener(actionListener);
        cbCheckIn.addActionListener(actionListener);
        cbCheckOut.addActionListener(actionListener);
        searchRoomsBtn.addActionListener(actionListener);
        nextBtn.addActionListener(actionListener);
    }
    public void setDocumentListener(DocumentListener documentListener) {
        tfGuestNameField.getDocument().addDocumentListener(documentListener);
    }

    public CardLayout getBookReservationCardLayout() {
        return bookReservationCardLayout;
    }
    public JPanel getBookReservationCard() {
        return bookReservationCard;
    }


}
