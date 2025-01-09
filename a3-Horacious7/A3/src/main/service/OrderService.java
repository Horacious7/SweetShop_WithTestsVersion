package main.service;

import main.domain.Order;
import main.domain.BirthdayCake;
import main.filter.OrderFilters.FilterOrderByStatus;
import main.filter.OrderFilters.FilterOrderByPrice;
import main.repository.IRepository;
import main.repository.memory.InMemoryFilteredRepository;
import main.repository.RepositoryException;

import java.util.List;
import java.util.ArrayList;

public class OrderService<ID> {
    private IRepository<ID, Order<ID>> orderRepository;
    private IRepository<ID, BirthdayCake<ID>> cakeRepository;
    private Integer currentOrderId;

    public OrderService(IRepository<ID, Order<ID>> orderRepository, IRepository<ID, BirthdayCake<ID>> cakeRepository) {
        if (orderRepository == null || cakeRepository == null) {
            throw new RepositoryException("Repository cannot be null.");
        }
        this.orderRepository = orderRepository;
        this.cakeRepository = cakeRepository;
        this.currentOrderId = ((List<Order<ID>>) orderRepository.getAll()).size() + 1;
    }

    public ID addOrder(Order<ID> order) {
        if (order.getId() == null) {
            order.setId((ID) currentOrderId);
            currentOrderId++;
        }
        orderRepository.add(order);
        return order.getId();
    }

    public int generateNewId() {
        List<Order<ID>> orders = (List<Order<ID>>) getAllOrders();
        if (orders.isEmpty()) {
            return 1;
        }
        for (int i = 1; i <= orders.size() + 1; i++) {
            boolean idExists = false;
            for (Order<ID> order : orders) {
                if (order.getId().equals(i)) {
                    idExists = true;
                    break;
                }
            }
            if (!idExists) {
                return i;
            }
        }
        return orders.size() + 1;
    }

    public Iterable<Order<ID>> getAllOrders() {
        return orderRepository.getAll();
    }

    public void updateOrder(Order<ID> order) {
        orderRepository.update(order.getId(), order);
    }

    public void deleteOrder(ID idToDelete) {
        orderRepository.delete(idToDelete);
    }

    public Iterable<Order<ID>> getOrdersByPriceRange(double minPrice, double maxPrice) {
        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Prices cannot be negative!");
        }
        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("Min price cannot be greater than max price!");
        }
        FilterOrderByPrice<ID> priceFilter = new FilterOrderByPrice<>(minPrice, maxPrice);
        InMemoryFilteredRepository<ID, Order<ID>> filteredRepository = new InMemoryFilteredRepository<>(orderRepository, priceFilter);
        return filteredRepository.getAll();
    }

    public Iterable<Order<ID>> getOrdersByStatus(String statusToFilterBy) {
        FilterOrderByStatus<ID> statusFilter = new FilterOrderByStatus<>(statusToFilterBy);
        InMemoryFilteredRepository<ID, Order<ID>> filteredRepository = new InMemoryFilteredRepository<>(orderRepository, statusFilter);
        return filteredRepository.getAll();
    }
}