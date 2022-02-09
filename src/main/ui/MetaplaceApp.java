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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    public MetaplaceApp() {
        runMetaplace();
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void runMetaplace() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = sc.next();
            command = command.toLowerCase();
//            if (sc. {
//                keepGoing = false;
//            }

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processMenuCommand(command);
            }
        }

        System.out.println("\n---------------------------------");
        System.out.println("- Thank you for using METAPLACE -");
        System.out.println("           Goodbye!");
        System.out.println(("---------------------------------"));
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void displayMenu() {
        System.out.println("\n-----------------------");
        System.out.println("       METAPLACE       ");
        System.out.println("-----------------------");
        System.out.println("\nSelect from:");
        System.out.println("\t1 => Browse Products");
        System.out.println("\t2 => Create a Listing");
        System.out.println("\t3 => View your Purchases");
        System.out.println("\t4 => Wallet");
        System.out.println("\tQ => Quit");
        System.out.println("\nUser Input: ");
        //System.out.println("-----------------------");
    }

    // REQUIRES:
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
                break;
            default:
                System.out.println("\nSelection not valid...");
                System.out.println("Please try again!");
                break;
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void viewProducts() {
        System.out.println("\nMETAPLACE");
        for (Products product : productsList) {
            System.out.println("-----------------------");
            System.out.printf("Name: %s%nPrice: $%.0f%nDescription: %s",
                    product.getName(), product.getPrice(), product.getDescription());
            System.out.println();
        }
        if (productsList.isEmpty()) {
            System.out.println();
            System.out.println("No items to display!");
        } else {
            System.out.println("-----------------------");
            System.out.printf("%nCurrent Balance: $%.0f%n", account.getBalance());
            System.out.println("\n1) Purchase an Item");
            System.out.println("2) Return to Menu");
            System.out.println("\nUser Input: ");
            String selection = sc.next(); // TODO


            switch (selection) {
                case "1":
                    purchaseItem();
                    break;
                case "2":
                    System.out.println("\nReturning to menu...");
                    break;
                default:
                    System.out.println("\nSelection not valid...");
                    System.out.println("Please try again!");
                    viewProducts();
                    break;
            }
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:

    private void noItemsLeftToPurchase() {
        if (productsList.isEmpty()) {
            System.out.println("No items to display!");
            System.out.println("Returning to menu...");
            displayMenu();
        }
    }

    private void purchaseItem() {
        System.out.println("\nPlease enter the number of the item you wish to purchase: ");
        if (sc.hasNextInt() && !productsList.isEmpty()) {
            int selection = sc.nextInt();
            if (selection > 0 && selection <= productsList.size()) {
                Products yourProduct = productsList.get(selection - 1);
                if (yourProduct.getPrice() <= account.getBalance()) {
                    account.purchase(yourProduct);
                    productsList.remove(yourProduct);
                    System.out.println("\n   PURCHASE RECEIPT");
                    System.out.println("-----------------------");
                    System.out.println("Congratulations!");
                    System.out.println("You Have Successfully Purchased:");
                    System.out.println();
                    System.out.printf("Name: %s, Price: $%.0f%n%nYour Remaining Balance: %.0f$",
                            yourProduct.getName(), yourProduct.getPrice(), account.getBalance());
                    System.out.println("\nYou are now returning to the main menu...");
                } else {
                    System.out.println("\nNot enough funds to purchase '" + yourProduct.getName() + "'!");
                    System.out.println("Returning to Product Listing(s)...");
                    viewProducts();
                }
            } else {
                System.out.println("\nInput not valid...");
                System.out.println("Please Try Again");
                purchaseItem();
            }
        } else {
            System.out.println("Input is not an Integer!");
            System.out.println("Please Try Again");
            String garbage = sc.next();
            purchaseItem();
        }

//        if (selection >= 6 || selection <= 0) {
//            System.out.println("\nSelection not valid...");
//            System.out.println("Please try again!");
//            purchaseItem();
//        }
    }


    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void listProducts() {
        System.out.println("\nMETAPLACE LISTING");
        System.out.println("-----------------------");
        System.out.println("1) Enter details of Listing");
        System.out.println("2) Return to Menu");
        System.out.println("\nUser Input: ");

        String selection = sc.next(); // TODO

        switch (selection) {
            case "1":
                listing();
                break;
            case "2":
                System.out.println("\nReturning to menu...");
                break;
            default:
                System.out.println("\nSelection not valid...");
                System.out.println("Please try again!");
                listProducts();
                break;
        }

    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void listing() {
        System.out.println("\nYour Listing");
        System.out.println("-----------------------");
        System.out.print("Name: ");
        String selectionName = sc.next(); // TODO
        System.out.print("Price: ");
        if (sc.hasNextInt()) {
            double selectionPrice = sc.nextInt();
            if (selectionPrice > 0) {
                System.out.print("Description: ");
                String selectionDesc = sc.next(); // TODO
                Products newProduct = new Products(selectionName, selectionPrice, selectionDesc);
                productsList.add(newProduct);
                System.out.println("\nYou have successfully listed your product '" + newProduct.getName()
                        + "' in the METAPLACE");
                System.out.println("\nReturning to menu...");
            } else {
                System.out.println("\nInput not valid...");
                System.out.println("Please enter a positive integer!");
                listing();
            }
        } else {
            String garbage = sc.next();
            System.out.println("Input is not an Integer!");
            System.out.println("Please Try Again");
            listing();
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void viewPurchases() {
        if (account.getPurchase().isEmpty()) {
            System.out.println("\nPURCHASE HISTORY");
            System.out.println("-----------------------");
            System.out.println("No Purchases to Show!");
            System.out.println("Returning to Menu...");
        } else {
            System.out.println();
            System.out.println("PURCHASE HISTORY");
            System.out.println("-----------------------");
            for (Products p : account.getPurchase()) {
                System.out.println("\n" + p.getName() + "\nPrice: " + p.getPrice());
            }
            System.out.println("\n1) Return to Menu");
            System.out.println("\nUser Input: ");
            String selection = sc.next(); // TODO
            while (!selection.equals("1")) {
                System.out.println("\nSelection not valid...");
                System.out.println("Please try again!");
                selection = sc.next(); // TODO
            }
            if (selection.equals("1")) {
                System.out.println("\nReturning to menu...");
            }
        }
    }

    // REQUIRES:
    // MODIFIES:
    // EFFECTS:
    private void viewWallet() {
        System.out.println("\nWALLET");
        System.out.println("-----------------------");
        System.out.printf("Current Balance: $%.0f%n", account.getBalance());
        System.out.println("\n1) Add Funds");
        System.out.println("2) Return to Menu\n");
        System.out.println("User Input: ");

        String selection = sc.next(); // TODO

        switch (selection) {
            case "1":
                addFunds();
                break;
            case "2":
                System.out.println("\nReturning to menu...");
                break;
            default:
                System.out.println("\nSelection not valid...");
                System.out.println("Please try again!");
                viewWallet();
                break;
        }

    }

    private void addFunds() {
        System.out.println("\nPlease enter the amount you wish to add: ");
        if (sc.hasNextInt()) {
            double amount = sc.nextInt(); // TODO
            if (amount > 0) {
                account.reload(amount);
                System.out.printf("%nYou have successfully added $%.0f to your account", amount);
                System.out.printf("%nYour new balance is $%.0f%n", account.getBalance());
            } else {
                System.out.println("\nInput not valid...");
                System.out.println("Please try again!");
                String garbage = sc.next();
                viewWallet();
            }
        } else {
            System.out.println("\nInput not valid...");
            System.out.println("Please try again!");
            String garbage = sc.next();
            viewWallet();
        }


    }


}
