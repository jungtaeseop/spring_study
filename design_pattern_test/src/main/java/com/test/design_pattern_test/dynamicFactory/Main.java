package com.test.design_pattern_test.dynamicFactory;

public class Main {
    public static void main(String[] args) {

        DynamicSupplierFactory.resisterSupplier("SpecialItem", SpecialItemInventoryManagement::new);

        // Test the factory method with different types of items.
        String[] types = {"Aged Bire", "Sulfuras, Hand of Ragnaros", "Backstage passes to a TAFKAL80ETC concert", "SpecialItem"};

        for (String type : types) {
            InventoryManagement im = DynamicSupplierFactory.getInventoryManagement(type);
            if (im != null) im.manage();
            else System.out.println("No management strategy for type: " + type);
        }

        // Test the factory method with an unknown item type.
        String unknownType = "Unknown";
        InventoryManagement imUnknown = DynamicSupplierFactory.getInventoryManagement(unknownType);
        if (imUnknown != null) imUnknown.manage();
        else System.out.println("No management strategy for type: " + unknownType);

        // Test the factory method with a basic item.
        String basicType = DynamicSupplierFactory.BASIC_DEFAULT_VALUE;
        InventoryManagement imBasic = DynamicSupplierFactory.getInventoryManagement(basicType);
        if (imBasic != null) imBasic.manage();
        else System.out.println("No management strategy for type: "+ basicType);
    }

}
