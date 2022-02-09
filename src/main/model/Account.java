package model;

//Creates Account with a list of your purchases (0 initially) and your current balance
//Has methods to purchase a product and add funds.

import java.util.*;

public class Account {

    private static double balance;
    private static List<Products> purchases;

    // EFFECTS: Creates an empty List
    public Account() {
        purchases = new ArrayList<>();
        balance = 0;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount to balance
    public void reload(double amount) {
        balance += amount;
    }

    // REQUIRES: balance >= item.getPrice()
    // MODIFIES: this
    // EFFECTS: Adds chosen item to purchases list and subtracts price of item from balance
    public void purchase(Products item) {
        purchases.add(item);
        balance -= item.getPrice();
    }

    // EFFECTS: returns current balance
    public double getBalance() {
        return balance;
    }

    // EFFECTS: returns list of current purchases
    public List<Products> getPurchase() {
        return purchases;
    }

}
