import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Represents the GUI for Hotel Management options
 */
public class ManageHotelPanel extends JPanel {
    /**
     * main card panel of the class
     */
    private JPanel managePanel = new JPanel();
    /**
     * cardlayout of the mainPanel
     */
    private CardLayout manageCardLayout = new CardLayout();
    /**
     * JPanel containing managing options
     */
    private JPanel manageOptionPanel;
    /**
     * panel to change hotel name
     */
    private JPanel changeNamePanel;
    /**
     * panel to add rooms to chosen hotel
     */
    private JPanel addRoomsPanel;
    /**
     * panel to remove rooms to chosen hotel
     */
    private JPanel removeRoomsPanel;
    /**
     * panel to update the base price of rooms of a chosen hotel
     */
    private JPanel updatePricePanel;
    /**
     * panel to update the date-price modifier of a chosen hotel
     */
    private JPanel updatePriceModPanel;
    /**
     * panel to remove a reservation
     */
    private JPanel removeRsrvPanel;
    /**
     * panel to remove a hotel
     */
    private JPanel removeHotelPanel;

    /**
     * button to access changeNamePanel
     */
    private JButton changeNameOptBtn;
    /**
     * button to access addRoomsPanel
     */
    private JButton addRoomOptBtn;
    /**
     * button to removeRoomsPanel
     */
    private JButton removeRoomOptBtn;
    /**
     * button to access updatePricePanel
     */
    private JButton updatePriceOptBtn;
    /**
     * button to access updatePriceModPanel
     */
    private JButton updateModOptBtn;
    /**
     * button to access removeRsrvPanel
     */
    private JButton removeRsrvOptBtn;
    /**
     * button to access removeHotelPanel
     */
    private JButton removeHotelOptBtn;

    /**
     * button for changing hotel name
     */
    private JButton changeBtn;
    /**
     * textfield for the new hotel name
     */
    private JTextField tfNewName;

    /**
     * room type to add
     */
    private JComboBox<String> cbRoomTypeAdd;
    /**
     * number of rooms to add
     */
    private JComboBox<Integer> cbRmToAdd;
    /**
     * button to add the entered rooms
     */
    private JButton confirmAddBtn;

    /**
     * types of rooms to remove
     */
    private JComboBox<String> cbRoomTypeRemove;
    /**
     * number of rooms to remove
     */
    private JComboBox<Integer> cbRmToRemove;
    /**
     * button to select type of room to remove
     */
    private JButton selectRoomRemoveBtn;
    /**
     * button to confirm removal of rooms
     */
    private JButton confirmRemoveBtn;

    /**
     * text field to enter new price
     */
    private JTextField tfNewPrice;
    /**
     * button to change price
     */
    private JButton confirmPriceBtn;

    /**
     * start date to change date-price modifier
     */
    private JComboBox<Integer> cbStartDateMod;
    /**
     * end date to change date-price modifier
     */
    private JComboBox<Integer> cbEndDateMod;
    /**
     * text field to enter new modifier
     */
    private JTextField tfNewPriceMod;
    /**
     * button to confirm change
     */
    private JButton confirmPriceModBtn;
    /**
     * panel which checks and shows for the reservation before removing
     */
    private JPanel rsrvInfoPanel;
    /**
     * text field to find guest name of a reservation
     */
    private JTextField tfGuestName;
    /**
     * button to search guest's reservation
     */
    private JButton searchRsrvBtn;
    /**
     * arraylist of JLabels which stores booking information of a guest
     */
    private ArrayList<JLabel> lblInfoList;
    /**
     * button to confirm the removal of a reservation
     */
    private JButton confirmRemoveRsrvBtn;
    /**
     * button to return to main panel
     */
    private JButton returnToHomeBtn;
    /**
     * panel which asks the user for confirmation in removing a hotel
     */
    private JPanel hotelRemovedPanel;
    /**
     * button to remove a hotel
     */
    private JButton confirmRemoveHotelBtn;

    /**
     * Creates a ManageHotelPanel
     */
    public ManageHotelPanel() {
        super(new BorderLayout());

        init();
        setVisible(true);
    }

