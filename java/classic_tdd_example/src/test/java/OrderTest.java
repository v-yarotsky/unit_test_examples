import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class OrderTest {
    private static String TALISKER      = "Talisker";
    private static String HIGHLAND_PARK = "Highland Park";
    private Warehouse warehouse         = new WarehouseImpl();

    @Before
    public void setUp() {
        warehouse.add(TALISKER, 50);
        warehouse.add(HIGHLAND_PARK, 25);
    }

    @Test
    public void testOrderIsNotFilledInitially() {
        Order order = new Order(TALISKER, 50);
        assertFalse(order.isFilled());
    }

    @Test
    public void testOrderIsFilledIfEnoughInWarehouse() {
        Order order = new Order(TALISKER, 50);
        order.fill(warehouse);
        assertTrue(order.isFilled());
        assertEquals(0, warehouse.getInventory(TALISKER));
    }

    @Test
    public void testOrderDoesNotRemoveIfNotEnough() {
        Order order = new Order(TALISKER, 51);
        order.fill(warehouse);
        assertFalse(order.isFilled());
        assertEquals(50, warehouse.getInventory(TALISKER));
    }
}

