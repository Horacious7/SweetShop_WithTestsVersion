package tests.domain;

import main.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    private Order<Integer> order;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @BeforeEach
    public void setUp() {
        order = new Order<>(null, 10, "Lala", "123 Street", "2023-01-01", "preparing", 100.0);
    }

    @Test
    public void testGetId_nullId_returnsNullId() {
        assertNull(order.getId());
    }

    @Test
    public void testSetId_positiveId_returnsId() {
        order.setId(10);
        assertEquals(10, order.getId());
    }

    @Test
    public void testToString_expectedString_returnsStringRepresentation() {
        order.setId(1);
        String expectedString = "1,10,2023-01-01,Lala,123 Street,preparing,100.0";
        assertEquals(expectedString, order.toString());
    }

    @Test
    public void testOrderConstructor_nullCustomerName_returnsNullCustomerName() {
        Order<Integer> nullCustomerNameOrder = new Order<>(1, 10, null, "123 Street", "2023-01-01", "preparing", 100.0);
        assertNull(nullCustomerNameOrder.getCustomerName());
    }

    @Test
    public void testConstructor_orderStatus_returnsPreparingStatus() {
        assertEquals("preparing", order.getStatus());
    }

    @Test
    public void testSetOrderStatus_differentStatus_shouldChange() {
        order.setStatus("different");
        assertEquals("different", order.getStatus());
    }
}