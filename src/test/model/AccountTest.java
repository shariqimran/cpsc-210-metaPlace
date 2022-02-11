package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account testAccount;
    private Products a;
    private Products b;
    private Products c;

    @BeforeEach
    void runBefore() {
        testAccount = new Account();
        a = new Products("Red Shirt", 10, "Red");
        b = new Products("Red Shirt", 10, "Red");
        c = new Products("Red Shirt", 10, "Red");
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
}
