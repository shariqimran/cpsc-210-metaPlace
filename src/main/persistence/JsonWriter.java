package persistence;

import model.Account;
import model.Products;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
// Class taken from JsonSerializationDemo, (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
// Represents a writer that writes JSON representation of workroom to file

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // method taken from JsonSerializationDemo, (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // method taken from JsonSerializationDemo, (https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git)
    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(Account ac) {
        JSONObject json = ac.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
//    public void write2(List<Products> pl) {
//        JSONObject json = pl.toJson();
//        saveToFile(json.toString(TAB));
//    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

//    public JSONObject toJson() {
//        return null;
//    }
}
