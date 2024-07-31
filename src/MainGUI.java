import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * Represents the Main GUI class of the program
 */
public class MainGUI extends JFrame {
    /**
     * button to access createHotel panel
     */
    private JButton createHotelBtn;
    /**
     * button to access viewHotel panel
     */
    private JButton viewHotelBtn;
    /**
     * button to access manage hotel panel
     */
    private JButton manageHotelBtn;
    /**
     * button to access bookReservation panel
     */
    private JButton bookReservationBtn;
    /**
     * button to exit the program
     */
    private JButton exitBtn;
    /**
     * button to return to the main panel
     */
    private JButton backButton;
    /**
     * button to create a new hotel
     */
    private JButton addHotelBtn;
    /**
     * button to search for a current hotel
     */
    private JButton searchHotelBtn;
    /**
     * text field for creating a new hotel
     */
    private JTextField tfHotelName;
    /**
     * JComboBox of options to select current hotels
     */
    private JComboBox cbHotelNames;
    /**
     * list of strings that contain the hotel names
     */
    public ArrayList<String> hotelNames = new ArrayList<String>();

    /**
     * panel for creating a new hotel
     */
    private JPanel createPanel;

    /**
     * cardlayout for the class
     */
    private CardLayout mainCardLayout;
    /**
     * main panel for the class
     */
    private JPanel mainCardPanel;
    /**
     * panel to show viewHotel
     */
    private ViewHotelPanel viewHotelPanel;
    /**
     * panel to show manageHotel
     */
    private ManageHotelPanel manageHotelPanel;
    /**
     * panel to show bookReservation
     */
    private BookReservationPanel bookReservationPanel;

    /**
     * option pane for errors
     */
    private JOptionPane errorMessage;
    /**
     * option pane for confirmations
     */
    private JOptionPane confirmMessage;

    /**
     * stores which card is currently being shown in the GUI
     */
    private int currView;

    /**
     * Creates the MainGUI class
     */
    public MainGUI(){
        super("Hotel Reservation");
        setLayout(new BorderLayout());

        setSize(600, 450);
        init();
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * initialization method
     */
    public void init(){
        setTitle("Hotel Reservation");
        cbHotelNames = new JComboBox<>();
        cbHotelNames.setEnabled(true);
        currView = 0;
        //for bottom menu of panles
        JPanel bottomMenu = bottomMenuPanel();

        //card initialization
        mainCardLayout = new CardLayout();
        mainCardPanel = new JPanel(mainCardLayout);
        mainCardPanel.setSize(450, 300);

        //initialization of panels
        JPanel mainView = mainView();
        createPanel = createHotelPanel();
        viewHotelPanel = new ViewHotelPanel();
        manageHotelPanel = new ManageHotelPanel();
        bookReservationPanel = new BookReservationPanel();
        JPanel searchHotelPanel = searchHotelPanel();

        //adding panels to cardlayout
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

    /**
     * Creates the menu panel at the bottom of the screen
     * @return JPannel of the bottom menu
     */
    public JPanel bottomMenuPanel(){
        JPanel bottomMenu = new JPanel();
        bottomMenu.setLayout(new FlowLayout());
        bottomMenu.setBackground(Color.decode("#A9927D"));

        //button options for viewing the other panels
        createHotelBtn = new JButton("Create Hotel");
        viewHotelBtn = new JButton("View Hotel");
        manageHotelBtn = new JButton("Manage Hotel");
        bookReservationBtn = new JButton("Book Reservation");
        exitBtn = new JButton("Shutdown");

        //designing panel
        Dimension buttonSize = new Dimension(115, 40);
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

    /**
     * Creates the starting panel of the program
     * @return JPanel of the welcome panel
     */
    public JPanel mainView(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        //center panel
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.setAlignmentX(Component.CENTER_ALIGNMENT);

        // label initialization
        JLabel lblWelcome = new JLabel("Welcome!");
        JLabel lblHRS = new JLabel("Hotel Reservation System v1.0");
        JLabel authors = new JLabel("by Cumti & Escano");

        setFontAndStyle(lblWelcome);
        setFontAndStyle(lblHRS);
        setFontAndStyle(lblHRS);

        //alignment of labels
        lblWelcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblHRS.setAlignmentX(Component.CENTER_ALIGNMENT);
        authors.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelCenter.add(Box.createVerticalGlue());
        panelCenter.add(lblWelcome);
        panelCenter.add(Box.createVerticalStrut(10));
        panelCenter.add(lblHRS);
        panelCenter.add(Box.createVerticalStrut(10));
        panelCenter.add(authors);
        panelCenter.add(Box.createVerticalGlue());

        mainPanel.add(panelCenter, BorderLayout.CENTER);

        return mainPanel;
    }

    /**
     * Creates a createHotelPanel for adding a hotel
     * @return JPanel containing options of adding new hotels to the system
     */
    public JPanel createHotelPanel(){
        JPanel createPanel = new JPanel();
        createPanel.setLayout(new BorderLayout());

        // North panel
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel lblHotelName = new JLabel("Create New Hotel");
        lblHotelName.setFont(new Font("Arial", Font.BOLD, 24));
        lblHotelName.setHorizontalAlignment(SwingConstants.CENTER);
        panelNorth.add(lblHotelName);
        createPanel.add(panelNorth, BorderLayout.NORTH);

        // center panel
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));
        panelCenter.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        //prompt panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel lblWelcome = new JLabel("Enter Hotel Name: ");
        setFontAndStyle(lblWelcome);
        tfHotelName = new JTextField(20);
        detailsPanel.add(lblWelcome);
        detailsPanel.add(tfHotelName);

        addHotelBtn = new JButton("Add Hotel");

        panelCenter.add(detailsPanel);
        panelCenter.add(Box.createVerticalStrut(10));
        panelCenter.add(addHotelBtn);

        JPanel centerContainer = new JPanel(new BorderLayout());
        centerContainer.add(panelCenter, BorderLayout.CENTER);
        createPanel.add(centerContainer, BorderLayout.CENTER);

        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
        backButton = new JButton("Back");
        panelSouth.add(backButton);
        createPanel.add(panelSouth, BorderLayout.SOUTH);

        return createPanel;

    }

    /**
     * Creates a panel for searching a current hotel
     * @return JPanel to search hotels
     */
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

        //list of hotels
        panelCenter.add(lblSearchName);
        panelCenter.add(Box.createVerticalStrut(10));
        panelCenter.add(cbHotelNames);
        panelCenter.add(Box.createVerticalStrut(10));
        panelCenter.add(searchHotelBtn);

        panel.add(panelCenter, BorderLayout.CENTER);

        return panel;
    }

