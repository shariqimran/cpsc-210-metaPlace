package persistence;

import model.*;
import org.json.JSONObject;
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
            //assertEquals(14000, ac.getBalance());
            assertEquals(13900, ac.getBalance());
            //assertEquals(1, ac.getPurchase().size());
            assertEquals(2, ac.getPurchase().size());
            //assertEquals(4, ac.getProducts().size());
            assertEquals(4, ac.getProducts().size());//was5

            ac.addToProducts(new Products("Product(1)", 100, "Product(1)"));

            assertEquals(5, ac.getProducts().size()); //was 5
            ac.purchase(ac.getProducts().get(0));
            ac.purchase(ac.getProducts().get(4));
            ac.purchase(ac.getProducts().get(1));
//          ac.addToPurchases(ac.getProducts().get(0));
            assertEquals(5, ac.getPurchase().size()); //was 4
            assertEquals(7300, ac.getBalance()); //was 7400

            List<Products> purchases = JsonReader.getPurchases(ac);
            assertFalse(purchases.isEmpty());
//          assertEquals("SM1 Art Piece", purchases.get(0).getName());
//          assertEquals("Product(1)", purchases.get(1).getName());
//          assertEquals("Product(1)", purchases.get(1).getName());

            assertEquals("Test2N", purchases.get(0).getName());
            assertEquals("Test2", purchases.get(1).getName()); //Was SM1 Art Piece
            assertEquals("Test2", purchases.get(1).getName()); //same as above

//          reader.addStuff(ac, reader);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

