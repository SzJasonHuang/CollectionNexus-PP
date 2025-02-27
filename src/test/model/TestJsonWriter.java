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
import persistence.JsonWriter;

public class TestJsonWriter {
    @Test
    void testWriterInvalidFile() {
        try {
            CardList cl = new CardList();
            JsonWriter writer = new JsonWriter("./data/my\\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCardList() {
        try {
            CardList cl = new CardList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCardList.json");
            writer.open();
            writer.write(cl);
            writer.close();
            
            JsonReader reader = new JsonReader("./data/testWriterEmptyCardList.json");
            cl = reader.read();
            assertEquals(0,cl.getMyBinder().size());
            
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterRealCardList() {
        try {
            Card card1 = new Card("Near Mint", "Rare", "Dragon", "MTG", 20);
            Card card2 = new Card("Near Mint", "Rare", "Merfolk", "MTG", 100);
            card2.increaseQuantity();

            CardList cl = new CardList();
            cl.addCard(card1);
            cl.addCard(card2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            cl = reader.read();
            ArrayList<Card> thiscardlist = cl.getMyBinder();

            assertEquals(2, thiscardlist.size());
            assertEquals("Near Mint",thiscardlist.get(0).getCondition());
            assertEquals("MTG",thiscardlist.get(0).getType());
            assertEquals("Dragon",thiscardlist.get(0).getName());
            assertEquals("Rare",thiscardlist.get(0).getRarity());
            assertEquals(20,thiscardlist.get(0).getPrice());
            assertEquals(false,thiscardlist.get(0).getSold());
            assertEquals(1,thiscardlist.get(0).getQuantity());
            assertEquals("Near Mint",thiscardlist.get(1).getCondition());
            assertEquals("MTG",thiscardlist.get(1).getType());
            assertEquals("Merfolk",thiscardlist.get(1).getName());
            assertEquals("Rare",thiscardlist.get(1).getRarity());
            assertEquals(100,thiscardlist.get(1).getPrice());
            assertEquals(false,thiscardlist.get(1).getSold());
            assertEquals(2,thiscardlist.get(1).getQuantity());
            

        } catch (IOException e) {
            fail("Exception should not have been thrown");

            }
    }
    
}
