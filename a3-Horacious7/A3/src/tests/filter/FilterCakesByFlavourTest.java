package tests.filter;

import main.domain.BirthdayCake;
import main.filter.CakeFilters.FilterCakeByFlavour;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilterCakesByFlavourTest {

    private FilterCakeByFlavour<Integer> filter;

    @BeforeEach
    public void setUp() {
        filter = new FilterCakeByFlavour<>("Vanilla");
    }

    @Test
    public void testAccept_matchingFlavour_accepted() {
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "Tort", "Vanilla", 22.2);
        boolean filterResult = filter.apply(cake);
        assertTrue(filterResult, "Cakes with matching flavour should be accepted!");
    }

    @Test
    public void testAccept_matchingFlavourCaseInsensitive_accepted() {
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "Tort", "vanilla", 22.2);
        boolean filterResult = filter.apply(cake);
        assertTrue(filterResult, "Cakes with matching flavour, case insensitive, should be accepted!");
    }

    @Test
    public void testAccept_differentFlavour_notAccepted(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "Tort", "Strawberries", 22.2);
        boolean filterResult = filter.apply(cake);
        assertFalse(filterResult, "Cakes with different flavours should not be accepted!");
    }

    @Test
    public void testAccept_emptyCakeFlavourEmptyCakeFlavour_accepted(){
        String filterFlavour = " ";
        FilterCakeByFlavour<Integer> filterWithEmptyFlavour = new FilterCakeByFlavour<>(filterFlavour);
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "Tort", " ", 22.2);
        boolean filterResult = filterWithEmptyFlavour.apply(cake);
        assertTrue(filterResult, "Cakes with empty flavours matching empty filer flavour should be accepted!");
    }

    @Test
    public void testAccept_nullFilterFlavour_notAccepted(){
        String filterFlavour = null;
        FilterCakeByFlavour<Integer> filterWithNullFlavour = new FilterCakeByFlavour<>(filterFlavour);
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "Tort", "Ananas", 22.2);
        boolean filterResult = filterWithNullFlavour.apply(cake);
        assertFalse(filterResult, "Filter should not accept the cake because the filter flavour is null!");
    }
}