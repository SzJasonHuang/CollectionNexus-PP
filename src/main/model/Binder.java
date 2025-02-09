package model;

import java.util.ArrayList;
import model.Card;

public class Binder {
    private ArrayList<Card> mybinder; 
    private Boolean emptyornot; 

    public Binder(){
        mybinder = new ArrayList<Card>();
    }
    
    //get all cards that are unsold
    public ArrayList<Card> getCards() {
        ArrayList<Card> resultlist = new ArrayList<Card>();
        for (Card thiscard : mybinder) {
            if (!thiscard.getSold()) {
                resultlist.add(thiscard);
            }
        }
        return resultlist;
    }

    //see all cards in the collection
    public void seeTheCollection(){
        for (Card indiviCard : mybinder){
            int index = 1;
            String number = Integer.toString(index) + ".";
            System.out.println(number + indiviCard);
            index++;
        }
    }


    public Boolean setEmptyOrNot(){
        emptyornot = false;
        return emptyornot; 
    }
}
