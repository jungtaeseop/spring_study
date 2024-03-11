package org.springjava.springjavatest.completableFuture;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {

    private Map<String, Product> productMap = new HashMap<>();

    @PostConstruct
    public void init(){
        productMap.put("cellphone", Product.builder().name("cellphone").price(1100000).build());
        productMap.put("cup", Product.builder().name("cup").price(1300).build());
        productMap.put("monitor", Product.builder().name("monitor").price(900).build());
    }

    public int getPriceByName(String name){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return productMap.get(name).getPrice();
    }
}
