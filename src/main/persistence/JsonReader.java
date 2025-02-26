package persistence;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;
import model.CardList;

import org.json.*;

//Represents a reader that reads CardList from JSON Data stored in file
public class JsonReader {
    private String source;
    private CardList test = new CardList();

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CardList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CardList read() throws IOException {
       return test;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return "";
    }

    // EFFECTS: parses CardList from JSON object and returns it
    private CardList parseCardList(JSONObject jsonObject) {
        return test;
    }

    // MODIFIES: cl
    // EFFECTS: parses cards from JSON object and adds them to CardList
    private void addCards(CardList cl, JSONObject jsonObject) {
    }

    // MODIFIES: cl
    // EFFECTS: parses card from JSON object and adds it to CardList
    private void addCard(CardList cl, JSONObject jsonObject) {
    }


    
}
