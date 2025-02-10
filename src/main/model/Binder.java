package model;

import java.util.ArrayList;
import model.Card;

public class Binder {
    private ArrayList<Card> mybinder; 
    private Boolean emptyornot; 

    public Binder(){
        mybinder = new ArrayList<Card>();
        emptyornot = true;
    }
    
    //get all cards that are unsold
    public ArrayList<Card> getCardsUnsold() {
        ArrayList<Card> resultlist = new ArrayList<Card>();
        for (Card thiscard : mybinder) {
            if (!thiscard.getSold()) {
                resultlist.add(thiscard);
            }
        }
        return resultlist;
    }
    //add card
    public void addCard(Card newcard) { 
        if (mybinder.contains(newcard)) {
            newcard.increaseQuantity();
        System.out.println("This card already exsists, quantity is increased by one!");
        }
        else {
            mybinder.add(newcard);
        }
    }

    //search the individual card
    public Card searchCard(Card cardname) {
        Card returncard = new Card("jj", "jj", "jj", "jj", 20); 
        boolean found = false;  
    
        for (Card targetcard : mybinder) {
            if (targetcard.getName().equals(cardname.getName())) { 
                found = true;  
                return targetcard; 
            }
        }

        if (!found) {
            System.out.println("Error: Card not found - " + cardname.getName());
        }
        return returncard; 
    }
    
    
    //see all cards in the collection
    public void seeTheCollection(){
        for (Card indiviCard : mybinder){
            int index = 1;
            String number = Integer.toString(index) + ".";
            System.out.println(number + indiviCard.readCardInfo());
            index++;
        }
    }


    public Boolean setEmptyOrNot(){
        emptyornot = false;
        return emptyornot; 
    }
}
