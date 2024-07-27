import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class ManageHotelPanel extends JPanel {
    private JPanel managePanel = new JPanel();
    private CardLayout manageCardLayout = new CardLayout();


    private JPanel manageOptionPanel;
    private JPanel changeNamePanel;
    private JPanel addRoomsPanel;
    private JPanel removeRoomsPanel;
    private JPanel updatePricePanel;
    private JPanel updatePriceModPanel;
    private JPanel removeRsrvPanel;
    private JPanel removeHotelPanel;

    private JButton changeNameOptBtn;
    private JButton addRoomOptBtn;
    private JButton removeRoomOptBtn;
    private JButton updatePriceOptBtn;
    private JButton updateModOptBtn;
    private JButton removeRsrvOptBtn;
    private JButton removeHotelOptBtn;

    private JButton changeBtn;
    private JTextField tfNewName;

    private JComboBox<String> cbRoomTypeAdd;
    private JComboBox<Integer> cbRmToAdd;
    private JButton confirmAddBtn;

    private JComboBox<String> cbRoomTypeRemove;
    private JComboBox<Integer> cbRmToRemove;
    private JButton selectRoomRemoveBtn;
    private JButton confirmRemoveBtn;


    private JTextField tfNewPrice;
    private JButton confirmPriceBtn;

    private JComboBox<Integer> cbStartDateMod;
    private JComboBox<Integer> cbEndDateMod;
    private JTextField tfNewPriceMod;
    private JButton confirmPriceModBtn;

    private JTextField tfGuestName;
    private JButton searchRsrvBtn;
    private JButton confirmRemoveRsrvBtn;

    private JButton confirmRemoveHotelBtn;

    public ManageHotelPanel() {
        super(new BorderLayout());

        init();
        setVisible(true);
    }

    public void init() {
        String[] roomTypes = {"Standard", "Deluxe", "Executive"};
        Integer[] startDateList = new Integer[30];
        Integer[] endDateList = new Integer[30];
        for(int i = 1; i <= 31; i++) {
            if (i > 1)
                endDateList[i - 2] = i;
            if(i < 31)
                startDateList[i - 1] = i;
        }
        cbRoomTypeAdd = new JComboBox<String>(roomTypes);
        cbRoomTypeRemove = new JComboBox<String>(roomTypes);
        cbRmToAdd = new JComboBox<Integer>();
        cbRmToRemove = new JComboBox<Integer>();
        cbStartDateMod = new JComboBox<Integer>(startDateList);
        cbEndDateMod = new JComboBox<>(endDateList);
        cbEndDateMod.addItem(31);

        JLabel lblManageHotel = new JLabel("Manage Hotel");
        this.add(lblManageHotel, BorderLayout.NORTH);

        changeNamePanel = changeHotelName();
        manageOptionPanel = manageOptions();
        addRoomsPanel = addRooms();
        removeRoomsPanel = removeRooms();
        updatePricePanel = updateBasePricePanel();
        updatePriceModPanel = updatePriceModPanel();
        removeRsrvPanel = removeRsrvPanel();
        removeHotelPanel = removeHotelPanel();


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

        manageCardLayout.show(managePanel, "manageOptionPanel");
        this.add(managePanel, BorderLayout.CENTER);

    }


    public JPanel manageOptions(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        changeNameOptBtn = new JButton("Change Name");
        panel.add(changeNameOptBtn);
        addRoomOptBtn = new JButton("Add Room");
        panel.add(addRoomOptBtn);
        removeRoomOptBtn = new JButton("Remove Room");
        panel.add(removeRoomOptBtn);
        updatePriceOptBtn = new JButton("Update Base Price");
        panel.add(updatePriceOptBtn);
        updateModOptBtn = new JButton("Update Price Modifier");
        panel.add(updateModOptBtn);
        removeRsrvOptBtn = new JButton("Remove Reservation");
        panel.add(removeRsrvOptBtn);
        removeHotelOptBtn = new JButton("Remove Hotel");
        panel.add(removeHotelOptBtn);


        return panel;
    }


    public JPanel changeHotelName(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //flow
        JPanel promptPanel = new JPanel();
        promptPanel.setLayout(new FlowLayout());
        JLabel lblPrompt = new JLabel("Enter new Hotel Name: ");
        promptPanel.add(lblPrompt);
        tfNewName = new JTextField(20);
        promptPanel.add(tfNewName);
        changeBtn = new JButton("Confirm Name Change");
        promptPanel.add(changeBtn);

        panel.add(promptPanel);

        return panel;
    }


    public JPanel addRooms(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblAddRoom = new JLabel("Add Room");
        panel.add(lblAddRoom);

        //roomType
        JPanel roomTypePanel = new JPanel();
        roomTypePanel.setLayout(new FlowLayout());
        JLabel lblRoomType = new JLabel("Select Room Type");
        roomTypePanel.add(lblRoomType);
        roomTypePanel.add(cbRoomTypeAdd);
        panel.add(roomTypePanel);

        //roomCount
        JPanel roomCountPanel = new JPanel();
        roomCountPanel.setLayout(new FlowLayout());
        JLabel lblRoomName = new JLabel("Select Number of Rooms to Add");
        roomCountPanel.add(lblRoomName);
        roomCountPanel.add(cbRmToAdd);
        panel.add(roomCountPanel);

        confirmAddBtn = new JButton("Confirm Add Room");
        panel.add(confirmAddBtn);

        return panel;
    }

    public JPanel removeRooms(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblRemoveRoom = new JLabel("Remove Room");
        panel.add(lblRemoveRoom);


        //roomType
        JPanel roomTypePanel = new JPanel();
        roomTypePanel.setLayout(new FlowLayout());
        JLabel lblRoomType = new JLabel("Room Type to Remove: ");
        roomTypePanel.add(lblRoomType);
        roomTypePanel.add(cbRoomTypeRemove);
        selectRoomRemoveBtn = new JButton("Select Room Type");
        roomTypePanel.add(selectRoomRemoveBtn);
        panel.add(roomTypePanel);

        //room count
        JPanel roomCountPanel = new JPanel();
        JLabel lblRoomName = new JLabel("Select Number of Rooms to Remove");
        roomCountPanel.add(lblRoomName);
        roomCountPanel.add(cbRmToRemove);
        panel.add(roomCountPanel);

        confirmRemoveBtn = new JButton("Confirm Remove Room");
        panel.add(confirmRemoveBtn);

        return panel;
    }

    public JPanel updateBasePricePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel lblBasePrice = new JLabel("Update Base Price");
        panel.add(lblBasePrice);

        //prompt Panel
        JPanel promptPanel = new JPanel();
        promptPanel.setLayout(new FlowLayout());
        JLabel lblNewPrice = new JLabel("Enter New Price");
        promptPanel.add(lblNewPrice);
        tfNewPrice = new JTextField(10);
        promptPanel.add(tfNewPrice);
        confirmPriceBtn = new JButton("Confirm Base Price");
        promptPanel.add(confirmPriceBtn);

        panel.add(promptPanel);


        return panel;
    }

    public JPanel updatePriceModPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel lblBasePrice = new JLabel("Update Price Modifier");
        panel.add(lblBasePrice);

        //date Panel
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());
        JLabel lblStartDatePrompt = new JLabel("Enter Start Date: ");
        datePanel.add(lblStartDatePrompt);
        datePanel.add(cbStartDateMod);
        JLabel lblEndDatePrompt = new JLabel("Enter End Date: ");
        datePanel.add(lblEndDatePrompt);
        datePanel.add(cbEndDateMod);
        panel.add(datePanel);

        //price Panel
        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new FlowLayout());
        JLabel lblNewPrice = new JLabel("Enter New Price Modifier [0.5 - 1.5]: ");
        pricePanel.add(lblNewPrice);
        tfNewPriceMod = new JTextField(3);
        pricePanel.add(tfNewPriceMod);
        confirmPriceModBtn = new JButton("Confirm Price Modifier");
        pricePanel.add(confirmPriceModBtn);
        panel.add(pricePanel);

        return panel;
    }

    public JPanel removeRsrvPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblRemoveRsrv = new JLabel("Remove Reservation");
        panel.add(lblRemoveRsrv);

        //prompt Panel
        JPanel promptPanel = new JPanel();
        promptPanel.setLayout(new FlowLayout());
        JLabel lblRoomName = new JLabel("Enter Guest Name: ");
        promptPanel.add(lblRoomName);
        tfGuestName = new JTextField(20);
        promptPanel.add(tfGuestName);
        searchRsrvBtn = new JButton("Search Reservation");
        promptPanel.add(searchRsrvBtn);
        panel.add(promptPanel);

        confirmRemoveRsrvBtn = new JButton("Confirm Remove Reservation");
        panel.add(confirmRemoveRsrvBtn);

        return panel;
    }

    public JPanel removeHotelPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblRemoveHotel = new JLabel("Remove Hotel");
        panel.add(lblRemoveHotel);

        JLabel lblRoomName = new JLabel("Are you sure you want to remove this Hotel?");
        panel.add(lblRoomName);
        confirmRemoveHotelBtn = new JButton("Confirm Remove Hotel");
        panel.add(confirmRemoveHotelBtn);


        return panel;
    }


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

        searchRsrvBtn.addActionListener(actionListener);
        confirmRemoveRsrvBtn.addActionListener(actionListener);

        confirmRemoveHotelBtn.addActionListener(actionListener);

    }

    public void setDocumentListener(DocumentListener documentListener) {
        tfNewName.getDocument().addDocumentListener(documentListener);
        tfNewPrice.getDocument().addDocumentListener(documentListener);
        tfNewPriceMod.getDocument().addDocumentListener(documentListener);
        tfGuestName.getDocument().addDocumentListener(documentListener);
    }

    public void setItemListener(ItemListener itemListener) {
        cbRoomTypeRemove.addItemListener(itemListener);
    }

    public String getTfNewName(){
        return tfNewName.getText();
    }
    public String getTfNewPrice(){
        return tfNewPrice.getText();
    }
    public String getTfNewPriceMod(){
        return tfNewPriceMod.getText();
    }



    public JPanel getManagePanel() {
        return managePanel;
    }
    public CardLayout getManageCardLayout() {
        return manageCardLayout;
    }

    public JPanel getManageOptionPanel() {
        return manageOptionPanel;
    }
    public JPanel getChangeNamePanel() {
        return changeNamePanel;
    }
    public JPanel getAddRoomsPanel() {
        return addRoomsPanel;
    }
    public JPanel getRemoveRoomsPanel() {
        return removeRoomsPanel;
    }
    public JPanel getUpdatePricePanel() {
        return updatePricePanel;
    }

    public JComboBox<String> getCbRoomTypeAdd() {
        return cbRoomTypeAdd;
    }
    public JComboBox<Integer> getCbRmToAdd(){
        return cbRmToAdd;
    }
    public JComboBox<String> getCbRoomTypeRemove() {
        return cbRoomTypeRemove;
    }
    public JComboBox<Integer> getCbRmToRemove() {
        return cbRmToRemove;
    }
    public JComboBox<Integer> getCbStartDateMod(){
        return cbStartDateMod;
    }
    public JComboBox<Integer> getCbEndDateMod(){
        return cbEndDateMod;
    }



    public JButton getSelectRoomRemoveBtn() {
        return selectRoomRemoveBtn;
    }
}
