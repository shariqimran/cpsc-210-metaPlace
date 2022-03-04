package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Account ac = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyAccount.json");
        try {
            Account ac = reader.read();
            assertEquals(0, ac.getBalance());
            assertEquals(0, ac.getPurchase().size());
            assertEquals(0, ac.getProducts().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralAccount.json");
        try {
            Account ac = reader.read();
            assertEquals(15000, ac.getBalance());
            assertEquals(0, ac.getPurchase().size());
            assertEquals(4, ac.getProducts().size());

            ac.addToProducts(new Products("Product(1)", 100, "Product(1)"));

            assertEquals(5, ac.getProducts().size());
            ac.purchase(ac.getProducts().get(0));
            ac.purchase(ac.getProducts().get(4));
            ac.purchase(ac.getProducts().get(1));
//            ac.addToPurchases(ac.getProducts().get(0));
            assertEquals(3, ac.getPurchase().size());
            assertEquals(8400, ac.getBalance());
//            ac.purchase(ac.getProducts().get(4));
//            ac.addToPurchases(ac.getProducts().get(3));
//            assertEquals(11900, ac.getBalance());
//            assertEquals(2, ac.getPurchase().size());

//            List<Account> accountList = JsonReader.
//            assertFalse(.isEmpty());
//            assertEquals("monkey 1", nfts.get(0).getTitle());
//            assertEquals("monkey 3", nfts.get(1).getTitle());
//            assertEquals("Alex Lee", nfts.get(1).getOwner());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
