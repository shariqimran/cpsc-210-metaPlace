package model;

//Has all the products in the marketplace
//Has product descriptions and prices
//Has product specific discount?(Lowest seller would go for said product)
//Can list your own product with specific price and lowest you would go

import java.util.ArrayList;
import java.util.List;

public class Products {
    private String name;
    private int price;
    private String description;
    private int discount;
    private List<Products> allProducts;

    public Products() {
        allProducts = new ArrayList<Products>();
    }

    public Products(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void addProduct(Products products) {
        allProducts.add(products);
    }

    public List<Products> getAllProducts() {
        return allProducts;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
    public Products returnIndex(int i) {
        Products chosen = allProducts.get(i - 1);
        return chosen;
    }

    @Override
    public String toString() {
        return getName();
    }
}