    /**
     * Sets the alignment and font of a jLabel
     * @param label label to be edited
     */
    private void setFontAndStyle(JLabel label) {
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Action Listener Method
     * @param listener listener of performed action
     */
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

    /**
     * Document Listener for textfields
     * @param listener for text field
     */
    public void setDocumentListener(DocumentListener listener) {
        tfHotelName.getDocument().addDocumentListener(listener);
    }

    /**
     * Getter method for viewHotelPanel
     * @return viewHotelPanel
     */
    public ViewHotelPanel getViewHotelPanel() {
        return viewHotelPanel;
    }
    /**
     * Getter method for manageHotelPanel
     * @return manageHotelPanel
     */
    public ManageHotelPanel getManageHotelPanel() {
        return manageHotelPanel;
    }
    /**
     * Getter method for bookReservationPanel
     * @return bookReservationPanel
     */
    public BookReservationPanel getBookReservationPanel() {
        return bookReservationPanel;
    }

    /**
     * Getter method for tfHotel name as string
     * @return String value of tfHotelName
     */
    public String getHotelName(){
        return tfHotelName.getText();
    }

    /**
     * Getter method of mainCardLayout
     * @return mainCardLayout
     */
    public CardLayout getMainCardLayout() {
        return mainCardLayout;
    }
    /**
     * Getter method of mainCardPanel
     * @return mainCardPanel
     */
    public JPanel getMainCardPanel() {
        return mainCardPanel;
    }

    /**
     * Getter method for hotelNames
     * @return hotelNames
     */
    public ArrayList<String> getHotelNames() {
        return hotelNames;
    }

    /**
     * Getter method for getting current view
     * @return currView
     */
    public int getCurrView() {
        return currView;
    }

    /**
     * Setter method for current view
     * @param currView new view
     */
    public void setCurrView(int currView) {
        this.currView = currView;
    }
    /**
     * Getter method for cbHotelNames
     * @return cbHotelNames
     */
    public JComboBox getCbHotelNames(){
        return cbHotelNames;
    }

    /**
     * Setter method for JOption page for errors
     * @param errorMessage error message based on error
     */
    public void setErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Setter method for JOption page for confirmations
     * @param confirmMessage confirming successful action of program
     */
    public void setConfirmMessage(String confirmMessage) {
        JOptionPane.showMessageDialog(this, confirmMessage, "Confirm", JOptionPane.INFORMATION_MESSAGE);
    }
}
