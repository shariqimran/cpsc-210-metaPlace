package model;

import java.util.*;

// Represents an Account with a list of your purchases (0 initially) and your current balance (0 initially)
public class Account {

    private double balance;                    // Current balance of account
    private final List<Products> purchases;    // Current purchases of account

    // EFFECTS: creates a List, sets balance to 0
    public Account() {
        purchases = new ArrayList<>();
        balance = 0;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: returns true if amount > 0, false otherwise
    public boolean reload(double amount) {
        return amount > 0;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount to balance
    public void addMoney(double amount) {
        balance += amount;
    }

    // REQUIRES: balance >= item.getPrice()
    // MODIFIES: this
    // EFFECTS: adds chosen item to purchases list and subtracts price of item from balance
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
