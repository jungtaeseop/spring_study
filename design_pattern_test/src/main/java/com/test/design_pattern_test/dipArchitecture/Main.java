package com.test.design_pattern_test.dipArchitecture;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){
        OrderRepository orderRepository = new InMemoryOrderRepository();
        OrderService orderService = new OrderService(orderRepository);

        // 주문 생성 및 저장
        List<String> items = new ArrayList<>();
        items.add("item 1");
        items.add("item 2");
        orderService.placeOrder(1,"john doe",items);

        // 주문 조회
        Order order = orderService.getOrder(1);
        if (order != null) {
            System.out.println("Order ID: " + order.getOrderId());
            System.out.println("Customer Name: " + order.getCustomerName());
            System.out.println("Items: " + order.getItems());
        } else {
            System.out.println("Order not found.");
        }
    }
}
