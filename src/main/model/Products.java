package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents Products with a list of all products in metaplace (initially 0)
public class Products {
    private String name;        // The product name
    private double price;       // The product price
    private String description; // The product description

    // MODIFIES: this
    // EFFECTS: name is set to name, price is set
    //          to price, description is set to description
    public Products(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;

    }

    // EFFECTS: returns product price
    public double getPrice() {
        return price;
    }

    // EFFECTS: returns product name
    public String getName() {
        return name;
    }

    // EFFECTS: returns product description
    public String getDescription() {
        return description;
    }


}