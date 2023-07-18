package com.test.test_demo.dipArchitecture;

public interface OrderRepository {
    void saveOrder(Order order);

    Order getOrderById(int orderId);
}
