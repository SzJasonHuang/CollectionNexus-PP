package model;

public class Card {
    private String condition;
    private String rarity;
    private String name;
    private String type;
    private int price; 
    private Boolean sold;

 

    public Card (String condition, String rarity, String name, String type, int price) {
        this.condition = condition; 
        this.rarity = rarity; 
        this.name = name;
        this.type = type;
        this.price = price; 
        this.sold = false; 
    }

    public String readCardInfo() {
        String cardinfo = name + ", " + type + ", " + rarity + ", " + condition + ", " + "$" + Integer.toString(price);
        return cardinfo;         
    }

    
    public String getCondition() {
        return condition;
    }

    public String getRarity() {
        return rarity;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public boolean getSold() {
        return sold;
    }

}
