package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.util.Scanner;

import model.Card;
import model.CardList;
import persistence.JsonReader;
import persistence.JsonWriter;

public class CollectionNexusJFrame extends JFrame {
    private static final String JSON_STORE_binder = "./data/binder.json";
    private static final String JSON_STORE_wishlist = "./data/wishlist.json";
    private CardList binder;
    private CardList wishlist;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonWriter jsonWriter1;
    private JsonReader jsonReader;
    private JsonReader jsonReader1;

    private JTextField nameInput;
    private JTextField conditionInput;
    private JTextField rarityInput;
    private JTextField typeInput;
    private JTextField priceInput;

    private JTextArea displayArea;

    public CollectionNexusJFrame() {
        super("CollectionNexus");
        // Data Intialization
        binder = new CardList();
        wishlist = new CardList();

        // JSON Read/Writer Initialization
        jsonWriter = new JsonWriter(JSON_STORE_binder);
        jsonWriter1 = new JsonWriter(JSON_STORE_wishlist);
        jsonReader = new JsonReader(JSON_STORE_binder);
        jsonReader1 = new JsonReader(JSON_STORE_wishlist);

        // GUI SetUp
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 600);
        setLocationRelativeTo(null);

        // Local Helper
        setUpGUI();

        setVisible(true);

    }

    public void setUpGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Intialize DisplayArea
        displayArea = new JTextArea(14, 50);
        displayArea.setEditable(false);
        JScrollPane scrollableDisplay = new JScrollPane(displayArea);
        mainPanel.add(scrollableDisplay, BorderLayout.CENTER);

        // Create Input JPanel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        // Setup Padding for Button
        gbc.insets = new Insets(4, 4, 4, 4);

        // Creating Input Fields
        // Creating a input field for the card name
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Card Name:"), gbc);
        gbc.gridx = 1;
        nameInput = new JTextField(15);
        inputPanel.add(nameInput, gbc);

        // Creating a input field for the condition
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Condition:"), gbc);
        gbc.gridx = 1;
        conditionInput = new JTextField(15);
        inputPanel.add(conditionInput, gbc);

        // Creating a input field for the Rarity
        gbc.gridx = 0;
gbc.gridy = 2;
inputPanel.add(new JLabel("Rarity:"), gbc);
gbc.gridx = 1;
rarityInput = new JTextField(15);
inputPanel.add(rarityInput, gbc);

        // Creating a input field for the type
        gbc.gridx = 0;
gbc.gridy = 3;
inputPanel.add(new JLabel("Type:"), gbc);
gbc.gridx = 1;
typeInput = new JTextField(15);
inputPanel.add(typeInput, gbc);

        // Creating a input field for the price
        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Price:"), gbc);
        gbc.gridx = 1;
        priceInput = new JTextField(15);
        inputPanel.add(priceInput, gbc);

        mainPanel.add(inputPanel, BorderLayout.WEST);
        add(mainPanel);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton addButton = new JButton("Add to Binder");
        addButton.addActionListener(this::handleAddToBinder);
        inputPanel.add(addButton, gbc);

        // Creating a panel under inputs, allowing users to save/load data
        JPanel saveLoadPanel = new JPanel();
        saveLoadPanel.setLayout(new FlowLayout());
        saveLoadPanel.setBorder(BorderFactory.createTitledBorder("Smart Save/Load"));
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        saveLoadPanel.add(saveButton);
        saveLoadPanel.add(loadButton);
        // set GBC-y position under the last input, the
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        inputPanel.add(saveLoadPanel, gbc);

    }

    private void handleAddToBinder(ActionEvent e) {
        try {
            String name = nameInput.getText().trim();
            String condition = conditionInput.getText().trim();
            String rarity = rarityInput.getText().trim();
            String type = typeInput.getText().trim();
            int price = Integer.parseInt(priceInput.getText().trim());

            if (name.isEmpty() || condition.isEmpty() || rarity.isEmpty() || type.isEmpty()) {
                displayArea.append("⚠ All fields must be filled!\n");
                return;
            }

            Card card = new Card(condition, rarity, name, type, price);
            binder.addCard(card);
            wishlist.addCard(card); // Optional: Also add to wishlist if needed
            displayArea.append("✔ Card added to Binder: " + card.readCardInfo() + "\n");
            clearInputBox();
        } catch (NumberFormatException ex) {
            displayArea.append("❌ Price must be a valid number.\n");

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
}