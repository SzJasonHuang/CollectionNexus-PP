package model;

import java.util.List;
import java.util.ArrayList;

public class CardWishList {
    private ArrayList<Card> mywishlist; 

    // Creates a wishlist of cards with no cards in it
    public CardWishList() {
        
    }

    //MODIFIES: this
    //EFFECTS: adds a single card to the user's wishlist
    public void addCardToWishList(Card wishcard) {

    }

    //MODIFIES: this
    //EFFECTS: removes a single card from the user's wishlist
    public void removeCardFromWishList(String wishcard) {

    }

    //EFFECTS: returns user's wishlist
    public ArrayList<Card> getWishList() {
        return mywishlist;
    }
    
}
