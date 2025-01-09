package tests.filter;

import main.domain.Order;
import main.filter.OrderFilters.FilterOrderByPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterOrderByPriceTest {

    private FilterOrderByPrice<Integer> filter;
    private double minPrice;
    private double maxPrice;

    @BeforeEach
    public void setUp() {
        minPrice = 10.0;
        maxPrice = 50.0;
        filter = new FilterOrderByPrice<>(minPrice, maxPrice);
    }

    @Test
    public void testAccept_priceEqualToMin_accepted() {
        Order<Integer> order = new Order<>(1, 1, "Customer", "Address", "Phone", "Email", minPrice);
        boolean filterResult = filter.apply(order);
        assertTrue(filterResult, "Price equals min range should be accepted!");
    }

    @Test
    public void testAccept_priceEqualToMax_accepted() {
        Order<Integer> order = new Order<>(1, 1, "Customer", "Address", "Phone", "Email", maxPrice);
        boolean filterResult = filter.apply(order);
        assertTrue(filterResult, "Price equals max range should be accepted!");
    }

    @Test
    public void testAccept_priceWithinRange_accepted() {
        Order<Integer> order = new Order<>(1, 1, "Customer", "Address", "Phone", "Email", 30.0);
        boolean filterResult = filter.apply(order);
        assertTrue(filterResult, "Price within range should be accepted!");
    }

    @Test
    public void testAccept_priceBelowRange_notAccepted() {
        Order<Integer> order = new Order<>(1, 1, "Customer", "Address", "Phone", "Email", 5.0);
        boolean filterResult = filter.apply(order);
        assertFalse(filterResult, "Price below range should not be accepted!");
    }

    @Test
    public void testAccept_priceAboveRange_notAccepted() {
        Order<Integer> order = new Order<>(1, 1, "Customer", "Address", "Phone", "Email", 60.0);
        boolean filterResult = filter.apply(order);
        assertFalse(filterResult, "Price above range should not be accepted!");
    }

    @Test
    public void testAccept_minPriceGreaterThanMaxPrice_notAccepted() {
        double minPriceGreaterThanMaxPrice = 50.0;
        double maxPriceLessThanMinPrice = 10.0;
        filter = new FilterOrderByPrice<>(minPriceGreaterThanMaxPrice, maxPriceLessThanMinPrice);
        Order<Integer> order = new Order<>(1, 1, "Customer", "Address", "Phone", "Email", 30.0);
        boolean filterResult = filter.apply(order);
        assertFalse(filterResult, "Min range greater than max range should not be accepted!");
    }

    @Test
    public void testAccept_negativeMinPrice_accepted() {
        double minPriceNegative = -10.0;
        filter = new FilterOrderByPrice<>(minPriceNegative, maxPrice);
        Order<Integer> order = new Order<>(1, 1, "Customer", "Address", "Phone", "Email", 5.0);
        boolean filterResult = filter.apply(order);
        assertTrue(filterResult, "Order should be accepted even if the min price is negative!");
    }
}