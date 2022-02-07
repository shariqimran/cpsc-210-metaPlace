package model;

//Add funds to your account so you can purchase desired product
//Gives cashback rewards
//Can buy product by negotiating
//Can see your purchases

import java.util.*;

public class Account extends Products{

//    public static int POINTS_NEEDED_FOR_CASH_BACK = 2000;
//    public static int REWARD_POINTS_PER_CENT_CHARGED = 1;
//    public static int CASH_BACK_REWARD = 10;
    private static int balance;
    private static List<Products> purchases;
//    private int rewardPoints;

    public Account() {
        this.purchases = new ArrayList<Products>();
        this.balance = 0;
    }

    public void reload(int amount) {
        if (balance > 0) {
            balance += amount;
        }
    }

    public void purchase(Products item) {
//        if (item.getPrice() <= balance) {
//            balance -= item.getPrice();
            purchases.add(item);
        //}
    }

    public int getBalance() {
        return balance;
    }


    @SuppressWarnings("checkstyle:WhitespaceAround")
    public List<Products> getPurchases() {
        Products[] elements = new Products[purchases.size()];
        for(int i = 0; i < elements.length; i++) {
            elements[i] = purchases.get(i);
        }
        return purchases;
    }

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
