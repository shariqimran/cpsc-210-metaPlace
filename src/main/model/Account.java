package model;

//Add funds to your account so you can purchase desired product
//Gives cashback rewards
//Can buy product by negotiating
//Can see your purchases

import java.util.*;

public class Account {

//    public static int POINTS_NEEDED_FOR_CASH_BACK = 2000;
//    public static int REWARD_POINTS_PER_CENT_CHARGED = 1;
//    public static int CASH_BACK_REWARD = 10;
    private static double balance;
    private static List<Products> purchases;
//    private int rewardPoints;

    // EFFECTS: Creates an empty List
    public Account() {
        this.purchases = new ArrayList<Products>();
        this.balance = 0;
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


//    @SuppressWarnings("checkstyle:WhitespaceAround")
//    public List<Products> getPurchases() {
//        Products[] elements = new Products[purchases.size()];
//        for(int i = 0; i < elements.length; i++) {
//            elements[i] = purchases.get(i);
//        }
//        return purchases;
//    }

    // EFFECTS: returns list of current purchases
    public List<Products> getPurchase() {
        return purchases;
    }


//    @SuppressWarnings("checkstyle:WhitespaceAround")
//    public <purchases> String[] getAllElementsInCollection(purchases){
//        double[] elements = new double[purchases.size()];
//        for(int i = 0; i < elements.length; i++){
//            elements[i] = purchases.get(i);
//        }
//        return purchases;
//
//    }
}
