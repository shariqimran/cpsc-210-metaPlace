package ui;

import model.Account;
import model.Products;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.sun.javafx.fxml.expression.Expression.add;
import static javax.swing.JFrame.*;

public class GUI {

    private int counter;

    JLabel balanceLabel;

    private JTextField enterMoneyText;

    private JTextField listMoney;
    private JTextField listName;
    private JTextField listDes;

    private JTextField field;

    ArrayList<JButton> buttonList = new ArrayList<>();

    protected JButton b1;
    protected JButton b2;
    protected JButton b3;
    protected JButton b4;
    protected JButton b5;

    protected JButton returnToMenu =  new JButton("Return to Main Menu");

    JFrame frame = new JFrame();
    JFrame walletFrame = new JFrame();
    JFrame purchaseHistoryFrame = new JFrame();
    JFrame createListingFrame = new JFrame();
    JFrame viewProductsFrame = new JFrame();

    MetaplaceApp metaplaceApp = new MetaplaceApp();
    Account account = metaplaceApp.getAccount();

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public GUI() {

        frame.setTitle("METAPLACE");

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 600));
        ((JPanel) frame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        frame.setLayout(new FlowLayout());

        returnToMenu.addActionListener(e -> returnToMenu());

        b1 = new JButton("Browse Products");
        b1.addActionListener(e -> viewGuiProducts());
        frame.add(b1);

        b2 = new JButton("Create Listing");
        b2.addActionListener(e -> listGuiProducts());
        frame.add(b2);

        b3 = new JButton("View Purchases");
        b3.addActionListener(e -> viewGuiPurchases());
        frame.add(b3);

        b4 = new JButton("Wallet");
        b4.addActionListener(e -> viewGuiWallet());
        frame.add(b4);

        b5 = new JButton("Save and Quit");
        b5.addActionListener(e -> quitApp());
        frame.add(b5);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void quitApp() {
        metaplaceApp.saveAccount();
    }

    //fix this
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void viewGuiPurchases() {
        JPanel purchasePane = new JPanel(new GridLayout(0,1));
        purchaseHistoryFrame.setTitle("Purchase History");
        purchaseHistoryFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        purchaseHistoryFrame.setPreferredSize(new Dimension(500, 1000));
        ((JPanel) purchaseHistoryFrame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        purchaseHistoryFrame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Your Purchases:");
        purchaseHistoryFrame.add(label);
        for (Products p : account.getPurchase()) {
            JLabel productName = new JLabel(p.getName());
            JLabel productPrice = new JLabel("$" + Math.round(p.getPrice()));
            JLabel productDes = new JLabel(p.getDescription());
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
        walletFrame.pack();
        walletFrame.setLocationRelativeTo(null);
        walletFrame.setResizable(false);
        walletFrame.setVisible(true);
        frame.setVisible(false);
    }

    private void addGuiFunds() {
        int moneyToAdd = Integer.parseInt(enterMoneyText.getText());
        account.addMoney(moneyToAdd);
        balanceLabel.setText("Current Balance: $"
                + Math.round(account.getBalance()));
    }


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

    private void createGuiListing() {
        String prodName = listName.getText();
        int prodPrice = Integer.parseInt(listMoney.getText());
        String prodDes = listDes.getText();

        Products newProduct = new Products(prodName, prodPrice, prodDes);
        metaplaceApp.productsList.add(newProduct);
    }


    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings", "checkstyle:GenericWhitespace", "checkstyle:NoWhitespaceBefore"})
    private void viewGuiProducts() {
        JPanel labelPane = new JPanel(new GridLayout(0,1));
        viewProductsFrame.setTitle("Products");
        viewProductsFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        viewProductsFrame.setPreferredSize(new Dimension(500, 1000));
        ((JPanel) viewProductsFrame.getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        viewProductsFrame.setLayout(new FlowLayout());
        JLabel label = new JLabel("Current Products:");
        viewProductsFrame.add(label);
        JButton[] buttons = new JButton[metaplaceApp.productsList.size()];

        for (Products p : metaplaceApp.productsList) {
            counter = 0;
            counter = counter + 1;
            JLabel productName = new JLabel(p.getName());
            JLabel productPrice = new JLabel("$" + Math.round(p.getPrice()));
            JLabel productDes = new JLabel(p.getDescription());

//            JButton button = new JButton("Purchase");
//            buttonList.add(button);

            buttons[counter] = new JButton("Purchase");
            buttonList.add(buttons[counter]);
            buttons[counter].addActionListener(e -> purchaseGui());


            labelPane.add(productName);
            labelPane.add(productPrice);
            labelPane.add(productDes);
            labelPane.add(buttons[counter]);

            labelPane.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            viewProductsFrame.add(labelPane, BorderLayout.CENTER);
        }

//        JLabel allGuiProducts = new JLabel(getGuiProducts());
//        viewProductsFrame.add(allGuiProducts);
        viewProductsFrame.add(returnToMenu);
        viewProductsFrame.pack();
        viewProductsFrame.setLocationRelativeTo(null);
        viewProductsFrame.setResizable(false);
        viewProductsFrame.setVisible(true);
        frame.setVisible(false);

    }

    private void purchaseGui() {
        account.purchase(metaplaceApp.productsList.get(counter));
    }

}
