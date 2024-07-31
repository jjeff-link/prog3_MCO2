import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

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
    private JPanel rsrvInfoPanel;

    private JTextField tfGuestName;
    private JButton searchRsrvBtn;
    private ArrayList<JLabel> lblInfoList;
    private JButton confirmRemoveRsrvBtn;
    private JButton returnToHomeBtn;

    private JPanel hotelRemovedPanel;

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
        lblInfoList = new ArrayList<JLabel>();

        JLabel lblManageHotel = new JLabel("Manage Hotel");
        lblManageHotel.setFont(new Font("Arial", Font.BOLD, 24));
        lblManageHotel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblManageHotel, BorderLayout.NORTH);

        changeNamePanel = changeHotelName();
        manageOptionPanel = manageOptions();
        addRoomsPanel = addRooms();
        removeRoomsPanel = removeRooms();
        updatePricePanel = updateBasePricePanel();
        updatePriceModPanel = updatePriceModPanel();
        removeRsrvPanel = removeRsrvPanel();
        removeHotelPanel = removeHotelPanel();
        hotelRemovedPanel = hotelRemovedPanel();


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


    public JPanel manageOptions(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

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


    public JPanel changeHotelName(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblPrompt = new JLabel("Enter new Hotel Name: ");
        lblPrompt.setFont(labelFont);

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


    public JPanel addRooms(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblAddRoom = new JLabel("Add Room");
        lblAddRoom.setFont(new Font("Arial", Font.BOLD, 16));
        lblAddRoom.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel roomTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblRoomType = new JLabel("Select Room Type");
        lblRoomType.setFont(labelFont);
        roomTypePanel.add(lblRoomType);
        roomTypePanel.add(cbRoomTypeAdd);

        JPanel roomCountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblRoomName = new JLabel("Select Number of Rooms to Add");
        lblRoomName.setFont(labelFont);
        roomCountPanel.add(lblRoomName);
        roomCountPanel.add(cbRmToAdd);

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

    public JPanel removeRooms(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblRemoveRoom = new JLabel("Remove Room");
        lblRemoveRoom.setFont(new Font("Arial", Font.BOLD, 16));
        lblRemoveRoom.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel roomTypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblRoomType = new JLabel("Room Type to Remove: ");
        lblRoomType.setFont(labelFont);
        roomTypePanel.add(lblRoomType);
        roomTypePanel.add(cbRoomTypeRemove);
        selectRoomRemoveBtn = new JButton("Select Room Type");
        selectRoomRemoveBtn.setFont(buttonFont);
        roomTypePanel.add(selectRoomRemoveBtn);

        JPanel roomCountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblRoomName = new JLabel("Select Number of Rooms to Remove");
        lblRoomName.setFont(labelFont);
        roomCountPanel.add(lblRoomName);
        roomCountPanel.add(cbRmToRemove);

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

    public JPanel updateBasePricePanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblBasePrice = new JLabel("Update Base Price");
        lblBasePrice.setFont(new Font("Arial", Font.BOLD, 16));
        lblBasePrice.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel promptPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblNewPrice = new JLabel("Enter New Price");
        lblNewPrice.setFont(labelFont);
        tfNewPrice = new JTextField(10);
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
        JLabel lblStartDatePrompt = new JLabel("Enter Start Date: ");
        lblStartDatePrompt.setFont(labelFont);
        datePanel.add(lblStartDatePrompt);
        datePanel.add(cbStartDateMod);
        JLabel lblEndDatePrompt = new JLabel("Enter End Date: ");
        lblEndDatePrompt.setFont(labelFont);
        datePanel.add(lblEndDatePrompt);
        datePanel.add(cbEndDateMod);

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblNewPrice = new JLabel("Enter New Price Modifier [0.5 - 1.5]: ");
        lblNewPrice.setFont(labelFont);
        tfNewPriceMod = new JTextField(3);
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
        JLabel lblRoomName = new JLabel("Enter Guest Name: ");
        lblRoomName.setFont(labelFont);
        tfGuestName = new JTextField(20);
        searchRsrvBtn = new JButton("Search Reservation");
        searchRsrvBtn.setFont(buttonFont);

        promptPanel.add(lblRoomName);
        promptPanel.add(tfGuestName);
        promptPanel.add(searchRsrvBtn);

        confirmRemoveRsrvBtn = new JButton("Confirm Remove Reservation");
        confirmRemoveRsrvBtn.setFont(buttonFont);
        confirmRemoveRsrvBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

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

    public JPanel removeHotelPanel(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblRemoveHotel = new JLabel("Remove Hotel");
        lblRemoveHotel.setFont(new Font("Arial", Font.BOLD, 16));
        lblRemoveHotel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblConfirmation = new JLabel("Are you sure you want to remove this Hotel?");
        lblConfirmation.setFont(labelFont);
        lblConfirmation.setAlignmentX(Component.CENTER_ALIGNMENT);

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

    public JPanel hotelRemovedPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);

        JLabel lblRemoveHotel = new JLabel("Hotel Removed Successfully!");
        lblRemoveHotel.setFont(new Font("Arial", Font.BOLD, 16));
        lblRemoveHotel.setAlignmentX(Component.CENTER_ALIGNMENT);

        returnToHomeBtn = new JButton("Return to Main");
        returnToHomeBtn.setFont(buttonFont);
        returnToHomeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(lblRemoveHotel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(returnToHomeBtn);

        return panel;
    }


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
    public String getTfGuestName(){
        return tfGuestName.getText();
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
