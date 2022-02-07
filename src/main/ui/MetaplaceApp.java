package ui;

import model.*;

import java.util.*;

//display menu
public class MetaplaceApp {

    private Products metaplace;
    private Account account;
    private Products product1;
    private Products product2;

    private Scanner sc;


    public MetaplaceApp() {
        runMetaplace();
    }

    private void runMetaplace() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = sc.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMenuCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }


    private void init() {
        account = new Account();
        metaplace = new Products();
        product1 = new Products("Blue Shirt", 500, "Very Blue");
        product2 = new Products("Red Shirt", 500, "Very Red");
        metaplace.addProduct(product1);
        metaplace.addProduct(product2);
        sc = new Scanner(System.in);
    }

    @SuppressWarnings("checkstyle:LeftCurly")
    private void displayMenu() {
        System.out.println("-------METAPLACE-------");
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> View Products");
        System.out.println("\t2 -> List Products");
        System.out.println("\t3 -> View Cart");
        System.out.println("\t4 -> Wallet");
        System.out.println("\tQ -> Quit");
        System.out.println("-----------------------");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processMenuCommand(String command) {
        switch (command) {
            case "1":
                viewProducts();
                break;
            case "2":
                listProducts();
                break;
            case "3":
                viewPurchases();
                break;
            case "4":
                viewWallet();
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    @SuppressWarnings("checkstyle:MethodLength")
    private void viewProducts() {
        for (Products product : metaplace.getAllProducts()) {
            System.out.println("-----------------------");
            System.out.printf("Name: %s%nPrice: %.0f%nDescription: %s",
                    product.getName(), product.getPrice(), product.getDescription());
            System.out.println();
        }
        System.out.println("-----------------------");
        System.out.println("1) Purchase an Item");
        System.out.println("2) Return to Menu");
        System.out.println("-----------------------");
        String selection = sc.next();

        switch (selection) {
            case "1":
                purchaseItem();
                break;
            case "2":
                break;
            default:
                System.out.println("Wrong Input! Please try again!");
                viewProducts();
                break;
        }
    }

    private void purchaseItem() {
        System.out.println("Please enter the number of the item you wish to purchase: ");
        int selection = Integer.parseInt(sc.next());
        Products yourProduct = metaplace.returnIndex(selection);
        account.purchase(yourProduct);
        System.out.println("You have purchased: " + account.getPurchases());
        System.out.println("Thank you!");
        System.out.println("You are now returning to the main menu...");
        System.out.println();


    }
//        aList.indexOf("Apple"));
//        for (Products product : metaplace.getAllProducts()) {
//            int productNum = metaplace.get("selection");
//
//        }
//        metaplace.get(selection-1);


    @SuppressWarnings("checkstyle:WhitespaceAround")
    private void viewWallet() {
        System.out.println();
        System.out.println("Wallet");
        System.out.printf("%nBalance: %d$%n", account.getBalance());
        System.out.println();
        System.out.println("1) Add Funds");
        System.out.println("2) Return to Menu");
        System.out.println();

        String selection = sc.next();

        switch (selection) {
            case "1":
                System.out.println("01");
                break;
            case "2":
                System.out.println("02");
                break;
            default:
                System.out.println("Wrong Input! Please try again!");
                viewWallet();
                break;
        }

    }

    private void listProducts() {

    }

    private void viewPurchases() {
    }


}
