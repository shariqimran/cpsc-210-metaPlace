package persistence;

import model.Account;
import model.Products;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // method taken from JsonSerializationDemo, (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Account read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseAccount(jsonObject);
    }

    // method taken from JsonSerializationDemo, (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // method taken from JsonSerializationDemo, (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    // EFFECTS: parses account from JSON object and returns it
    private Account parseAccount(JSONObject jsonObject) {
        double balance = jsonObject.getDouble("Balance");
        Account ac = new Account();
        ac.addMoney(balance);
        addStuff(ac, jsonObject);
        addProductOnMarketPlace(ac, jsonObject);
        return ac;
    }

    // method taken from JsonSerializationDemo, (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    // MODIFIES: ac
    // EFFECTS: parses purchases from JSON object and adds them to accounts
    public void addStuff(Account ac, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("purchases");
        for (Object json : jsonArray) {
            JSONObject nextPurchase = (JSONObject) json;
            addPurchase(ac, nextPurchase);
        }

    }

    // method taken from JsonSerializationDemo, (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    // MODIFIES: ac
    // EFFECTS: parses purchases from JSON object and adds it to account
    private void addPurchase(Account ac, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        String description = jsonObject.getString("description");
        Products product = new Products(name, price, description);
        ac.purchase(product);
    }

    // MODIFIES: ac
    // EFFECTS: parses purchases from JSON object and adds it to account
    public void addProductOnMarketPlace(Account ac, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Products");
        for (Object json : jsonArray) {
            JSONObject nextProduct = (JSONObject) json;
            addProduct(ac, nextProduct);
        }
    }

    // MODIFIES: ac
    // EFFECTS: parses purchases from JSON object and adds it to account
    public void addProduct(Account ac, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double price = jsonObject.getDouble("price");
        String description = jsonObject.getString("description");
        Products p = new Products(name, price, description);
        ac.addToProducts(p);
    }

    // EFFECTS: return List of purchases
    public static List<Products> getPurchases(Account ac) {
        return ac.getPurchase();
    }
}
