package model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account testAccount;
    List<Products> products = new ArrayList<>();
    private Products a;
    private Products b;
    private Products c;

    @BeforeEach
    void runBefore() {
        testAccount = new Account();
        a = new Products("Red Shirt", 10, "Red");
        b = new Products("Blue Shirt", 10, "Blue");
        c = new Products("Green Shirt", 10, "Green");
    }

    @Test
    void testConstructor() {
        assertTrue(testAccount.getPurchase().isEmpty());
        assertEquals(0, testAccount.getBalance());
    }

    @Test
    void testReload() {
        assertEquals(0, testAccount.getBalance());
        assertTrue(testAccount.reload(100));
        assertEquals(0, testAccount.getBalance());
    }

    @Test
    void testZeroReload() {
        assertFalse(testAccount.reload(0));
        testAccount.addMoney(0);
        assertEquals(0, testAccount.getBalance());

        testAccount.addMoney(150);
        testAccount.addMoney(0);
        assertEquals(150, testAccount.getBalance());
    }

    @Test
    void testAddMoney() {
        assertEquals(0, testAccount.getBalance());
        testAccount.addMoney(100);
        assertEquals(100, testAccount.getBalance());
    }

    @Test
    void testMultipleAddMoney() {
        testAccount.addMoney(100);
        testAccount.addMoney(100);
        assertEquals(200, testAccount.getBalance());
    }

    @Test
    void testPurchase() {
        testAccount.addMoney(100);
        assertTrue(testAccount.getPurchase().isEmpty());
        testAccount.purchase(a);
        assertTrue(testAccount.getPurchase().contains(a));
    }

    @Test
    void testMultiplePurchase() {
        testAccount.addMoney(20);
        testAccount.purchase(a);
        testAccount.purchase(b);
        testAccount.purchase(c);

        assertTrue(testAccount.getPurchase().contains(a));
        assertTrue(testAccount.getPurchase().contains(b));
        assertFalse(testAccount.getPurchase().contains(c));


    }

    @Test
    void testAddToProducts() {
        testAccount.addMoney(20);
        testAccount.addToProducts(a);
        assertTrue(testAccount.getProducts().contains(a));

        assertEquals(0, testAccount.getProducts().lastIndexOf(a));
    }

    @Test
    void testToJson() {
        testAccount.addMoney(20);
        testAccount.addToProducts(a);
        testAccount.addToProducts(b);

        assertTrue(testAccount.getProducts().contains(a));

        testAccount.purchase(a);
        testAccount.purchase(b);

        testAccount.toJson();

        assertTrue(testAccount.getProducts().isEmpty());
    }

    @Test
    void testToJsonIF() {
        testAccount.addMoney(20);;
        products.add(a);
        products.add(b);

        testAccount.purchase(a);
        testAccount.purchase(b);
        products.remove(a);
        products.remove(b);

        testAccount.toJson();

        assertTrue(products.isEmpty());
    }
}
