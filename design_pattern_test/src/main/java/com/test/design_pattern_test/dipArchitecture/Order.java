package com.test.design_pattern_test.dipArchitecture;

import java.util.*;

public class Order {
    private int orderId;
    private String customerName;
    private List<String> items;

    public Order(int orderId, String customerName, List<String> items){
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<String> getItems() {
        return items;
    }
}
