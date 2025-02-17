package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import model.CardList;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCardList {
    CardList testcardlist; 
    Card card1;
    Card card2;
    Card card3;

    @BeforeEach
    void runBefore() {
        card1 = new Card("bad", "rare", "jason", "baseball", 10);
        card2 = new Card("decent", "mythic", "Vincent", "Basketball", 100);
        card3 = new Card("decent", "Uncommon", "Michael","Basketball", 1);
        testcardlist = new CardList();
    }

    @Test
    void testCardListConstructor() {
        testcardlist = new CardList();
        assertEquals(0,testcardlist.getMyBinder().size());
    }

    @Test
    void testCardListAddCardDidNotHave() { 
        assertTrue(testcardlist.addCard(card1));
        assertEquals(1,testcardlist.getMyBinder().size());
    }

    @Test
    void testCardListAddCardAlreadyExsists() {
        assertTrue(testcardlist.addCard(card1));
        assertEquals(1,testcardlist.getMyBinder().size());
        assertFalse(testcardlist.addCard(card1));
        assertEquals(1,testcardlist.getMyBinder().size());

    }
    
    @Test
    void testCardListCalculateValue() {
        testcardlist.addCard(card1);
        testcardlist.addCard(card2);
        testcardlist.addCard(card3);
        ArrayList<String> testlist = new ArrayList<String>(); 
        testlist.add(card1.getName());
        testlist.add(card2.getName());
        testlist.add(card3.getName());
        assertEquals(111,testcardlist.calculateValue(testlist));
    }

    @Test
    void testCardListCalculateValueSameCard() {
        testcardlist.addCard(card1);
        testcardlist.addCard(card1);
        testcardlist.addCard(card2);
        ArrayList<String> testlist = new ArrayList<String>(); 
        testlist.add(card1.getName());
        testlist.add(card2.getName());
        assertEquals(120,testcardlist.calculateValue(testlist));
    }

    @Test
    void testCardListCalculateValueNotFound() {
        testcardlist.addCard(card1);
        testcardlist.addCard(card1);
        testcardlist.addCard(card2);
        ArrayList<String> testlist = new ArrayList<String>(); 
        testlist.add(card3.getName());
        assertEquals(0,testcardlist.calculateValue(testlist));
    }

    @Test
    void testCardListSearchCardFound() {
        testcardlist.addCard(card1);
        assertEquals(card1,testcardlist.searchCard(card1.getName()));
    }

    @Test
    void testCardListSearchCardNotExist() {
        testcardlist.addCard(card1);
        assertEquals(null,testcardlist.searchCard("Kevin"));
    }

    @Test
    void testRemoveCardFromWishList() {
        testcardlist.addCard(card1);
        assertEquals(1,testcardlist.getMyBinder().size());
        testcardlist.removeCardFromList("jason");
        assertEquals(0,testcardlist.getMyBinder().size());
    }

    @Test
    void testRemoveMutipleCardsFromWishList() {
        testcardlist.addCard(card1);
        testcardlist.addCard(card2);
        assertEquals(2,testcardlist.getMyBinder().size());
        testcardlist.removeCardFromList("jason");
        assertEquals(1,testcardlist.getMyBinder().size());
        assertFalse(testcardlist.getMyBinder().contains(card1));
        testcardlist.removeCardFromList("Vincent");
        assertEquals(0,testcardlist.getMyBinder().size());
        assertFalse(testcardlist.getMyBinder().contains(card2));
    }

    @Test
    void testRemoveCardFromWishListInvalid() {
        testcardlist.addCard(card1);
        testcardlist.addCard(card2);
        assertEquals(2,testcardlist.getMyBinder().size());
        testcardlist.removeCardFromList("monkey");
        assertEquals(2,testcardlist.getMyBinder().size());
        assertTrue(testcardlist.getMyBinder().contains(card1));
        assertTrue(testcardlist.getMyBinder().contains(card2));
    }
}
