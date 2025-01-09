package tests.filter;

import main.domain.Order;
import main.filter.OrderFilters.FilterOrderByStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterOrderByStatusTest {

    private FilterOrderByStatus<Integer> filter;
    private String filterStatus;

    @BeforeEach
    public void setUp() {
        filterStatus = "cancelled";
        filter = new FilterOrderByStatus<>(filterStatus);
    }

    @Test
    public void testAccept_matchingStatus_accepted(){
        Order<Integer> order =  new Order<>(1, 1, "Customer", "Address", "Phone", "Email", 100.0);
        order.setStatus("cancelled");
        boolean filterResult = filter.apply(order);
        assertTrue(filterResult, "Orders with matching status should be accepted!");
    }

    @Test
    public void testAccept_matchingStatusCaseInsensitive_accepted(){
        Order<Integer> order =  new Order<>(1, 1, "Customer", "Address", "Phone", "Email", 100.0);
        order.setStatus("Cancelled");
        boolean filterResult = filter.apply(order);
        assertTrue(filterResult, "Orders with matching status, case insensitive, should be accepted!");
    }

    @Test
    public void testAccept_differentStatus_notAccepted(){
        Order<Integer> order =  new Order<>(1, 1, "Customer", "Address", "Phone", "Email", 100.0);
        order.setStatus("finished");
        boolean filterResult = filter.apply(order);
        assertFalse(filterResult, "Orders with different status should not be accepted!");
    }

    @Test
    public void testAccept_emptyOrderStatusEmptyFilterStatus_accepted(){
        FilterOrderByStatus<Integer> filterWithEmptyStatus = new FilterOrderByStatus<>("");
        Order<Integer> order =  new Order<>(1, 1, "Customer", "Address", "Phone", "Email", 100.0);
        order.setStatus("");
        boolean filterResult = filterWithEmptyStatus.apply(order);
        assertTrue(filterResult, "Orders with empty status should be accepted because the filter status is also empty!");
    }

    @Test
    public void testAccept_nullFilterStatus_notAccepted(){
        FilterOrderByStatus<Integer> filterWithNullStatus = new FilterOrderByStatus<>(null);
        Order<Integer> order =  new Order<>(1, 1, "Customer", "Address", "Phone", "Email", 100.0);
        order.setStatus("finished");
        boolean filterResult = filterWithNullStatus.apply(order);
        assertFalse(filterResult,"Filter should not accept order because the filter status is null!");
    }
}