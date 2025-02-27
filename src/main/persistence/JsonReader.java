package persistence;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Card;
import model.CardList;
import org.json.*;

//Represents a reader that reads CardList from JSON Data stored in file
public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CardList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CardList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCardList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses CardList from JSON object and returns it
    private CardList parseCardList(JSONObject jsonObject) {
        CardList cl = new CardList();
        addCards(cl,jsonObject);
        return cl;
    }


    // MODIFIES: cl
    // EFFECTS: parses cards from JSON object and adds them to CardList
    private void addCards(CardList cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cards");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addCard(cl, nextCard);
        }
    }

    // MODIFIES: cl
    // EFFECTS: parses card from JSON object and adds it to CardList
    private void addCard(CardList cl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        String condition = jsonObject.getString("condition");
        String rarity = jsonObject.getString("rarity");
        int price = jsonObject.getInt("price");
        boolean sold = jsonObject.getBoolean("sold");
        int quantity = jsonObject.getInt("quantity");
        Card indicard = new Card(condition,rarity,name,type,price);
        indicard.setPrice(price);
        indicard.setQuantity(quantity);
        indicard.setSold(sold);
        cl.addCard(indicard);
    }
    
}
