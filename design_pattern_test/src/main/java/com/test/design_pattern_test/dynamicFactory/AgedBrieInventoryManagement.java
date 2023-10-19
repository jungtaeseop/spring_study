package com.test.design_pattern_test.dynamicFactory;

public class AgedBrieInventoryManagement implements InventoryManagement {
    public AgedBrieInventoryManagement() {

    }

    @Override
    public void manage() {
        System.out.println("Managing Aged Brie inventory");
    }
}
