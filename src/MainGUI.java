import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class MainGUI extends JFrame {
    private JButton createHotelBtn;
    private JButton viewHotelBtn;
    private JButton manageHotelBtn;
    private JButton bookReservationBtn;
    private JButton exitBtn;
    private JButton backButton;
    private JButton addHotelBtn;
    private JButton searchHotelBtn;
    private JTextField tfHotelName;
    private JComboBox cbHotelNames;
    public ArrayList<String> hotelNames = new ArrayList<String>();

    private JPanel createPanel;


    private CardLayout mainCardLayout;
    private JPanel mainCardPanel;

    private ViewHotelPanel viewHotelPanel;
    private ManageHotelPanel manageHotelPanel;
    private BookReservationPanel bookReservationPanel;

    private JOptionPane errorMessage;
    private JOptionPane confirmMessage;

    private int currView;

    public MainGUI(){
        super("Hotel Reservation");
        setLayout(new BorderLayout());

        setSize(600, 450);
        init();
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init(){
        setTitle("Hotel Reservation");
        cbHotelNames = new JComboBox<>();
        cbHotelNames.setEnabled(true);
        currView = 0;

        JPanel bottomMenu = bottomMenuPanel();


        mainCardLayout = new CardLayout();
        mainCardPanel = new JPanel(mainCardLayout);
        mainCardPanel.setSize(450, 300);

        JPanel mainView = mainView();
        createPanel = createHotelPanel();
        viewHotelPanel = new ViewHotelPanel();
        manageHotelPanel = new ManageHotelPanel();
        bookReservationPanel = new BookReservationPanel();
        JPanel searchHotelPanel = searchHotelPanel();

        mainCardPanel.add(mainView, "main");
        mainCardPanel.add(createPanel, "create");
        mainCardPanel.add(viewHotelPanel, "viewHotel");
        mainCardPanel.add(manageHotelPanel, "manageHotel");
        mainCardPanel.add(bookReservationPanel, "bookReservation");
        mainCardPanel.add(searchHotelPanel, "searchHotel");


        mainCardLayout.show(mainCardPanel, "main");
        this.add(mainCardPanel, BorderLayout.CENTER);
        this.add(bottomMenu, BorderLayout.SOUTH);


    }

    public JPanel bottomMenuPanel(){
        JPanel bottomMenu = new JPanel();
        bottomMenu.setLayout(new FlowLayout()); // Reduced horizontal gap to 10
        bottomMenu.setBackground(Color.decode("#A9927D"));

        createHotelBtn = new JButton("Create Hotel");
        viewHotelBtn = new JButton("View Hotel");
        manageHotelBtn = new JButton("Manage Hotel");
        bookReservationBtn = new JButton("Book Reservation");
        exitBtn = new JButton("Shutdown");

        // Set button sizes and add to panel
        Dimension buttonSize = new Dimension(115, 40); // Adjust size as needed
        createHotelBtn.setPreferredSize(buttonSize);
        viewHotelBtn.setPreferredSize(buttonSize);
        manageHotelBtn.setPreferredSize(buttonSize);
        bookReservationBtn.setPreferredSize(buttonSize);
        exitBtn.setPreferredSize(buttonSize);

        bottomMenu.add(createHotelBtn);
        bottomMenu.add(viewHotelBtn);
        bottomMenu.add(manageHotelBtn);
        bottomMenu.add(bookReservationBtn);
        bottomMenu.add(exitBtn);

        return bottomMenu;
    }



    public JPanel mainView(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Center panel with BoxLayout for vertical alignment
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create labels
        JLabel lblWelcome = new JLabel("Welcome!");
        JLabel lblHRS = new JLabel("Hotel Reservation System v1.0");
        JLabel authors = new JLabel("by Cumti & Escano");

        setFontAndStyle(lblWelcome);
        setFontAndStyle(lblHRS);
        setFontAndStyle(lblHRS);

        // Center the labels horizontally
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHRS.setAlignmentX(Component.CENTER_ALIGNMENT);
        authors.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add vertical glue to center labels vertically in the panelCenter
        panelCenter.add(Box.createVerticalGlue());
        panelCenter.add(lblWelcome);
        panelCenter.add(Box.createVerticalStrut(10));
        panelCenter.add(lblHRS);
        panelCenter.add(Box.createVerticalStrut(10));
        panelCenter.add(authors);
        panelCenter.add(Box.createVerticalGlue());

        // Add the centered panelCenter to the mainPanel
        mainPanel.add(panelCenter, BorderLayout.CENTER);

        return mainPanel;
    }

    public JPanel createHotelPanel(){
        JPanel createPanel = new JPanel();
        createPanel.setLayout(new BorderLayout());

        // North panel with the label
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblHotelName = new JLabel("Create New Hotel");
        lblHotelName.setFont(new Font("Arial", Font.BOLD, 24));
        lblHotelName.setHorizontalAlignment(SwingConstants.CENTER);
        panelNorth.add(lblHotelName);
        createPanel.add(panelNorth, BorderLayout.NORTH);

        // Center panel with text field and button
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); // Align text field to the left
        JLabel lblWelcome = new JLabel("Enter Hotel Name: ");
        setFontAndStyle(lblWelcome);
        tfHotelName = new JTextField(20);
        detailsPanel.add(lblWelcome);
        detailsPanel.add(tfHotelName);

        addHotelBtn = new JButton("Add Hotel");

        // Add components to panelCenter
        panelCenter.add(detailsPanel);
        panelCenter.add(Box.createVerticalStrut(10)); // Space between text field and button
        panelCenter.add(addHotelBtn);

        // Center panel
        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.add(panelCenter, BorderLayout.CENTER);
        createPanel.add(centerContainer, BorderLayout.CENTER);

        // South panel with back button
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
        backButton = new JButton("Back");
        panelSouth.add(backButton);
        createPanel.add(panelSouth, BorderLayout.SOUTH);

        return createPanel;

    }


    public JPanel searchHotelPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSearchName = new JLabel("Select Hotel: ");
        lblSearchName.setFont(new Font("Arial", Font.BOLD, 24));
        lblSearchName.setHorizontalAlignment(SwingConstants.CENTER);
        searchHotelBtn = new JButton("Search Hotel");

        panelCenter.add(lblSearchName);
        panelCenter.add(Box.createVerticalStrut(10));
        panelCenter.add(cbHotelNames);
        panelCenter.add(Box.createVerticalStrut(10));
        panelCenter.add(searchHotelBtn);

        panel.add(panelCenter, BorderLayout.CENTER);

        return panel;
    }

    // Helper method to center align labels
    private void setFontAndStyle(JLabel label) {
        label.setFont(new Font("Arial", Font.PLAIN, 16)); // Apply font style
        label.setAlignmentX(Component.CENTER_ALIGNMENT); // Center alignment
    }



    public void setActionListener(ActionListener listener){
        createHotelBtn.addActionListener(listener);
        viewHotelBtn.addActionListener(listener);
        manageHotelBtn.addActionListener(listener);
        bookReservationBtn.addActionListener(listener);
        exitBtn.addActionListener(listener);
        backButton.addActionListener(listener);
        addHotelBtn.addActionListener(listener);
        searchHotelBtn.addActionListener(listener);
        cbHotelNames.addActionListener(listener);
        viewHotelPanel.setActionListener(listener);
        manageHotelPanel.setActionListener(listener);
        bookReservationPanel.setActionListener(listener);
    }

    public void setDocumentListener(DocumentListener listener) {
        tfHotelName.getDocument().addDocumentListener(listener);
    }

    public ViewHotelPanel getViewHotelPanel() {
        return viewHotelPanel;
    }
    public ManageHotelPanel getManageHotelPanel() {
        return manageHotelPanel;
    }
    public BookReservationPanel getBookReservationPanel() {
        return bookReservationPanel;
    }

    public void setItemListener(ItemListener listener) {
        cbHotelNames.addItemListener(listener);
    }

    public String getHotelName(){
        return tfHotelName.getText();
    }

    public CardLayout getMainCardLayout() {
        return mainCardLayout;
    }
    public JPanel getMainCardPanel() {
        return mainCardPanel;
    }

    public ArrayList<String> getHotelNames() {
        return hotelNames;
    }

    public int getCurrView() {
        return currView;
    }
    public void setCurrView(int currView) {
        this.currView = currView;
    }
    public JComboBox getCbHotelNames(){
        return cbHotelNames;
    }

    public void setErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public void setConfirmMessage(String confirmMessage) {
        JOptionPane.showMessageDialog(this, confirmMessage, "Confirm", JOptionPane.INFORMATION_MESSAGE);
    }
}
