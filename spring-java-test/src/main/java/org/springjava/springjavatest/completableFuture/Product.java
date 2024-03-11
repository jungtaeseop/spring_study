package org.springjava.springjavatest.completableFuture;

import lombok.Builder;
import lombok.Data;

@Data
public class Product {
    private String name;
    private int price;

    @Builder
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
}
