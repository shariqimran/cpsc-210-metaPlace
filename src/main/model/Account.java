package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents an Account with a list of your purchases (0 initially) and your current balance (0 initially)
public class Account implements Writable {

    private double balance;              // Current balance of account
    private List<Products> purchases;    // Current purchases of account
    private ArrayList<Products> products;     // list of all products on the app

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
    // REQUIRES: Product
    // MODIFIES: this
    // EFFECTS: adds product to products, logs event
    public void addToProducts(Products p) {
//        if (!products.contains(p)) {
        products.add(p);
        EventLog.getInstance().logEvent(new Event("Added your product: " + p.getName() + " to the Products List"));
//        }
//        return null;
    }

    public ArrayList<Products> getProducts() {
        return products;
    }

    //I use this for every purchase
    // REQUIRES: balance >= item.getPrice()
    // MODIFIES: this
    // EFFECTS: adds chosen item to purchases list and subtracts price of item from balance, logs event
    public void purchase(Products item) {
        if (item.getPrice() <= balance) {
            purchases.add(item);
            balance -= item.getPrice();
            EventLog.getInstance().logEvent(new Event("Purchased Item: " + item.getName()));
        }
    }

    // EFFECTS: returns current balance
    public double getBalance() {
        return balance;
    }

    // EFFECTS: returns list of current purchases
    public List<Products> getPurchase() {
        return purchases;
    }


    // EFFECTS: Removes already purchases Product from Products list
    // Try using any Sets
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

}
