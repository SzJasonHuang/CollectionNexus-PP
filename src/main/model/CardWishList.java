package model;

import java.util.List;
import java.util.ArrayList;

public class CardWishList {
    private ArrayList<Card> mywishlist; 

    // Creates a wishlist of cards with no cards in it
    public CardWishList() {
        mywishlist = new ArrayList<Card>();
    }

    //MODIFIES: this, Card
    //EFFECTS: adds a single card to the user's wishlist, a card's quantity is increased
    //by one if it had been added twice and it would not be added to the wishlist. 
    public void addCardToWishList(Card wishcard) {
        mywishlist.add(wishcard);

    }

    //REQUIRES: mywishlist must contain the card trying to be removed
    //MODIFIES: this
    //EFFECTS: removes a single card from the user's wishlist
    public void removeCardFromWishList(String wishcard) {
        for(Card indicard : mywishlist){
            if(indicard.getName() == wishcard){
                mywishlist.remove(indicard);
            }
        }
    }

    //EFFECTS: returns user's wishlist
    public ArrayList<Card> getWishList() {
        return mywishlist;
    }
    
}
