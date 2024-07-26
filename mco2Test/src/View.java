import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class View extends JFrame {
    JButton createHotelBtn;
    JButton viewHotelBtn;
    JButton manageHotelBtn;
    JButton bookReservationBtn;
    JButton backButton;
    JButton addHotelBtn;
    JButton searchHotelBtn;
    private JTextField tfHotelName;
    JComboBox cbHotelNames;
    public ArrayList<String> hotelNames = new ArrayList<String>();


    private CardLayout mainCardLayout;
    private JPanel mainCardPanel;

    private ViewHotelPanel viewHotelPanel;
    private ManageHotelPanel manageHotelPanel;
    private BookReservationPanel bookReservationPanel;


    private int currView;

    public View(){
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
        JPanel createPanel = createHotelPanel();
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
        bottomMenu.setLayout(new FlowLayout());
        bottomMenu.setBackground(Color.decode("#A9927D"));


        createHotelBtn = new JButton("Create Hotel");
        viewHotelBtn = new JButton("View Hotel");
        manageHotelBtn = new JButton("Manage Hotel");
        bookReservationBtn = new JButton("Book Reservation");

        bottomMenu.add(createHotelBtn);
        bottomMenu.add(viewHotelBtn);
        bottomMenu.add(manageHotelBtn);
        bottomMenu.add(bookReservationBtn);


        return bottomMenu;
    }



    public JPanel mainView(){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));


        JLabel lblWelcome = new JLabel("Welcome!");
        mainPanel.add(lblWelcome);

        JLabel lblHRS = new JLabel("Hotel Reservation System v1.0");
        mainPanel.add(lblHRS);

        JLabel authors = new JLabel("by Cumti & Escano");
        mainPanel.add(authors);



        //center Panel
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));


        mainPanel.add(panelCenter, BorderLayout.CENTER);
        return mainPanel;

    }

    public JPanel createHotelPanel(){
        JPanel createPanel = new JPanel();
        createPanel.setLayout(new BorderLayout());

        //NorthPanel
        JPanel panelNorth = new JPanel();
        panelNorth.setLayout(new FlowLayout());
        JLabel lblHotelName = new JLabel("Create New Hotel");
        panelNorth.add(lblHotelName);

        createPanel.add(panelNorth, BorderLayout.NORTH);

        //CenterPanel
        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new FlowLayout());
        JLabel lblWelcome = new JLabel("Enter Hotel Name: ");
        detailsPanel.add(lblWelcome);
        tfHotelName = new JTextField(20);
        detailsPanel.add(tfHotelName);

        addHotelBtn = new JButton("Add Hotel");

        panelCenter.add(detailsPanel);
        panelCenter.add(addHotelBtn);

        createPanel.add(panelCenter, BorderLayout.CENTER);


        //SouthPanel
        JPanel panelSouth = new JPanel();
        panelSouth.setLayout(new FlowLayout());
        backButton = new JButton("Back");
        panelSouth.add(backButton);
        createPanel.add(panelSouth, BorderLayout.SOUTH);


        return createPanel;
    }


    public JPanel searchHotelPanel(){
        JPanel panel = new JPanel();
        setLayout(new BorderLayout());

        JPanel panelCenter = new JPanel();
        panelCenter.setLayout(new BoxLayout(panelCenter, BoxLayout.Y_AXIS));

        JLabel lblSearchName = new JLabel("Select Hotel: ");
        panelCenter.add(lblSearchName);
        panelCenter.add(cbHotelNames);

        searchHotelBtn = new JButton("Search Hotel");
        panelCenter.add(searchHotelBtn);

        panel.add(panelCenter, BorderLayout.CENTER);

        return panel;
    }




    public void setActionListener(ActionListener listener){
        createHotelBtn.addActionListener(listener);
        viewHotelBtn.addActionListener(listener);
        manageHotelBtn.addActionListener(listener);
        bookReservationBtn.addActionListener(listener);
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
}
