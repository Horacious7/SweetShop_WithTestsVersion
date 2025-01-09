package tests.repository.file;

import main.domain.Order;
import main.repository.file.text.TextFileOrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TextFileOrderRepositoryTest {
    private static final String filename = "test_orders.txt";
    private TextFileOrderRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new TextFileOrderRepository(filename);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(filename);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testAdd_newOrder_true(){
        Order<Integer> order = new Order<>(1, 1, "Bean", "cancelled", "details", "address", 100.0);
        order.setId(1);
        order.setStatus("cancelled");
        order.setOrderDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        repository.add(order);
        List<Order<Integer>> orders = (List<Order<Integer>>) repository.getAll();
        assertEquals(order.getCustomerName(), orders.get(0).getCustomerName());
    }

    @Test
    public void testDelete_order_true(){
        Order<Integer> order = new Order<>(1, 1, "Bean", "cancelled", "details", "address", 100.0);
        order.setId(1);
        order.setStatus("cancelled");
        order.setOrderDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        repository.add(order);
        repository.delete(order.getId());
        List<Order<Integer>> orders = (List<Order<Integer>>) repository.getAll();
        assertTrue(orders.isEmpty(), "Repo should be empty!");
    }

    @Test
    public void testModify_modifyingTheOrder_true(){
        Order<Integer> order = new Order<>(1, 1, "Bean", "cancelled", "details", "address", 100.0);
        order.setId(1);
        order.setStatus("cancelled");
        order.setOrderDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        repository.add(order);

        order.setCustomerName("Ana");
        order.setStatus("preparing");
        repository.update(order.getId(), order);

        List<Order<Integer>> orders = (List<Order<Integer>>) repository.getAll();
        assertEquals("Ana", orders.get(0).getCustomerName());
    }
}