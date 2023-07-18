package com.test.test_demo.dipArchitecture;

import java.util.HashMap;
import java.util.Map;

public class InMemoryOrderRepository implements OrderRepository{

    private Map<Integer,Order> orderMap;

    public InMemoryOrderRepository() {
        orderMap = new HashMap<>();
    }
    @Override
    public void saveOrder(Order order) {
        orderMap.put(order.getOrderId(),order);
    }

    @Override
    public Order getOrderById(int orderId) {
        return orderMap.get(orderId);
    }
}
