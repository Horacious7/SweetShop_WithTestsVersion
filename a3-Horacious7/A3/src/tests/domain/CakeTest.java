package tests.domain;

import main.domain.BirthdayCake;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CakeTest {
    @Test
    public void testCakeConstructorWithoutIdAndGetName_cakeName_returnsName(){
        BirthdayCake<Integer> cake = new BirthdayCake<>("Red", "Strawberry", 65.8);
        assertEquals("Red", cake.getName());
    }

    @Test
    public void testCakeConstructorWithoutIdAndGetFlavour_cakeFlavour_returnsFlavour(){
        BirthdayCake<Integer> cake = new BirthdayCake<>("Red", "Strawberry", 65.8);
        assertEquals("Strawberry", cake.getFlavour());
    }

    @Test
    public void testCakeConstructorWithoutIdAndGetPrice_cakePrice_returnsPrice(){
        BirthdayCake<Integer> cake = new BirthdayCake<>("Red", "Strawberry", 65.8);
        assertEquals(65.8, cake.getPrice());
    }

    @Test
    public void testCakeConstructor_cakeId_returnsId(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1,"Red", "Strawberry", 65.8);
        assertEquals(1, cake.getId());
    }

    @Test
    public void testCakeConstructor_cakeName_returnsName(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1,"Red", "Strawberry", 65.8);
        assertEquals("Red", cake.getName());
    }

    @Test
    public void testCakeConstructorAndGetFlavour_cakeFlavour_returnsFlavour(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1,"Red", "Strawberry", 65.8);
        assertEquals("Strawberry", cake.getFlavour());
    }

    @Test
    public void testCakeConstructorAndGetPrice_cakePrice_returnsPrice(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(1,"Red", "Strawberry", 65.8);
        assertEquals(65.8, cake.getPrice());
    }

    @Test
    public void testCakeConstructorAndGetName_EmptyName_returnsEmptyName(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(12,"","", 66.1);
        assertEquals("", cake.getName());
    }

    @Test
    public void testCakeConstructorAndGetId_NullID_returnsNullId(){
        BirthdayCake<Integer> cake = new BirthdayCake<>(null,"banana","banana", 66.1);
        assertNull(cake.getId());
    }
}