package model;

// Represents a card having with its name, current condition, card rarity, the type of card, 
// the price represented by an integer, current stock status of the card and finally the quantity of the card. 
public class Card {
    private String condition;
    private String rarity;
    private String name;
    private String type;
    private int price; 
    private Boolean sold;
    private int quantity;

 
    // EFFECTS: Create a new card based on the its condition, rarity, name, type and price. The newly created
    // card is not sold and the initial quantity is set to 1. 
    public Card (String condition, String rarity, String name, String type, int price) {
        this.condition = condition; 
        this.rarity = rarity; 
        this.name = name;
        this.type = type;
        this.price = price; 
        this.sold = false; 
        quantity = 1;
        
    }

    // EFFECTS: Concatenates relavent informations information of a card into one string 
    public String readCardInfo() {
        String cardinfo = name + ", " + type + ", " + rarity + ", " + condition + ", " + "$" + Integer.toString(price);
        return cardinfo;         
    }

    // MODIFIES: this
    // EFFECTS: increase the quantity of card by one
    public void increaseQuantity() {
        this.quantity++;
    }
    
    // EFFECTS: returns the condition of card 
    public String getCondition() {
        return condition;
    }

    // EFFECTS: returns the rarity of card
    public String getRarity() {
        return rarity;
    }

    // EFFECTS: returns the name of card
    public String getName() {
        return name;
    }

    // EFFECTS: returns the type of card
    public String getType() {
        return type;
    }

    // EFFECTS: returns the price of card
    public int getPrice() {
        return price;
    }

    // EFFECTS: returns the sold status of card
    public boolean getSold() {
        return sold;
    }
    

}
