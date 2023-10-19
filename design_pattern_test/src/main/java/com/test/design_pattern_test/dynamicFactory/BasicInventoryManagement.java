package com.test.design_pattern_test.dynamicFactory;

public class BasicInventoryManagement implements InventoryManagement {
    @Override
    public void manage() {
        System.out.println("Managing Basic inventory");
    }
}
