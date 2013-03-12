import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Mockery;
import org.jmock.Expectations;

@RunWith(JMock.class)
public class OrderTest {
    private static String TALISKER = "Talisker";
    Mockery context                = new JUnit4Mockery();

    @Test
    public void testOrderIsNotFilledInitially() {
        Order order = new Order(TALISKER, 50);
        assertFalse(order.isFilled());
    }

    @Test
    public void testOrderIsFilledIfEnoughInWarehouse() {
        Order order = new Order(TALISKER, 50);
        final Warehouse warehouse = context.mock(Warehouse.class);

        context.checking(new Expectations() {{
            oneOf (warehouse).hasInventory(TALISKER, 50); will(returnValue(true));
            oneOf (warehouse).remove(TALISKER, 50);
        }} );

        order.fill(warehouse);
        assertTrue(order.isFilled());
    }

    @Test
    public void testOrderDoesNotRemoveIfNotEnough() {
        Order order = new Order(TALISKER, 50);
        final Warehouse warehouse = context.mock(Warehouse.class);

        context.checking(new Expectations() {{
            oneOf (warehouse).hasInventory(TALISKER, 50); will(returnValue(false));
        }} );

        order.fill(warehouse);
        assertFalse(order.isFilled());
    }
}

