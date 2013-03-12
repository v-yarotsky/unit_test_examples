import static org.junit.Assert.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {
    private static String TALISKER = "Talisker";
    @Mock private Warehouse warehouse;

    @Test
    public void testOrderIsNotFilledInitially() {
        Order order = new Order(TALISKER, 50);
        assertFalse(order.isFilled());
    }

    @Test
    public void testOrderIsFilledIfEnoughInWarehouse() {
        Order order = new Order(TALISKER, 50);
        when(warehouse.hasInventory(TALISKER, 50)).thenReturn(true);
        order.fill(warehouse);
        verify(warehouse).hasInventory(TALISKER, 50);
        verify(warehouse).remove(TALISKER, 50);
        assertTrue(order.isFilled());
    }

    @Test
    public void testOrderDoesNotRemoveIfNotEnough() {
        Order order = new Order(TALISKER, 50);
        when(warehouse.hasInventory(TALISKER, 50)).thenReturn(false);
        order.fill(warehouse);
        verify(warehouse).hasInventory(TALISKER, 50);
        assertFalse(order.isFilled());
    }
}

