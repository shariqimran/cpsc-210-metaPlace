package persistence;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    Account ac;

    @BeforeEach
    public void setUp() {
        ac = new Account();
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyAccount.json");
            writer.open();
            writer.write(ac);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyAccount.json");
            ac = reader.read();
            assertEquals(0, ac.getProducts().size());
            assertEquals(0, ac.getPurchase().size());
            assertEquals(0, ac.getBalance());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralAccount.json");
            ac.addToProducts(new Products("prod1", 100, "prod1"));
            ac.addToProducts(new Products("Product(2)", 100, "Product2"));
            ac.addToProducts(new Products("Red", 100, "Red"));
            ac.addMoney(1000);
            assertEquals(1000, ac.getBalance());
            assertEquals(3, ac.getProducts().size()); //was 4
            //ac.purchase(ac.getProducts().get(1));
            writer.open();
            writer.write(ac);
            writer.close();
            assertEquals(3, ac.getProducts().size()); //2
            ac.addToProducts(new Products("prod1", 100, "prod1"));
            assertEquals(4, ac.getProducts().size());//4
            ac.purchase(ac.getProducts().get(1));

            JsonReader reader = new JsonReader("./data/testWriterGeneralAccount.json");
            ac = reader.read();
            assertEquals(3, ac.getProducts().size());//2
           assertEquals(0, ac.getPurchase().size());
            assertEquals(1000, ac.getBalance());

            JsonWriter wr = new JsonWriter("./data/testWriterGeneralAccount.json");
//            ac.addToProducts(new Products("Product(1)", 100, "Product(1)"));
//            ac.addToProducts(new Products("Product(1)", 100, "Product(1)"));
//            ac.addToProducts(new Products("Red", 100, "Red"));
//
            ac.purchase(ac.getProducts().get(0));
            ac.purchase(ac.getProducts().get(2));
            ac.addToProducts(new Products("prod1", 100, "prod1"));
            wr.open();
            wr.write(ac);
            wr.close();

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
