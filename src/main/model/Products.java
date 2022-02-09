package model;

//Creates Products with a list of all products metaplace (initially 0)
//Has methods to initialize a product, get price, get name and get description of product

import java.util.ArrayList;
import java.util.List;

public class Products {
    private String name;
    private double price;
    private String description;


    public Products() {
        List<Products> allProducts = new ArrayList<>();
    }

    // REQUIRES: price needs to be of integer type
    // MODIFIES: this
    // EFFECTS: creates a product
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
