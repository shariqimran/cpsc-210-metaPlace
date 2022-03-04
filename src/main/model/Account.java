package model;

import org.json.JSONObject;
import persistence.Writable;
import ui.MetaplaceApp;

import java.util.*;

// Represents an Account with a list of your purchases (0 initially) and your current balance (0 initially)
public class Account implements Writable {

    private double balance;              // Current balance of account
    private List<Products> purchases;    // Current purchases of account
    private List<Products> products;     // list of all products on the app

    // EFFECTS: creates a List, sets balance to 0
    public Account() {
        purchases = new ArrayList<>();
        balance = 0;
        products = new ArrayList<>();
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: returns true if amount > 0, false otherwise
    public boolean reload(double amount) {
        return (amount > 0);
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount to balance
    public void addMoney(double amount) {
        balance += amount;
    }

    // TODO: Tests design
    public void addToProducts(Products p) {
        if (!products.contains(p)) {
            products.add(p);
        }

    }

    public List<Products> getProducts() {
        return products;
    }


    // REQUIRES: balance >= item.getPrice()
    // MODIFIES: this
    // EFFECTS: adds chosen item to purchases list and subtracts price of item from balance
    public void purchase(Products item) {
        if (item.getPrice() <= balance) {
            purchases.add(item);
            balance -= item.getPrice();
        }
    }

//    public void addToPurchases(Products item) {
//        purchases.add(item);
//    }

    // EFFECTS: returns current balance
    public double getBalance() {
        return balance;
    }

    // EFFECTS: returns list of current purchases
    public List<Products> getPurchase() {
        return purchases;
    }



    @Override
    public JSONObject toJson() {
        List<String> titlesOfPurchases = new ArrayList<>();

        for (Products p : purchases) {
            titlesOfPurchases.add(p.getName());
        }

        JSONObject json = new JSONObject();
        json.put("Balance", balance);
        json.put("purchases", purchases);

        for (Products p : purchases) {
            if (titlesOfPurchases.contains(p.getName())) {
                products.remove(p);
            }
        }
        json.put("Products", products);

        return json;
    }


    // EFFECTS: returns things in this workroom as a JSON array
//    private JSONArray purchasesToJson() {
//        JSONArray jsonArray = new JSONArray();
//
//        for (Products p : purchases) {
//            jsonArray.put(p.toJson());
//        }
//
//        return jsonArray;
//    }
}
