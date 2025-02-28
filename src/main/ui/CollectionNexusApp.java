package ui;

import model.CardList;
import model.Card;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;
import persistence.*;

//Code below took inspiration from TellerApp 
// 

//Connection Nexus App
public class CollectionNexusApp {
    private static final String JSON_STORE_binder = "./data/binder.json";
    private static final String JSON_STORE_wishlist = "./data/wishlist.json";
    private CardList binder;
    private CardList wishlist;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonWriter jsonWriter1;
    private JsonReader jsonReader;
    private JsonReader jsonReader1;

    // EFFECTS: Runs the CollectionNexus App
    public CollectionNexusApp() throws FileNotFoundException {
        jsonReader = new JsonReader(JSON_STORE_binder);
        jsonReader1 = new JsonReader(JSON_STORE_wishlist);
        jsonWriter = new JsonWriter(JSON_STORE_binder);
        jsonWriter1 = new JsonWriter(JSON_STORE_wishlist);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: Starts the app
    public void runApp() {
        boolean terminate = false;
        String command = null;

        createAccount();

        while (!terminate) {
            displayMenu();
            command = input.nextLine().trim(); // Use nextLine().trim()

            if (command.equals("e")) {
                terminate = true;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Processes user's command
    private void processCommand(String command) {
        if (command.equals("a")) {
            addCardToBinder();
        } else if (command.equals("b")) {
            addCardToWishList();
        } else if (command.equals("c")) {
            openBinder();
        } else if (command.equals("d")) {
            openWishList();
        } else {
            System.out.println("Selection is not valid, please enter the alphabet that corresponds the command");
        }
    }

    // MODIFIES: this
    // EFFECTS: Initializes an account for the user, which includes a list for
    // user's wishlist and
    // a list for user's cards (Call it binder)
    public void createAccount() {
        binder = new CardList();
        wishlist = new CardList();
        input = new Scanner(System.in);
    }

    // EFFECTS: Displays main menu to user.
    public void displayMenu() {
        System.out.println("Select from: ");
        System.out.println("a. Add Card to Binder");
        System.out.println("b. Add Card to Wishlist");
        System.out.println("c. MyBinder");
        System.out.println("d. MyWishList");
        System.out.println("e. Quit");
    }

    // MODIFIES: this
    // EFFECTS: Adds a single card to user's collection.
    public void addCardToBinder() {
        System.out.println("Please enter your card's condition, rarity, name, type, and price:");
        System.out.print("Name: ");
        String name1 = input.nextLine();
        System.out.print("Condition: ");
        String condition1 = input.nextLine();
        System.out.print("Rarity: ");
        String rarity1 = input.nextLine();
        System.out.print("Type: ");
        String type1 = input.nextLine();
        System.out.print("Please enter an integer as price: ");
        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer:");
            input.next();
        }
        int price1 = input.nextInt();
        input.nextLine();

        Card currentcard = new Card(condition1, rarity1, name1, type1, price1);
        binder.addCard(currentcard);

        System.out.println("Card '" + name1 + "' has been added to your binder");
    }

    // MODIFIES: this
    // EFFECTS: Adds a single card to user's wishlist.
    public void addCardToWishList() {
        System.out.println("Please enter your card's condition, rarity, name, type, and price:");

        System.out.print("Name: ");
        String name1 = input.nextLine();

        System.out.print("Condition: ");
        String condition1 = input.nextLine();

        System.out.print("Rarity: ");
        String rarity1 = input.nextLine();

        System.out.print("Type: ");
        String type1 = input.nextLine();

        System.out.print("Please enter an integer as price: ");

        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter an integer:");
            input.next();
        }
        int price1 = input.nextInt();
        input.nextLine();

        Card currentcard = new Card(condition1, rarity1, name1, type1, price1);
        wishlist.addCard(currentcard);

        System.out.println("Card '" + name1 + "' has been added to your wishlist");
    }

    // EFFECTS: Displays the Current user's binder menu and
    // a list of options to do with it.
    public void openBinder() {
        String command1 = null;
        Boolean terminate1 = false;
        while (!terminate1) {
            displayBinderMenu();
            command1 = input.nextLine().trim();
            if (command1.equals("f")) {
                terminate1 = true;
            } else {
                processCommandForBinder(command1);
            }
        }
    }

    // EFFECTS: Displays a menu of the options an user could do with his/her
    // binder.
    public void displayBinderMenu() {
        System.out.println("Select from: ");
        System.out.println("a. Search for a card in my binder");
        System.out.println("b. Value Calculator");
        System.out.println("c. Get current collection as a list of card");
        System.out.println("d. Save my binder to file");
        System.out.println("e. Load my binder to file");
        System.out.println("f. Back to main menu");
    }

    // MODIFIES: this
    // EFFECTS: processes user's command in the binder menu, based on user's
    // selection of alphabet
    public void processCommandForBinder(String command) {
        if (command.equals("a")) {
            searchCardInBinder();
        } else if (command.equals("b")) {
            calculateValueInBinder();
        } else if (command.equals("c")) {
            getListOfCardInCollection();
        } else if (command.equals("d")) {
            saveBinder();
        } else if (command.equals("e")) {
            loadBinder();
        } else {
            System.out.println("Selection is not valid, please enter the alphabet that corresponds the command");
        }
    }

    // EFFECTS: Given name of card, search user's binder for a card that matches the
    // name and
    // displays the details to the owner if found.
    public void searchCardInBinder() {
        String target = null;
        Boolean keepsearching = true;
        while (keepsearching) {
            System.out.println("Enter the name of the card you want to search: ");
            target = input.nextLine();
            if (binder.searchCard(target) == null) {
                System.out.println("The card you're searching does not exist!");
            } else {
                System.out.println("Details: " + binder.searchCard(target).getQuantity() + " copy/copies of "
                        + binder.searchCard(target).readCardInfo());
            }
            System.out.println("Search for more cards? yes/no");
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("yes")) {
                keepsearching = true;
            } else if (answer.equalsIgnoreCase("no")) {
                keepsearching = false;
            } else {
                System.out.println("Command invalid, please type yes/no for your answer");
            }
        }
    }

    // EFFECTS: Calculate the total value of the input list if cards are in user's
    // binder.

    public void calculateValueInBinder() {
        while (true) {
            System.out.println("Enter name of cards separated by spaces: ");
            String line = input.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("No cards entered, please try again.");
                continue;
            }

            int totalValue = binder.calculateValue(new ArrayList<>(Arrays.asList(line.split(" "))));
            System.out.println("The total value of your selected card is " + totalValue + " Dollars");

            System.out.println("Do you still need to calculate more card values? yes/no");
            if (!input.next().trim().equalsIgnoreCase("yes")) {
                break;
            }
            input.nextLine();
        }
    }

