package turnoverdoc.telegram.services;

import turnoverdoc.telegram.model.Order;

import java.util.List;

public interface OrderService {
    void createOrder(String username);
    List<Order> findByUserId(String username);
}
