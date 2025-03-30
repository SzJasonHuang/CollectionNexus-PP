package model;

import persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class CardList implements Writable {
    private ArrayList<Card> mybinder;

    //EFFECTS: Constructs a binder/wishlist with no cards in it.
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
            EventLog.getInstance().logEvent(new Event("Duplicate Added."));
            return false;
        } else {
            mybinder.add(newcard);
            EventLog.getInstance().logEvent(new Event("A card was added by the user."));
            return true;
        }
    }

    // EFFECTS: Search mybinder for a card, return the card if the card is found,
    // return null if not found.
    public Card searchCard(String cardname) {

        for (Card targetcard : mybinder) {
            if (targetcard.getName().equals(cardname)) {
                EventLog.getInstance().logEvent(new Event("User's search target was found."));
                return targetcard;
            }
        }
        EventLog.getInstance().logEvent(new Event("User's search target was not found."));
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
        EventLog.getInstance().logEvent(new Event("User viewed all cards in binder || all cards in wishlist"));
        return mybinder;
    }

    // MODIFIES: this
    // EFFECTS: removes a single card from the user's binder/wishlist
    public Boolean removeCardFromList(String target) {
        Iterator<Card> iterator = mybinder.iterator();
        while (iterator.hasNext()) {
            Card card = iterator.next();
            if (card.getName().equalsIgnoreCase(target)) {
                iterator.remove();
                EventLog.getInstance().logEvent(new Event("User successfully removed a card."));
                return true;
            }
        }
        EventLog.getInstance().logEvent(new Event("Remove of the desired card was unsuccessful"));
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", "binder");
        json.put("cards", cardsToJson());
        return json;
    }
    
    
    // EFFECTS: returns cards in this binder/wishlist as a JSON array
    private JSONArray cardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Card c : mybinder) {
            jsonArray.put(c.toJson());
        }
        
        return jsonArray;
    }

}
