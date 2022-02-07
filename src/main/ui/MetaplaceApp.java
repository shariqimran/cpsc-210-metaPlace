package ui;

import model.*;

import java.util.*;

//display menu
public class MetaplaceApp {

    private Products metaplace;
    private Account account;
    private Products product1;
    private Products product2;
    private Products product3;
    private Products product4;
    private Products product5;
    private List<Products> productsList;

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
        productsList = new ArrayList<>(); //

        account = new Account();
        metaplace = new Products();
        product1 = new Products("Sapphire Blue Shirt", 800, "Brand New!");
        product2 = new Products("Red Shirt", 500, "Used Like New!");
        product3 = new Products("SM1 Art Piece", 3000, "Get Exclusive Product Drops from the SM Website!");
        product4 = new Products("SM2 Art Piece", 3500, "Get Exclusive Product Drops from the SM Website!");
        product5 = new Products("KERS Collectors item", 6000, "Limited Edition KERS Black & White Figurine!");

        productsList.add(product1);
        productsList.add(product2);
        productsList.add(product3);
        productsList.add(product4);
        productsList.add(product5);

        sc = new Scanner(System.in);


    }

    private void displayMenu() {
        System.out.println("-------METAPLACE-------");
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Browse Products");
        System.out.println("\t2 -> Create a Listing");
        System.out.println("\t3 -> View your Purchases");
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

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void viewProducts() {
        for (Products product : productsList) {
            System.out.println("-----------------------");
            System.out.printf("Name: %s%nPrice: %.0f%nDescription: %s",
                    product.getName(), product.getPrice(), product.getDescription());
            System.out.println();
        }
        if (productsList.isEmpty()) {
            System.out.println();
            System.out.println("No items to display!");
            System.out.println("Returning to menu!");
            displayMenu();
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
        Products yourProduct = productsList.get(selection - 1);
        account.purchase(yourProduct);
        productsList.remove(yourProduct);
        System.out.println("You have purchased: ");
        for (Products p : account.getPurchase()) {
            System.out.println(p.getName());
        }


        System.out.println();
        System.out.println("Thank you!");
        System.out.println("You are now returning to the main menu...");



    }


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
        System.out.println("1) Enter details of Listing");
        System.out.println("2) Return to Menu");

        String selection = sc.next();

        switch (selection) {
            case "1":
                listing();
                break;
            case "2":
                break;
            default:
                System.out.println("Wrong Input! Please try again!");
                listProducts();
                break;
        }

    }

    private void listing() {
        System.out.print("Name: ");
        String selectionName = sc.next();
        System.out.print("Price: ");
        String selectionPrice = sc.next();



//    while (Integer.parseInt(selectionPrice).is)
//        try {
//            Integer.parseInt(selectionPrice);
//        } catch (Exception e) {
//            System.out.println("Input is not an Integer!");
//            System.out.println("Please Try Again");
//            System.out.print("Price: ");
//            selectionPrice = sc.next();
//        }

//        while (selectionPrice == ) {
//            System.out.println("Wrong Input!");
//            System.out.println("Please try again!");
//            System.out.print("Price: ");
//        }
        System.out.print("Description: ");
        String selectionDesc = sc.next();

        Products newProduct = new Products(selectionName, Integer.parseInt(selectionPrice), selectionDesc);
        productsList.add(newProduct);
        System.out.println("!!You have successfully listed your product '" + newProduct.getName()
                + "' in the METAPLACE!!");
        System.out.println();
    }

    private void viewPurchases() {
        if (account.getPurchase().isEmpty()) {
            System.out.println("No Purchases to Show!");
            System.out.println("Returning to Menu...");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("You have purchased: ");
            for (Products p : account.getPurchase()) {
                System.out.println(p.getName());
            }
            System.out.println("1) Return to Menu");
            String selection = sc.next();

            while (!selection.equals("1")) {
                System.out.println("Wrong Input!");
                System.out.println("Please try again");
                selection = sc.next();
            }
        }

    }


}
