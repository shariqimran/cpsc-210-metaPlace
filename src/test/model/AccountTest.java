package model;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    private Account testAccount;
    private Products a;
    private Products b;
    private Products c;

    @BeforeEach
    void runBefore() {
        testAccount = new Account();
        a = new Products("Red Shirt", 500, "Red");
        b = new Products("Red Shirt", 500, "Red");
        c = new Products("Red Shirt", 500, "Red");
    }

    @Test
    void testConstructor() {
        assertTrue(testAccount.getPurchase().isEmpty());
        assertEquals(0, testAccount.getBalance());
    }
    @Test
    void testReload() {
        assertEquals(0, testAccount.getBalance());
        testAccount.reload(100);
        assertEquals(100, testAccount.getBalance());
    }

    @Test
    void testZeroReload() {
        testAccount.reload(0);
        assertEquals(0, testAccount.getBalance());

        testAccount.reload(100);
        testAccount.reload(0);
        assertEquals(100, testAccount.getBalance());
    }

    @Test
    void testMultipleReload() {
        testAccount.reload(100);
        testAccount.reload(100);
        assertEquals(200, testAccount.getBalance());
    }

    @Test
    void testPurchase() {
        assertTrue(testAccount.getPurchase().isEmpty());
        testAccount.purchase(a);
        assertTrue(testAccount.getPurchase().contains(a));
    }

    @Test
    void testMultiplePurchase() {
        testAccount.purchase(a);
        testAccount.purchase(b);
        testAccount.purchase(c);

        assertTrue(testAccount.getPurchase().contains(a));
        assertTrue(testAccount.getPurchase().contains(b));
        assertTrue(testAccount.getPurchase().contains(c));


    }
}
