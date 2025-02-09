package model;

import static org.junit.Assert.assertEquals;
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
        assertEquals("Dragon, MTG, Rare, NM, $10",testCard.readCardInfo());
    }
}
