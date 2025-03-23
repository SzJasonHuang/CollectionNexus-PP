package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import model.Card;
import model.CardList;
import persistence.JsonReader;
import persistence.JsonWriter;

//Inspiration taken from the Teller App for JSON implementation and Space Invaders for GUI
//Connection Nexus App, extends JFrame for GUI purposes
public class CollectionNexusJFrame extends JFrame {
    private static final String JSON_STORE_binder = "./data/binder.json";
    private static final String JSON_STORE_wishlist = "./data/wishlist.json";
    private static final String Lebron_Image = "./data/lebron.png";

    private CardList binder;
    private CardList wishlist;
    private JsonWriter jsonWriter;
    private JsonWriter jsonWriter1;
    private JsonReader jsonReader;
    private JsonReader jsonReader1;

    private JTextField nameInput;
    private JTextField conditionInput;
    private JTextField rarityInput;
    private JTextField typeInput;
    private JTextField priceInput;
    private JTextField searchInput;
    private JTextField removeInput;
    private JPanel controlPanel = new JPanel();

    private JTextArea displayArea;

    // EFFECTS: Runs the CollectionNexus APP
    public CollectionNexusJFrame() {
        super("CollectionNexus");
        // Data Initialization
        binder = new CardList();
        wishlist = new CardList();
        // JSON Read/Writer Initialization
        jsonWriter = new JsonWriter(JSON_STORE_binder);
        jsonWriter1 = new JsonWriter(JSON_STORE_wishlist);
        jsonReader = new JsonReader(JSON_STORE_binder);
        jsonReader1 = new JsonReader(JSON_STORE_wishlist);
        // GUI Setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);
        // Local Helper
        setUpGUI();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Set up all panels, buttons and visual component, adds all panels to
    // JFrame
    public void setUpGUI() {
        ImageBackgroundPanel mainPanel = new ImageBackgroundPanel(Lebron_Image);
        mainPanel.setLayout(new BorderLayout());
        displayArea = new JTextArea(14, 50);
        createDisplayArea(displayArea);
        JScrollPane scrollableDisplay = new JScrollPane(displayArea);
        scrollableDisplay.getViewport().setOpaque(false);
        scrollableDisplay.setOpaque(false);
        mainPanel.add(scrollableDisplay, BorderLayout.CENTER);
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        controlPanel.add(createInputFieldsPanel());
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(createAddPanel());
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(createRemovePanel());
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(createSearchPanel());
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(createSaveLoadPanel());
        controlPanel.add(Box.createVerticalStrut(10));
        controlPanel.add(createViewAllPanel());
        mainPanel.add(controlPanel, BorderLayout.EAST);
        add(mainPanel);
    }
    
    //MODIFIES: this
    //EFFECTS: sets up the display area, used as a helper for setUpGUI to reduce lines
    private JTextArea createDisplayArea(JTextArea area) {
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.BOLD, 12));
        area.setBackground(new Color(230, 240, 255));
        area.setOpaque(false);
        return area;
    }

    //EFFECTS: sets up the input fields, used as a helper for setUpGUI to reduce lines
    private JPanel createInputFieldsPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 4, 4));
        panel.setBorder(BorderFactory.createTitledBorder("Card Info"));
        nameInput = new JTextField(15);
        conditionInput = new JTextField(15);
        rarityInput = new JTextField(15);
        typeInput = new JTextField(15);
        priceInput = new JTextField(15);
        panel.add(new JLabel("Card Name:"));
        panel.add(nameInput);
        panel.add(new JLabel("Condition:"));
        panel.add(conditionInput);
        panel.add(new JLabel("Rarity:"));
        panel.add(rarityInput);
        panel.add(new JLabel("Type:"));
        panel.add(typeInput);
        panel.add(new JLabel("Price:"));
        panel.add(priceInput);
        return panel;
    }

    //EFFECTS: creates the add panel and adds associated buttons, used as a helper for setUpGUI to reduce lines
    private JPanel createAddPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 4, 4));
        panel.setBorder(BorderFactory.createTitledBorder("Add"));
        JButton addButton = new JButton("Add to Binder");
        addButton.addActionListener(this::handleAddToBinder);
        JButton addWishListButton = new JButton("Add to WishList");
        addWishListButton.addActionListener(this::handleAddToWishlist);
        panel.add(addButton);
        panel.add(addWishListButton);
        return panel;
    }

    //EFFECTS: creates the remove panel and adds associated buttons, used as a helper for setUpGUI to reduce lines
    private JPanel createRemovePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 4, 4));
        panel.setBorder(BorderFactory.createTitledBorder("Remove"));
        JLabel label = new JLabel("Card Name to Remove:");
        removeInput = new JTextField(12);
        JButton removeBinder = new JButton("Remove from Binder");
        JButton removeWishlist = new JButton("Remove from Wishlist");
        removeBinder.addActionListener(this::handleRemoveFromBinder);
        removeWishlist.addActionListener(this::handleRemoveFromWishlist);
        panel.add(label);
        panel.add(removeInput);
        panel.add(removeBinder);
        panel.add(removeWishlist);
        return panel;
    }

    //EFFECTS: creates the save/load panel and adds associated buttons, used as a helper for setUpGUI to reduce lines
    private JPanel createSaveLoadPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Smart Save/Load"));
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        saveButton.addActionListener(this::save);
        loadButton.addActionListener(this::load);
        panel.add(saveButton);
        panel.add(loadButton);
        return panel;
    }

    //EFFECTS: creates the viewall panel and adds associated buttons, used as a helper for setUpGUI to reduce lines
    private JPanel createViewAllPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("View Cards"));
        JButton viewBinder = new JButton("View All in Binder");
        JButton viewWishlist = new JButton("View All in Wishlist");
        viewBinder.addActionListener(this::handleViewBinder);
        viewWishlist.addActionListener(this::handleViewWishlist);
        panel.add(viewBinder);
        panel.add(viewWishlist);
        return panel;
    }

    //EFFECTS: creates the search panel and adds associated buttons/input fields
    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Search"));
        panel.add(new JLabel("Search For A Card:"));
        searchInput = new JTextField(10);
        JButton searchButton = new JButton("Search Binder");
        searchButton.addActionListener(this::handleSearch);
        panel.add(searchInput);
        panel.add(searchButton);
        return panel;
    }

    // MODIFIES: this
    // EFFECTS: Given name of card, search user's binder for a card that matches the
    // name and
    // displays the details to the owner if found.
    private void handleSearch(ActionEvent e) {
        String target = searchInput.getText().trim();

        if (target.isEmpty()) {
            displayArea.append("Please enter a card name to search.\n");
            return;
        }

        Card foundCard = binder.searchCard(target);
        if (foundCard == null) {
            displayArea.append("Card not found in Binder: " + target + "\n");
        } else {
            displayArea.append("Found: " + foundCard.getQuantity() + " copy/copies of "
                    + foundCard.readCardInfo() + "\n");
        }

        searchInput.setText("");
    }

    // MODIFIES: this
    // EFFECTS: Adds a single card to user's collection.
    private void handleAddToBinder(ActionEvent e) {
        try {
            String name = nameInput.getText().trim();
            String condition = conditionInput.getText().trim();
            String rarity = rarityInput.getText().trim();
            String type = typeInput.getText().trim();
            int price = Integer.parseInt(priceInput.getText().trim());

            if (name.isEmpty() || condition.isEmpty() || rarity.isEmpty() || type.isEmpty()) {
                displayArea.append("All fields must be filled!\n");
                return;
            }
            Card card = new Card(condition, rarity, name, type, price);
            binder.addCard(card);
            displayArea.append("Card added to Binder: " + card.readCardInfo() + "\n");
            clearInputBox();
        } catch (NumberFormatException ex) {
            displayArea.append("Price must be a valid number.\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds a single card to user's wishlist.
    private void handleAddToWishlist(ActionEvent e) {
        try {
            String name = nameInput.getText().trim();
            String condition = conditionInput.getText().trim();
            String rarity = rarityInput.getText().trim();
            String type = typeInput.getText().trim();
            int price = Integer.parseInt(priceInput.getText().trim());

            if (name.isEmpty() || condition.isEmpty() || rarity.isEmpty() || type.isEmpty()) {
                displayArea.append("All fields must be filled!\n");
                return;
            }
            Card card = new Card(condition, rarity, name, type, price);
            wishlist.addCard(card);
            displayArea.append("Card added to Wishlist: " + card.readCardInfo() + "\n");
            clearInputBox();
        } catch (NumberFormatException ex) {
            displayArea.append("Price must be a valid number.\n");
        }
    }

    // MODIFIES: this
    // EFFECT: clears the input box for user
    private void clearInputBox() {
        nameInput.setText("");
        conditionInput.setText("");
        rarityInput.setText("");
        typeInput.setText("");
        priceInput.setText("");
    }

    // MODIFIES: this
    // EFFECTS: save current binder and wishlist to JSON file
    private void save(ActionEvent e1) {
        try {
            jsonWriter.open();
            jsonWriter.write(binder);
            jsonWriter.close();
            displayArea.append("Saved Binder to " + JSON_STORE_binder + "\n");
        } catch (FileNotFoundException e) {
            displayArea.append("Unable to write to file: " + JSON_STORE_binder + "\n");
        }
        try {
            jsonWriter1.open();
            jsonWriter1.write(wishlist);
            jsonWriter1.close();
            displayArea.append("Saved Wishlist to " + JSON_STORE_wishlist + "\n");
        } catch (FileNotFoundException e) {
            displayArea.append("Unable to write to file: " + JSON_STORE_wishlist + "\n");
        }
    }

    // MODIFIES: this
    // EFFECT: load current binder and wishlist from JSON file
    private void load(ActionEvent e1) {
        try {
            binder = jsonReader.read();
            displayArea.append("Loaded Binder from" + JSON_STORE_binder + "\n");
        } catch (IOException e) {
            displayArea.append("Unable to write to file: " + JSON_STORE_binder + "\n");
        }
        try {
            wishlist = jsonReader1.read();
            displayArea.append("Loaded Wishlist from" + JSON_STORE_wishlist + "\n");
        } catch (IOException e) {
            displayArea.append("Unable to write to file: " + JSON_STORE_wishlist + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: Based on the user's input, remove target card from user's binder
    private void handleRemoveFromBinder(ActionEvent e) {
        String name = removeInput.getText().trim();
        if (name.isEmpty()) {
            displayArea.append("Enter the card name to remove from Binder.\n");
            return;
        }

        boolean removed = binder.removeCardFromList(name);
        if (removed) {
            displayArea.append("Removed card from Binder: " + name + "\n");
        } else {
            displayArea.append("No such card in Binder: " + name + "\n");
        }
        clearInputBox();
    }

    // MODIFIES: this
    // EFFECTS: Based on the user's input, remove target card from user's wishlist
    private void handleRemoveFromWishlist(ActionEvent e) {
        String name = removeInput.getText().trim();
        if (name.isEmpty()) {
            displayArea.append("Enter the card name to remove from Wishlist.\n");
            return;
        }
        boolean removed = wishlist.removeCardFromList(name);
        if (removed) {
            displayArea.append("Removed card from Wishlist: " + name + "\n");
        } else {
            displayArea.append("No such card in Wishlist: " + name + "\n");
        }
        clearInputBox();
    }

    // EFFECTS: Displays all card's information in the owner's binder
    private void handleViewBinder(ActionEvent e) {
        if (binder.getMyBinder().isEmpty()) {
            displayArea.append("You currently don't have any cards in your collection" + "\n");
        } else {
            int num = 1;
            boolean firstTime = true;
            displayArea.append("Your cards in binder: ");
            for (Card indi : binder.getMyBinder()) {
                String displayNum = Integer.toString(num);
                if (firstTime) {
                    displayArea.append(displayNum + ": " + indi.readCardInfo() + "\n");
                    firstTime = false;
                } else {
                    displayArea.append("                      " + displayNum + ": " + indi.readCardInfo() + "\n");
                }
                num++;
            }
        }
    }

    // EFFECTS: Displays all card's information in the owner's wishlist
    private void handleViewWishlist(ActionEvent e) {
        if (wishlist.getMyBinder().isEmpty()) {
            displayArea.append("You currently don't have any cards in your wishlist" + "\n");
        } else {
            int num = 1;
            boolean firstTime = true;
            displayArea.append("Your cards in wishlist: ");
            for (Card indi : wishlist.getMyBinder()) {
                String displayNum = Integer.toString(num);
                if (firstTime) {
                    displayArea.append(displayNum + ": " + indi.readCardInfo() + "\n");
                    firstTime = false;
                } else {
                    displayArea.append("                        " + displayNum + ": " + indi.readCardInfo() + "\n");
                }
                num++;
            }
        }
    }
}
