package tests.service;

import main.domain.BirthdayCake;
import main.repository.memory.InMemoryCakeRepository;
import main.service.BirthdayCakeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class BirthdayCakeServiceTest {
    private BirthdayCakeService cakeService;
    private InMemoryCakeRepository cakeRepository;

    @BeforeEach
    void setUp() {
        cakeRepository = new InMemoryCakeRepository();
        cakeService = new BirthdayCakeService(cakeRepository);
    }

    @Test
    void addCake_ShouldAddCake_valid() {
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "Choco", "Chocolate", 55.1);
        Integer cakeId = (Integer) cakeService.addCake(cake);
        assertEquals(cake.getId(), cakeId);
    }

    @Test
    void getAllCakes_ShouldReturnAllCakes_valid() {
        BirthdayCake<Integer> cake1 = new BirthdayCake<>(1, "Name", "Chocolate", 25.0);
        BirthdayCake<Integer> cake2 = new BirthdayCake<>(2, "Classic", "Vanilla", 15.0);
        cakeRepository.add(cake1);
        cakeRepository.add(cake2);

        Iterable<BirthdayCake<Integer>> result = cakeService.getAllCakes();
        List<BirthdayCake<Integer>> cakeList = new ArrayList<>();
        result.forEach(cakeList::add);

        assertEquals(2, cakeList.size());
    }

    @Test
    void getAllCakes_ShouldReturnEmptyList_WhenNoCakes() {
        Iterable<BirthdayCake<Integer>> result = cakeService.getAllCakes();
        assertFalse(result.iterator().hasNext(), "getCakes should return an empty iterable if there are no cakes");
    }

    @Test
    void updateCake_ShouldUpdateCake_WhenValid() {
        Integer cakeId = 1;
        BirthdayCake<Integer> cake = new BirthdayCake<>(cakeId, "Lemon Fresh", "Lemon", 22.0);
        cakeRepository.add(cake);

        BirthdayCake<Integer> updatedCake = new BirthdayCake<>(cakeId, "Lemon Fresh", "Lemon", 24.0);
        cakeService.updateCake(updatedCake);

        BirthdayCake<Integer> foundCake = null;
        for (BirthdayCake<Integer> c : cakeRepository.getAll()) {
            if (c.getId().equals(cakeId)) {
                foundCake = c;
                break;
            }
        }

        assertNotNull(foundCake);
        assertEquals(24.0, foundCake.getPrice());
    }

    @Test
    void deleteCake_ShouldDeleteCake_WhenExists() {
        Integer cakeId = 1;
        BirthdayCake<Integer> cake = new BirthdayCake<>(cakeId, "Red Velvet", "Red Velvet", 28.0);
        cakeRepository.add(cake);

        cakeService.deleteCake(cakeId);

        BirthdayCake<Integer> foundCake = null;
        for (BirthdayCake<Integer> c : cakeRepository.getAll()) {
            if (c.getId().equals(cakeId)) {
                foundCake = c;
                break;
            }
        }

        assertNull(foundCake);
    }

    @Test
    void deleteCake_ShouldThrowException_WhenCakeDoesNotExist() {
        Integer cakeId = 1;

        assertThrows(IllegalArgumentException.class, () -> cakeService.deleteCake(cakeId));
    }

    @Test
    void filterByFlavor_ShouldReturnCakes_WithFilteringFlavor() {
        String flavour = "Vanilla";
        BirthdayCake<Integer> cake = new BirthdayCake<>(1, "Classic Vanilla", "Vanilla", 20.0);
        cakeRepository.add(cake);

        Iterable<BirthdayCake<Integer>> result = cakeService.getCakesByFlavour(flavour);
        result.forEach(c -> assertEquals(flavour, c.getFlavour()));
    }

    @Test
    void filterByPriceRange_ShouldReturnCakes_InPriceRange() {
        BirthdayCake<Integer> cake1 = new BirthdayCake<>(1, "Classic Chocolate", "Chocolate", 20.0);
        BirthdayCake<Integer> cake2 = new BirthdayCake<>(2, "Simple Vanilla", "Vanilla", 15.0);
        cakeRepository.add(cake1);
        cakeRepository.add(cake2);

        Iterable<BirthdayCake<Integer>> result = cakeService.getCakesByPriceRange(10.0, 30.0);
        result.forEach(cake -> assertTrue(cake.getPrice() >= 10.0 && cake.getPrice() <= 30.0));
    }

    @Test
    void filterByPriceRange_ShouldThrowException_WhenMinPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> cakeService.getCakesByPriceRange(-1.0, 10.0));
    }

    @Test
    void filterByPriceRange_ShouldThrowException_WhenMaxPriceLessThanMinPrice() {
        assertThrows(IllegalArgumentException.class, () -> cakeService.getCakesByPriceRange(15.0, 10.0));
    }

    @Test
    void filterByFlavor_ShouldThrowException_WhenRepositoryIsEmpty() {
        String flavour = "Vanilla";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cakeService.getCakesByFlavour(flavour));
        assertEquals("Empty repository!", exception.getMessage());
    }

    @Test
    void filterByPriceRange_ShouldThrowException_WhenRepositoryIsEmpty() {
        double minPrice = 10.0;
        double maxPrice = 20.0;

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cakeService.getCakesByPriceRange(minPrice, maxPrice));
        assertEquals("Empty repository!", exception.getMessage());
    }
}