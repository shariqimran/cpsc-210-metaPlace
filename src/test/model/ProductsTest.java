package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsTest {

    private Products testProduct1;

    @BeforeEach
    void runBefore() {
        testProduct1 = new Products("Red Shirt", 500, "Red");
    }

    @Test
    void testConstructor() {
        assertEquals("Red Shirt", testProduct1.getName());
        assertEquals(500, testProduct1.getPrice());
        assertEquals("Red", testProduct1.getDescription());
    }
}