    // EFFECTS: Displays all card's information in the owner's binder
    public void getListOfCardInCollection() {
        for (Card indi : binder.getMyBinder()) {
            System.out.println(indi.readCardInfo());
        }
    }

    // MODIFIES: this
    // EFFECTS: Displays the Current user's wishlist and give
    // a list of options to do with it.
    public void openWishList() {
        String command1 = null;
        Boolean terminate1 = false;
        while (!terminate1) {
            displayWishListMenu();
            command1 = input.nextLine().trim();
            if (command1.equals("f")) {
                terminate1 = true;
            } else {
                processCommandForWishList(command1);
            }
        }
    }

    // EFFECTS: Displays a menu of the options an user could do with his/her
    // wishlist.
    public void displayWishListMenu() {
        System.out.println("Select from:");
        System.out.println("a. Add a new card to the wishlist");
        System.out.println("b. Remove a card from the wishlist");
        System.out.println("c. View my wishlist");
        System.out.println("d. Save my wishlist to file");
        System.out.println("e. Load my wishlist to file");
        System.out.println("f. Back to main menu");
    }

    // EFFECTS: Processes the command from user given from the wishlist menu.
    public void processCommandForWishList(String command) {
        if (command.equals("a")) {
            addCardToWishList();
        } else if (command.equals("b")) {
            removeCardFromWishList();
        } else if (command.equals("c")) {
            printCurrentLists("wishlist");
        } else if (command.equals("d")) {
            saveWishlist();
        } else if (command.equals("e")) {
            loadWishlist();
        } else {
            System.out.println("Selection is not valid, please enter the alphabet that corresponds the command");
        }
    }

    // MODIFIES: this
    // EFFECTS: Based on the user's input, remove target card from user's wishlist
    public void removeCardFromWishList() {
        if (wishlist.getMyBinder().size() == 0) {
            System.out.println("Your wishlist is empty. Nothing to remove.");
            return;
        }
        System.out.println("Enter the name of the card you want to remove: ");
        String target = input.nextLine().trim();
        Card cardToRemove = wishlist.searchCard(target);
        if (cardToRemove == null) {
            System.out.println("The card '" + target + "' is not in your wishlist.");
        } else {
            wishlist.removeCardFromList(cardToRemove.getName());
            System.out.println("Card '" + target + "' has been removed from your wishlist.");
        }
    }

    // EFFECTS: Print out all the cards in a user's binder or wishlist
    public void printCurrentLists(String type) {
        if (type.equalsIgnoreCase("binder")) {
            System.out.println("Cards in binder:");
            for (Card card : binder.getMyBinder()) {
                System.out.println(card.readCardInfo());
            }
        } else {
            System.out.println("Cards in wishlist:");
            for (Card card : wishlist.getMyBinder()) {
                System.out.println(card.readCardInfo());
            }
        }
    }

    // EFFECTS: saves binder to file
    public void saveBinder() {
        try {
            jsonWriter.open();
            jsonWriter.write(binder);
            jsonWriter.close();
            System.out.println("Saved Binder to " + JSON_STORE_binder);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_binder);
        }
    }

    // EFFECTS: saves wishlist to file
    public void saveWishlist() {
        try {
            jsonWriter1.open();
            jsonWriter1.write(wishlist);
            jsonWriter1.close();
            System.out.println("Saved Wishlist to " + JSON_STORE_wishlist);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_wishlist);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads binder to file
    public void loadBinder() {
        try {
            binder = jsonReader.read();
            System.out.println("Loaded Binder from" + JSON_STORE_binder);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_binder);
        }

    }

    // MODIFIES: this
    // EFFECTS: loads wishlist to file
    public void loadWishlist() {
        try {
            wishlist = jsonReader1.read();
            System.out.println("Loaded Wishlist from" + JSON_STORE_wishlist);
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_wishlist);
        }
    }
}
