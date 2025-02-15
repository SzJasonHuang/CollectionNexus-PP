package model;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import model.CardList;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCardWishList {

public class TestCardList {
    CardWishList testcardwishlist; 
    Card card1;
    Card card2;
    Card card3;

    @BeforeEach
    void runBefore(){
        card1 = new Card("bad", "Rare", "MJ", "Basketball", 10);
        card2 = new Card("decent", "Mythic", "Kobe", "Basketball", 100);
        card3 = new Card("good", "Uncommon", "Messi","Soccer", 1);
        testcardwishlist = new CardWishList();
    }

    @Test
    void testCardWishListConstructor() {
        testcardwishlist = new CardWishList();
        assertEquals(0,testcardwishlist.getWishList().size());
    }

    @Test
    void testAddCardToWishList() {
        testcardwishlist.addCardToWishList(card1);
        testcardwishlist.addCardToWishList(card2);
        testcardwishlist.addCardToWishList(card3);
        assertEquals(3,testcardwishlist.getWishList().size());
        assertEquals(card1,testcardwishlist.getWishList().get(0));
        assertEquals(card2,testcardwishlist.getWishList().get(1));
        assertEquals(card3,testcardwishlist.getWishList().get(2));
    }

    @Test
    void testAddCardToWishListTwice() {
        testcardwishlist.addCardToWishList(card1);
        assertEquals(1,testcardwishlist.getWishList().size());
        assertEquals(card1,testcardwishlist.getWishList().get(0));
        testcardwishlist.addCardToWishList(card1);
        assertEquals(1,testcardwishlist.getWishList().size());
        assertEquals(card1,testcardwishlist.getWishList().get(0));
        assertEquals(2,card1.getQuantity());
    }




    @Test
    void testRemoveCardFromWishList() {
        testcardwishlist.addCardToWishList(card1);
        assertEquals(1,testcardwishlist.getWishList().size());
        testcardwishlist.removeCardFromWishList("MJ");
        assertEquals(0,testcardwishlist.getWishList().size());

    }

    @Test
    void testRemoveMutipleCardsFromWishList() {
        testcardwishlist.addCardToWishList(card1);
        testcardwishlist.addCardToWishList(card2);
        assertEquals(2,testcardwishlist.getWishList().size());
        testcardwishlist.removeCardFromWishList("MJ");
        assertEquals(1,testcardwishlist.getWishList().size());
        assertFalse(testcardwishlist.getWishList().contains(card1));
        testcardwishlist.removeCardFromWishList("Kobe");
        assertEquals(0,testcardwishlist.getWishList().size());
        assertFalse(testcardwishlist.getWishList().contains(card2));
    }
}
}
