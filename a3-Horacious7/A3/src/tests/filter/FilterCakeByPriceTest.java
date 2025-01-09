package tests.filter;

import main.domain.BirthdayCake;
import main.filter.CakeFilters.FilterCakeByPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterCakeByPriceTest {

    private FilterCakeByPrice<Integer> filter;
    private double minPrice;
    private double maxPrice;

    @BeforeEach
    public void setUp(){
        minPrice = 10.0;
        maxPrice = 20.0;
        filter = new FilterCakeByPrice<>(minPrice, maxPrice);
    }

    @Test
    public void testAccept_priceEqualToMin_accepted(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "CakeName", "Vanilla", minPrice);
        boolean filterResult = filter.apply(cake);
        assertTrue(filterResult, "Price equals min range should be accepted!");
    }

    @Test
    public void testAccept_priceEqualToMax_accepted(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "CakeName", "Vanilla", maxPrice);
        boolean filterResult = filter.apply(cake);
        assertTrue(filterResult, "Price equals max range should be accepted!");
    }

    @Test
    public void testAccept_priceWithinRange_accepted(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "CakeName", "Vanilla", 15.4);
        boolean filterResult = filter.apply(cake);
        assertTrue(filterResult, "Price within range should be accepted!");
    }

    @Test
    public void testAccept_priceBelowRange_notAccepted(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "CakeName", "Vanilla", 1.2);
        boolean filterResult = filter.apply(cake);
        assertFalse(filterResult, "Price below range should not be accepted!");
    }

    @Test
    public void testAccept_priceAboveRange_notAccepted(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "CakeName", "Vanilla", 100.23);
        boolean filterResult = filter.apply(cake);
        assertFalse(filterResult, "Price above range should not be accepted!");
    }

    @Test
    public void testAccept_minPriceGreaterThanMaxPrice_notAccepted(){
        double minPriceGreaterThanMaxPrice = 15.4;
        double maxPriceLessThanMinPrice = 1.2;
        filter = new FilterCakeByPrice<>(minPriceGreaterThanMaxPrice, maxPriceLessThanMinPrice);
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "CakeName", "Vanilla", 10.2);
        boolean filterResult = filter.apply(cake);
        assertFalse(filterResult, "Min range greater than max range should not be accepted!");
    }

    @Test
    public void testAccept_negativeMinPrice_accepted(){
        double minPriceNegative = -35.4;
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "CakeName", "Vanilla", 5.3);
        filter = new FilterCakeByPrice<>(minPriceNegative, maxPrice);
        boolean filterResult = filter.apply(cake);
        assertTrue(filterResult, "Cake should be accepted even if the min price is negative!");
    }
}