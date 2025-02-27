package model;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistence.JsonReader;

public class TestJsonReader {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CardList cl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCardList(){
        JsonReader reader = new JsonReader("./data/testReaderEmptyCardList.json");
        try {
            CardList cl = reader.read();
            assertEquals(0, cl.getMyBinder().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderRealCardList() {
        JsonReader reader = new JsonReader("./data/testReaderRealCardList.json");
        try {
            CardList cl = reader.read();
            ArrayList<Card> cards = cl.getMyBinder();
            assertEquals(2, cards.size());
            Card card1 = new Card("Near Mint", "Rare", "Kobe", "Basketball", 20);
            Card card2 = new Card("Near Mint", "Rare", "Lebron", "Basketball", 100);
            assertEquals("Near Mint",cards.get(0).getCondition());
            assertEquals("Rare",cards.get(0).getRarity());
            assertEquals("Kobe",cards.get(0).getName());
            assertEquals("Basketball",cards.get(0).getType());
            assertEquals(20,cards.get(0).getPrice());
            assertEquals(1,cards.get(0).getQuantity());
            assertEquals(false,cards.get(0).getSold());

            assertEquals("Near Mint",cards.get(1).getCondition());
            assertEquals("Rare",cards.get(1).getRarity());
            assertEquals("Lebron",cards.get(1).getName());
            assertEquals("Basketball",cards.get(1).getType());
            assertEquals(100,cards.get(1).getPrice());
            assertEquals(2,cards.get(1).getQuantity());
            assertEquals(false,cards.get(1).getSold());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
    

}