    /**
     * Initializing method
     */
    public void init() {
        String[] roomTypes = {"Standard", "Deluxe", "Executive"}; //room types
        //initialization of dates
        Integer[] startDateList = new Integer[30];
        Integer[] endDateList = new Integer[30];
        for(int i = 1; i <= 31; i++) {
            if (i > 1)
                endDateList[i - 2] = i;
            if(i < 31)
                startDateList[i - 1] = i;
        }
        //initialization of JComboBoxes
        cbRoomTypeAdd = new JComboBox<String>(roomTypes);
        cbRoomTypeRemove = new JComboBox<String>(roomTypes);
        cbRmToAdd = new JComboBox<Integer>();
        cbRmToRemove = new JComboBox<Integer>();
        cbStartDateMod = new JComboBox<Integer>(startDateList);
        cbEndDateMod = new JComboBox<>(endDateList);
        cbEndDateMod.addItem(31);
        lblInfoList = new ArrayList<JLabel>();

        //header labels
        JLabel lblManageHotel = new JLabel("Manage Hotel");
        lblManageHotel.setFont(new Font("Arial", Font.BOLD, 24));
        lblManageHotel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblManageHotel, BorderLayout.NORTH);

        //initialization of panels of the class
        changeNamePanel = changeHotelName();
        manageOptionPanel = manageOptions();
        addRoomsPanel = addRooms();
        removeRoomsPanel = removeRooms();
        updatePricePanel = updateBasePricePanel();
        updatePriceModPanel = updatePriceModPanel();
        removeRsrvPanel = removeRsrvPanel();
        removeHotelPanel = removeHotelPanel();
        hotelRemovedPanel = hotelRemovedPanel();

        //cardlayout of the class
        manageCardLayout = new CardLayout();
        managePanel = new JPanel(manageCardLayout);
        managePanel.add(manageOptionPanel, "manageOptionPanel");
        managePanel.add(changeNamePanel, "changeNamePanel");
        managePanel.add(addRoomsPanel, "addRoomsPanel");
        managePanel.add(removeRoomsPanel, "removeRoomsPanel");
        managePanel.add(updatePricePanel, "updatePricePanel");
        managePanel.add(updatePriceModPanel, "updatePriceModPanel");
        managePanel.add(removeRsrvPanel, "removeReservationPanel");
        managePanel.add(removeHotelPanel, "removeHotelPanel");
        managePanel.add(hotelRemovedPanel, "hotelRemovedPanel");

