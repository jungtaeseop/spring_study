package com.test.test_demo.dipArchitecture;

import java.util.List;

public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(int orderId, String customerName, List<String> items){
        Order order = new Order(orderId, customerName, items);
        orderRepository.saveOrder(order);
        System.out.println("Order placed successfully.");
    }

    public Order getOrder(int orderId) {
        return orderRepository.getOrderById(orderId);
    }
}
