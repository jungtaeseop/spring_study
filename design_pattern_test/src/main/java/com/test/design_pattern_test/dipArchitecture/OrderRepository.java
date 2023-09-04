package com.test.design_pattern_test.dipArchitecture;

public interface OrderRepository {
    void saveOrder(Order order);

    Order getOrderById(int orderId);
}
