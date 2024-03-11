package org.springjava.springjavatest.completableFuture;

import java.util.concurrent.Future;

public interface ProductUseCase {
    int getPrice(String name);                              //Sync(동기)
    Future<Integer> getPriceAsync(String name);             //Async(비동기)
    Future<Integer> getDiscountPriceAsync(Integer price);   //Async(비동기)
}
