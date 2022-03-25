package ui;

import model.Account;
import model.Products;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static javax.swing.JFrame.*;

//GUI for MetaplaceApp
public class MetaplaceGUI {

    String errorString;

    JLabel balanceLabel;
    JLabel errorLabel;

    private JTextField enterMoneyText;
    private JTextField listMoney;
    private JTextField listName;
    private JTextField listDes;

    protected JButton b1;
    protected JButton b2;
    protected JButton b3;
    protected JButton b4;
    protected JButton b5;
    protected JButton returnToMenu;

    JFrame frame;
    JFrame walletFrame;
    JFrame purchaseHistoryFrame;
    JFrame createListingFrame;
    JFrame viewProductsFrame;

    MetaplaceApp metaplaceApp;
    Account account;

    //Effects: runs the GUI, initializes frames
    public MetaplaceGUI() {
        errorLabel = new JLabel();

        returnToMenu = new JButton("Return to Main Menu");
        frame = new JFrame();
        walletFrame = new JFrame();
        purchaseHistoryFrame = new JFrame();
        createListingFrame = new JFrame();
        viewProductsFrame = new JFrame();

        metaplaceApp = new MetaplaceApp();
        account = metaplaceApp.getAccount();

        makeApp();
    }

    //Effects: initializes images for first frame
    private void makeApp() {
        ImageIcon imageIcon1 = new ImageIcon("src/main/ui/pics/viewProducts.png");
        Image image1 = imageIcon1.getImage(); // transform it
        Image newImg1 = image1.getScaledInstance(170, 90, Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon1 = new ImageIcon(newImg1);  // transform it back
        ImageIcon imageIcon2 = new ImageIcon("src/main/ui/pics/listing.png");
        Image image2 = imageIcon2.getImage(); // transform it
        Image newImg2 = image2.getScaledInstance(170, 90, Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon2 = new ImageIcon(newImg2);  // transform it back
        ImageIcon imageIcon3 = new ImageIcon("src/main/ui/pics/history.png");
        Image image3 = imageIcon3.getImage(); // transform it
        Image newImg3 = image3.getScaledInstance(170, 90, Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon3 = new ImageIcon(newImg3);  // transform it back
        ImageIcon imageIcon4 = new ImageIcon("src/main/ui/pics/wallet.png");
        Image image4 = imageIcon4.getImage(); // transform it
        Image newImg4 = image4.getScaledInstance(170, 90, Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon4 = new ImageIcon(newImg4);  // transform it back
        ImageIcon imageIcon5 = new ImageIcon("src/main/ui/pics/exit.png");
        Image image5 = imageIcon5.getImage(); // transform it
        Image newImg5 = image5.getScaledInstance(170, 90, Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon5 = new ImageIcon(newImg5);  // transform it back
        setGuiMenu(imageIcon1, imageIcon2, imageIcon3, imageIcon4, imageIcon5);
    }

    //Modifies: this
    //Effects: initializes buttons with its images
    private void setGuiMenu(ImageIcon imageIcon1, ImageIcon imageIcon2,
                            ImageIcon imageIcon3, ImageIcon imageIcon4, ImageIcon imageIcon5) {
        frame.setTitle("METAPLACE");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 600));
        ((JPanel) frame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        frame.setLayout(new FlowLayout());
        returnToMenu.addActionListener(e -> returnToMenu());
        b1 = new JButton("Browse Products");
        b1.setIcon(imageIcon1);
        b1.addActionListener(e -> viewGuiProducts());
        b2 = new JButton(" Create Listing ");
        b2.setIcon(imageIcon2);
        b2.addActionListener(e -> listGuiProducts());
        b3 = new JButton(" View Purchases");
        b3.setIcon(imageIcon3);
        b3.addActionListener(e -> viewGuiPurchases());
        b4 = new JButton("         Wallet        ");
        b4.setIcon(imageIcon4);
        b4.addActionListener(e -> viewGuiWallet());
        b5 = new JButton(" Save and Quit");
        b5.setIcon(imageIcon5);
        b5.addActionListener(e -> quitApp());
        setFrameGuiMenu();
    }

    //Modifies: this
    //Effects: adds buttons to main frame
    private void setFrameGuiMenu() {
        frame.add(b1);
        frame.add(b2);
        frame.add(b3);
        frame.add(b4);
        frame.add(b5);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    //Modifies: all the frames
    //Effects: sets all frames to false and main frame to true
    private void returnToMenu() {
        frame.setVisible(true);
        walletFrame.setVisible(false);
        walletFrame.getContentPane().removeAll();
        purchaseHistoryFrame.setVisible(false);
        purchaseHistoryFrame.getContentPane().removeAll();
        createListingFrame.setVisible(false);
        createListingFrame.getContentPane().removeAll();
        viewProductsFrame.setVisible(false);
        viewProductsFrame.getContentPane().removeAll();
    }

    //Modifies: this
    //Effects: sets layout for viewProductsFrame, creates label
    private void viewGuiProducts() {
        viewProductsFrame.getContentPane().removeAll();
        JPanel labelPane = new JPanel(new GridLayout(0, 1));
        viewProductsFrame.setTitle("Products");
        viewProductsFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        viewProductsFrame.setPreferredSize(new Dimension(500, 1000));
        ((JPanel) viewProductsFrame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        viewProductsFrame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Current Products:");
        viewProductsFrame.add(label);
        account.toJson();
        addGuiProducts(labelPane);
    }

    //Modifies: this
    //Effects: adds Products to viewProductsFrame and creates labels and buttons
    private void addGuiProducts(JPanel labelPane) {
        for (Products p : metaplaceApp.productsList) {
            JLabel productName = new JLabel(p.getName());
            JLabel productPrice = new JLabel("$" + Math.round(p.getPrice()));
            JLabel productDes = new JLabel(p.getDescription());
            JButton purchaseButton = new JButton("Purchase ");
            labelPane.add(productName);
            labelPane.add(productPrice);
            labelPane.add(productDes);
            labelPane.add(purchaseButton);
            purchaseButton.addActionListener(e -> purchaseThisGui(p));
            labelPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            viewProductsFrame.add(labelPane, BorderLayout.CENTER);
        }
        viewProductsFrame.add(returnToMenu);
        viewProductsFrame.pack();
        viewProductsFrame.setLocationRelativeTo(null);
        viewProductsFrame.setResizable(false);
        viewProductsFrame.setVisible(true);
        frame.setVisible(false);
    }

    //Modifies: this
    //Effects: purchases this product
    private void purchaseThisGui(Products p) {
        account.purchase(p);
        viewGuiProducts();
    }

    //Modifies: this
    //Effects: sets layout for createListingFrame, creates labels/buttons
    private void listGuiProducts() {
        createListingFrame.setTitle("Create Listing");
        createListingFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createListingFrame.setPreferredSize(new Dimension(500, 600));
        ((JPanel) createListingFrame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        createListingFrame.setLayout(new FlowLayout());
        JLabel listingName = new JLabel("Name: ");
        JLabel listingPrice = new JLabel("Price: ");
        JLabel listingDescription = new JLabel("Description: ");
        listName = new JTextField(5);
        listMoney = new JTextField(5);
        listDes = new JTextField(5);
        JButton submit = new JButton("Submit");
        submit.addActionListener(e -> createGuiListing());
        setListingGuiFrame(listingName, listingPrice, listingDescription, submit);
    }

    //Modifies: this
    //Effects: adds buttons/labels to frame
    private void setListingGuiFrame(JLabel listingName, JLabel listingPrice,
                                    JLabel listingDescription, JButton submit) {
        createListingFrame.add(listingName);
        createListingFrame.add(listName);
        createListingFrame.add(listingPrice);
        createListingFrame.add(listMoney);
        createListingFrame.add(listingDescription);
        createListingFrame.add(listDes);
        createListingFrame.add(submit);
        createListingFrame.add(returnToMenu);
        createListingFrame.pack();
        createListingFrame.setLocationRelativeTo(null);
        createListingFrame.setResizable(false);
        createListingFrame.setVisible(true);
        frame.setVisible(false);
    }

    //Modifies: this
    //Effects: adds Product to productsList
    private void createGuiListing() {
        String prodName = listName.getText();
        int prodPrice = Integer.parseInt(listMoney.getText());
        String prodDes = listDes.getText();
        Products newProduct = new Products(prodName, prodPrice, prodDes);
        metaplaceApp.productsList.add(newProduct);
    }

    //Modifies: this
    //Effects: sets layout for purchaseHistoryFrame, creates label
    private void viewGuiPurchases() {
        JPanel purchasePane = new JPanel(new GridLayout(0, 1));
        int counter = 0;
        purchaseHistoryFrame.setTitle("Purchase History");
        purchaseHistoryFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        purchaseHistoryFrame.setPreferredSize(new Dimension(500, 1000));
        ((JPanel) purchaseHistoryFrame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        purchaseHistoryFrame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Your Purchases:");
        purchaseHistoryFrame.add(label);
        addGuiPurchases(purchasePane, counter);
    }

    //Modifies: this
    //Effects: adds purchases to purchaseHistoryFrame
    private void addGuiPurchases(JPanel purchasePane, int counter) {
        JLabel prodNumb;
        for (Products p : account.getPurchase()) {
            counter += 1;
            prodNumb = new JLabel("Product # " + counter);
            JLabel productName = new JLabel(p.getName());
            JLabel productPrice = new JLabel("$" + Math.round(p.getPrice()));
            JLabel productDes = new JLabel(p.getDescription());
            purchasePane.add(prodNumb);
            purchasePane.add(productName);
            purchasePane.add(productPrice);
            purchasePane.add(productDes);
            purchasePane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            purchaseHistoryFrame.add(purchasePane, BorderLayout.CENTER);
        }
        purchaseHistoryFrame.add(returnToMenu);
        purchaseHistoryFrame.pack();
        purchaseHistoryFrame.setLocationRelativeTo(null);
        purchaseHistoryFrame.setResizable(false);
        purchaseHistoryFrame.setVisible(true);
        frame.setVisible(false);
    }

    //Modifies: this
    //Effects: sets layout for walletFrame, creates buttons/labels
    private void viewGuiWallet() {
        walletFrame.setTitle("Wallet");
        walletFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        walletFrame.setPreferredSize(new Dimension(500, 600));
        ((JPanel) walletFrame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        walletFrame.setLayout(new FlowLayout());
        balanceLabel = new JLabel("Current Balance: $"
                + Math.round(account.getBalance()));
        JLabel addFundsLabel = new JLabel("Add Funds: ");
        enterMoneyText = new JTextField(5);
        JButton addMoneyButton = new JButton("ADD");
        addMoneyButton.addActionListener(e -> addGuiFunds());
        walletFrame.add(balanceLabel);
        walletFrame.add(addFundsLabel);
        walletFrame.add(enterMoneyText);
        walletFrame.add(addMoneyButton);
        walletFrame.add(returnToMenu);
        walletFrame.add(errorLabel);
        walletFrame.pack();
        walletFrame.setLocationRelativeTo(null);
        walletFrame.setResizable(false);
        walletFrame.setVisible(true);
        frame.setVisible(false);
    }

    //Modifies: this
    //Effects: adds funds to wallet
    private void addGuiFunds() {
        try {
            int moneyToAdd = Integer.parseInt(enterMoneyText.getText());
            if (account.reload(moneyToAdd)) {
                account.addMoney(moneyToAdd);
                balanceLabel.setText("Current Balance: $"
                        + Math.round(account.getBalance()));
                errorString = "Successfully added $" + moneyToAdd + " to your account";
            } else {
                errorString = "Input not valid...";
            }
        } catch (Exception e) {
            errorString = "Input not valid...";
        }
        errorLabel.setText(errorString);
    }

    //Modifies: this
    //Effects: saves account
    private void quitApp() {
        metaplaceApp.saveAccount();
    }
}
