package model;

//Has all the products in the marketplace
//Has product descriptions and prices
//Has product specific discount?(Lowest seller would go for said product)
//Can list your own product with specific price and lowest you would go

import java.util.ArrayList;
import java.util.List;

public class Products {
    private String name;
    private double price;
    private String description;
    private double discount;
    private List<Products> allProducts;

    // EFFECTS: creates empty list
    public Products() {
        allProducts = new ArrayList<Products>();
    }

    // REQUIRES: price needs to be of integer type
    // MODIFIES: this
    // EFFECTS: creates a product
    public Products(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public void addProduct(Products products) {
        allProducts.add(products);
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public List<Products> getAllProducts() {
        return allProducts;
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public Products returnIndex(int i) {
        Products chosen = allProducts.get(i - 1);
        return chosen;
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: remove the item from the metaverse
    public void removeItem(Products item) {
        allProducts.remove(item);
    }
//
//    @Override
//    public String toString() {
//        return getName();
//    }
}
