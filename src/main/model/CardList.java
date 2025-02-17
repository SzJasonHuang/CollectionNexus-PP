package model;

import java.util.ArrayList;
import java.util.Iterator;

public class CardList {
    private ArrayList<Card> mybinder;

    public CardList() {
        mybinder = new ArrayList<Card>();
    }

    // MODIFIES: this, Card
    // EFFECTS: Add a card into the owner's binder, if the added card's
    // name already exsist in the collection, increase the card's
    // quantity by one instead of mybinder.add() and return false.
    public Boolean addCard(Card newcard) {
        if (mybinder.contains(newcard)) {
            newcard.increaseQuantity();
            return false;
        } else {
            mybinder.add(newcard);
            return true;
        }
    }

    // EFFECTS: Search mybinder for a card, return the card if the card is found,
    // return null if not found.
    public Card searchCard(String cardname) {

        for (Card targetcard : mybinder) {
            if (targetcard.getName().equals(cardname)) {
                return targetcard;
            }
        }
        return null;
    }

    // EFFECTS: Return the value of the list of cards names given,
    // returns 0 if no cards in the input list was found in the collection
    public int calculateValue(ArrayList<String> loc) {
        int totalvalue = 0;
        for (String s : loc) {
            Card card = searchCard(s);
            if (card != null) {
                totalvalue += card.getPrice() * card.getQuantity();
            }
        }
        return totalvalue;
    }

    // EFFECTS: returns the user's collection.
    public ArrayList<Card> getMyBinder() {
        return mybinder;
    }

    // MODIFIES: this
    // EFFECTS: removes a single card from the user's binder/wishlist
    public void removeCardFromList(String target) {
        Iterator<Card> iterator = mybinder.iterator();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getName().equalsIgnoreCase(target)) {
                iterator.remove();
                return;
            }
        }
    }

}
