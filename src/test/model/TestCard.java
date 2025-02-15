package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCard {
    Card testCard;

    @BeforeEach
    void runBefore() {
        testCard = new Card("NM", "Rare", "Dragon", "MTG", 10);
    }

    @Test
    void readCardInfoTest() {
        String result = "Dragon, MTG, Rare, NM, $10";
        String store = testCard.readCardInfo();
        assertEquals(result,store);
    }

    @Test
    void increaseQuantityTest() { 
        assertEquals(1,testCard.getQuantity());
        testCard.increaseQuantity();
        assertEquals(2,testCard.getQuantity());
    }

    @Test
    void increaseQuantityTwiceTest() { 
        assertEquals(1,testCard.getQuantity());
        testCard.increaseQuantity();
        assertEquals(2,testCard.getQuantity());
        testCard.increaseQuantity();
        assertEquals(3,testCard.getQuantity());
    }
    
    @Test
    void getConditionTest() {
        String store = testCard.getCondition();
        assertEquals("NM",store);
        
    }

    @Test
    void getSoldTest() {
        Boolean status = testCard.getSold();
        assertFalse(status);
    }

    @Test
    void getPriceTest() {
        int store = testCard.getPrice();
        assertEquals(10,store);
        
    }

    @Test
    void getRarityTest() {
        String store = testCard.getRarity();
        assertEquals("Rare",store);
        
    }

    @Test
    void getNameTest() {
        String store = testCard.getName();
        assertEquals("Dragon",store);
    }

    @Test
    void getTypeTest() {
        String store = testCard.getType();
        assertEquals("MTG",store);
    }
    

}


