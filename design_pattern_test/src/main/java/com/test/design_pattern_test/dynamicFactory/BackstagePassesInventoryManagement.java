package com.test.design_pattern_test.dynamicFactory;

public class BackstagePassesInventoryManagement implements InventoryManagement {
    public BackstagePassesInventoryManagement() {

    }

    @Override
    public void manage() {
        System.out.println("Managing Backstage Passes inventory");
    }
}