        manageCardLayout.show(managePanel, "manageOptionPanel");
        this.add(managePanel, BorderLayout.CENTER);

    }

    /**
     * Panel of management options
     * @return JPanel containing management options of a hotel system
     */
    public JPanel manageOptions(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        //JButtons of management options
        changeNameOptBtn = new JButton("Change Name");
        changeNameOptBtn.setFont(buttonFont);
        panel.add(changeNameOptBtn);

        addRoomOptBtn = new JButton("Add Room");
        addRoomOptBtn.setFont(buttonFont);
        panel.add(addRoomOptBtn);

        removeRoomOptBtn = new JButton("Remove Room");
        removeRoomOptBtn.setFont(buttonFont);
        panel.add(removeRoomOptBtn);

        updatePriceOptBtn = new JButton("Update Base Price");
        updatePriceOptBtn.setFont(buttonFont);
        panel.add(updatePriceOptBtn);

        updateModOptBtn = new JButton("Update Price Modifier");
        updateModOptBtn.setFont(buttonFont);
        panel.add(updateModOptBtn);

        removeRsrvOptBtn = new JButton("Remove Reservation");
        removeRsrvOptBtn.setFont(buttonFont);
        panel.add(removeRsrvOptBtn);

        removeHotelOptBtn = new JButton("Remove Hotel");
        removeHotelOptBtn.setFont(buttonFont);
        panel.add(removeHotelOptBtn);

        return panel;
    }

    /**
     * Panel to change hotel name
     * @return JPanel containing prompts for changing a hotel name
     */
    public JPanel changeHotelName(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblPrompt = new JLabel("Enter new Hotel Name: ");
        lblPrompt.setFont(labelFont);

        //asks for user input
        tfNewName = new JTextField(20);
        changeBtn = new JButton("Confirm Name Change");
        changeBtn.setFont(buttonFont);

        JPanel promptPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        promptPanel.add(lblPrompt);
        promptPanel.add(tfNewName);
        promptPanel.add(changeBtn);

        panel.add(promptPanel);
        return panel;
    }

    /**
     * Creates a panel for adding rooms
     * @return JPanel for user to add rooms to a hotel
     */
    public JPanel addRooms(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblAddRoom = new JLabel("Add Room");
        lblAddRoom.setFont(new Font("Arial", Font.BOLD, 16));
        lblAddRoom.setAlignmentX(Component.CENTER_ALIGNMENT);

        //asks for user input for room type
        JPanel roomTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblRoomType = new JLabel("Select Room Type");
        lblRoomType.setFont(labelFont);
        roomTypePanel.add(lblRoomType);
        roomTypePanel.add(cbRoomTypeAdd);
        //asks how many rooms to add
        JPanel roomCountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblRoomName = new JLabel("Select Number of Rooms to Add");
        lblRoomName.setFont(labelFont);
        roomCountPanel.add(lblRoomName);
        roomCountPanel.add(cbRmToAdd);

        //for confirmation
        confirmAddBtn = new JButton("Confirm Add Room");
        confirmAddBtn.setFont(buttonFont);
        confirmAddBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblAddRoom);
        panel.add(Box.createVerticalStrut(10));
        panel.add(roomTypePanel);
        panel.add(roomCountPanel);
        panel.add(confirmAddBtn);

        return panel;
    }

    /**
     * Creates a panel for user to remove rooms
     * @return JPanel of room removal options
     */
    public JPanel removeRooms(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblRemoveRoom = new JLabel("Remove Room");
        lblRemoveRoom.setFont(new Font("Arial", Font.BOLD, 16));
        lblRemoveRoom.setAlignmentX(Component.CENTER_ALIGNMENT);

        //asks for type of room to remove
        JPanel roomTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblRoomType = new JLabel("Room Type to Remove: ");
        lblRoomType.setFont(labelFont);
        roomTypePanel.add(lblRoomType);
        roomTypePanel.add(cbRoomTypeRemove);
        selectRoomRemoveBtn = new JButton("Select Room Type");
        selectRoomRemoveBtn.setFont(buttonFont);
        roomTypePanel.add(selectRoomRemoveBtn);

        //asks how many rooms to remove
        JPanel roomCountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblRoomName = new JLabel("Select Number of Rooms to Remove");
        lblRoomName.setFont(labelFont);
        roomCountPanel.add(lblRoomName);
        roomCountPanel.add(cbRmToRemove);

        //confirmation button
        confirmRemoveBtn = new JButton("Confirm Remove Room");
        confirmRemoveBtn.setFont(buttonFont);
        confirmRemoveBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblRemoveRoom);
        panel.add(Box.createVerticalStrut(10));
        panel.add(roomTypePanel);
        panel.add(roomCountPanel);
        panel.add(confirmRemoveBtn);

        return panel;
    }

    /**
     * Creates a panel to update base price of a hotel
     * @return JPanel of options to update hotel room base price
     */
    public JPanel updateBasePricePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblBasePrice = new JLabel("Update Base Price");
        lblBasePrice.setFont(new Font("Arial", Font.BOLD, 16));
        lblBasePrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        //asks for new price
        JPanel promptPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblNewPrice = new JLabel("Enter New Price");
        lblNewPrice.setFont(labelFont);
        tfNewPrice = new JTextField(10);
        //confirmation button
        confirmPriceBtn = new JButton("Confirm Base Price");
        confirmPriceBtn.setFont(buttonFont);

        promptPanel.add(lblNewPrice);
        promptPanel.add(tfNewPrice);
        promptPanel.add(confirmPriceBtn);

        panel.add(lblBasePrice);
        panel.add(Box.createVerticalStrut(10));
        panel.add(promptPanel);

        return panel;
    }

    /**
     * Creates a panel that updates the date-price modifier of a selected hotel
     * @return JPanel of options to update the modifier of a hotel
     */
    public JPanel updatePriceModPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblBasePrice = new JLabel("Update Price Modifier");
        lblBasePrice.setFont(new Font("Arial", Font.BOLD, 16));
        lblBasePrice.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //ComboBox options for selecting dates which modifiers will be changed
        JLabel lblStartDatePrompt = new JLabel("Enter Start Date: ");
        lblStartDatePrompt.setFont(labelFont);
        datePanel.add(lblStartDatePrompt);
        datePanel.add(cbStartDateMod);
        JLabel lblEndDatePrompt = new JLabel("Enter End Date: ");
        lblEndDatePrompt.setFont(labelFont);
        datePanel.add(lblEndDatePrompt);
        datePanel.add(cbEndDateMod);

        //asks user for new price modifier
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblNewPrice = new JLabel("Enter New Price Modifier [0.5 - 1.5]: ");
        lblNewPrice.setFont(labelFont);
        tfNewPriceMod = new JTextField(3);
        //confirmation button
        confirmPriceModBtn = new JButton("Confirm Price Modifier");
        confirmPriceModBtn.setFont(buttonFont);

        pricePanel.add(lblNewPrice);
        pricePanel.add(tfNewPriceMod);
        pricePanel.add(confirmPriceModBtn);

        panel.add(lblBasePrice);
        panel.add(Box.createVerticalStrut(10));
        panel.add(datePanel);
        panel.add(pricePanel);

        return panel;
    }

    /**
     * Creates a panel for removing a reservation
     * @return JPanel containing reservation removal options
     */
    public JPanel removeRsrvPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblRemoveRsrv = new JLabel("Remove Reservation");
        lblRemoveRsrv.setFont(new Font("Arial", Font.BOLD, 16));
        lblRemoveRsrv.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel promptPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //asks for guest name under the reservation
        JLabel lblRoomName = new JLabel("Enter Guest Name: ");
        lblRoomName.setFont(labelFont);
        tfGuestName = new JTextField(20);
        //button to find guest
        searchRsrvBtn = new JButton("Search Reservation");
        searchRsrvBtn.setFont(buttonFont);

        promptPanel.add(lblRoomName);
        promptPanel.add(tfGuestName);
        promptPanel.add(searchRsrvBtn);

        //confirmation button
        confirmRemoveRsrvBtn = new JButton("Confirm Remove Reservation");
        confirmRemoveRsrvBtn.setFont(buttonFont);
        confirmRemoveRsrvBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        //reservation panel which shows the reservation info once the guest is found
        rsrvInfoPanel = new JPanel();
        rsrvInfoPanel.setLayout(new BoxLayout(rsrvInfoPanel, BoxLayout.Y_AXIS));

        panel.add(lblRemoveRsrv);
        panel.add(Box.createVerticalStrut(10));
        panel.add(promptPanel);
        panel.add(confirmRemoveRsrvBtn);
        panel.add(rsrvInfoPanel);
        rsrvInfoPanel.setVisible(false);

        return panel;
    }

    /**
     * Creates a panel that can remove a hotel
     * @return JPanel which contain the option to remove the selected hotel
     */
    public JPanel removeHotelPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblRemoveHotel = new JLabel("Remove Hotel");
        lblRemoveHotel.setFont(new Font("Arial", Font.BOLD, 16));
        lblRemoveHotel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //confirmation prompt
        JLabel lblConfirmation = new JLabel("Are you sure you want to remove this Hotel?");
        lblConfirmation.setFont(labelFont);
        lblConfirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

        //confirmation button
        confirmRemoveHotelBtn = new JButton("Confirm Remove Hotel");
        confirmRemoveHotelBtn.setFont(buttonFont);
        confirmRemoveHotelBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblRemoveHotel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(lblConfirmation);
        panel.add(Box.createVerticalStrut(10));
        panel.add(confirmRemoveHotelBtn);

        return panel;
    }

    /**
     * Creates a panel if a hotel is removed
     * @return JPanel confirming that a hotel is removed
     */
    public JPanel hotelRemovedPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        //confirmation label
        JLabel lblRemoveHotel = new JLabel("Hotel Removed Successfully!");
        lblRemoveHotel.setFont(new Font("Arial", Font.BOLD, 16));
        lblRemoveHotel.setAlignmentX(Component.CENTER_ALIGNMENT);

        //option to return to the main panel of the program
        returnToHomeBtn = new JButton("Return to Main");
        returnToHomeBtn.setFont(buttonFont);
        returnToHomeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblRemoveHotel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(returnToHomeBtn);

        return panel;
    }

    /**
     * Method to update the reservation panel under remove reservation
     * This is called if no reservation is found
     */
    public void updateRsrvPanel(){
        JLabel notFoundMsg = new JLabel("Reservation Not Found");
        notFoundMsg.setForeground(Color.RED);
        rsrvInfoPanel.removeAll();
        rsrvInfoPanel.add(notFoundMsg);
        rsrvInfoPanel.repaint();
        rsrvInfoPanel.revalidate();
        removeRsrvPanel.repaint();
        removeRsrvPanel.revalidate();
        rsrvInfoPanel.setVisible(true);
    }
    /**
     * Method to update the reservation panel under remove reservation
     * This is called if a reservation is found
     */
    public void updateRsrvPanel(ArrayList<String> infoList){
        this.lblInfoList.clear();
        rsrvInfoPanel.removeAll();
        for(String line : infoList){
            System.out.println(line);
            this.lblInfoList.add(new JLabel(line));
        }
        for(JLabel label : lblInfoList){
            rsrvInfoPanel.add(label);
        }
        rsrvInfoPanel.repaint();
        rsrvInfoPanel.revalidate();
        removeRsrvPanel.repaint();
        removeRsrvPanel.revalidate();
        rsrvInfoPanel.setVisible(true);

    }

    /**
     * Action listener method for buttons and comboboxes
     * @param actionListener Action Listener for action performed
     */
    public void setActionListener(ActionListener actionListener) {
        changeNameOptBtn.addActionListener(actionListener);
        addRoomOptBtn.addActionListener(actionListener);
        removeRoomOptBtn.addActionListener(actionListener);
        updatePriceOptBtn.addActionListener(actionListener);
        updateModOptBtn.addActionListener(actionListener);
        removeRsrvOptBtn.addActionListener(actionListener);
        removeHotelOptBtn.addActionListener(actionListener);

        changeBtn.addActionListener(actionListener);
        cbRoomTypeAdd.addActionListener(actionListener);
        cbRmToAdd.addActionListener(actionListener);
        confirmAddBtn.addActionListener(actionListener);

        cbRoomTypeRemove.addActionListener(actionListener);
        cbRmToRemove.addActionListener(actionListener);
        selectRoomRemoveBtn.addActionListener(actionListener);
        confirmRemoveBtn.addActionListener(actionListener);

        confirmPriceBtn.addActionListener(actionListener);
        cbStartDateMod.addActionListener(actionListener);
        cbEndDateMod.addActionListener(actionListener);
        confirmPriceModBtn.addActionListener(actionListener);
        returnToHomeBtn.addActionListener(actionListener);

        searchRsrvBtn.addActionListener(actionListener);
        confirmRemoveRsrvBtn.addActionListener(actionListener);

        confirmRemoveHotelBtn.addActionListener(actionListener);

    }

    /**
     * Document Listener for text fields
     * @param documentListener document listener for inputted text
     */
    public void setDocumentListener(DocumentListener documentListener) {
        tfNewName.getDocument().addDocumentListener(documentListener);
        tfNewPrice.getDocument().addDocumentListener(documentListener);
        tfNewPriceMod.getDocument().addDocumentListener(documentListener);
        tfGuestName.getDocument().addDocumentListener(documentListener);
    }

    /**
     * getter method for tfNewName as a string
     * @return string value entered in tfNewName
     */
    public String getTfNewName(){
        return tfNewName.getText();
    }
    /**
     * getter method for tfNewPrice as a string
     * @return string value entered in tfNewPrice
     */
    public String getTfNewPrice(){
        return tfNewPrice.getText();
    }
    /**
     * getter method for tfNewPriceMod as a string
     * @return string value entered in tfPriceMod
     */
    public String getTfNewPriceMod(){
        return tfNewPriceMod.getText();
    }
    /**
     * getter method for tfGuestName as a string
     * @return string value entered in tfGuestName
     */
    public String getTfGuestName(){
        return tfGuestName.getText();
    }

    /**
     * Getter method to get managePanel
     * @return managePanel
     */
    public JPanel getManagePanel() {
        return managePanel;
    }

    /**
     * Getter method for manageCardLayout
     * @return cardLayout of the class
     */
    public CardLayout getManageCardLayout() {
        return manageCardLayout;
    }

    /**
     * getter method for cbRoomTypeAdd
     * @return cbRoomTypeAdd
     */
    public JComboBox<String> getCbRoomTypeAdd() {
        return cbRoomTypeAdd;
    }
    /**
     * getter method for cbRmToAdd
     * @return cbRmToAdd
     */
    public JComboBox<Integer> getCbRmToAdd(){
        return cbRmToAdd;
    }
    /**
     * getter method for cbRoomTypeRemove
     * @return cbRoomTypeRemove
     */
    public JComboBox<String> getCbRoomTypeRemove() {
        return cbRoomTypeRemove;
    }
    /**
     * getter method for cbRmToRemove
     * @return cbRmToRemove
     */
    public JComboBox<Integer> getCbRmToRemove() {
        return cbRmToRemove;
    }
    /**
     * getter method for cbStartDateMod
     * @return cbStartDateMod
     */
    public JComboBox<Integer> getCbStartDateMod(){
        return cbStartDateMod;
    }
    /**
     * getter method for cbEndDateMod
     * @return cbEndDateMod
     */
    public JComboBox<Integer> getCbEndDateMod(){
        return cbEndDateMod;
    }
}
