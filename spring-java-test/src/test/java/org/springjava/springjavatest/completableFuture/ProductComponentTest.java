package org.springjava.springjavatest.completableFuture;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class ProductComponentTest {

    @Autowired
    private ProductComponent productComponent;

    @Test
    public void 가격_조회_동기_블록킹_호출_테스트(){

        int expectedPrice = 1100000;

        int resultPrice = productComponent.getPrice("cellphone");
        log.info("최종 가격 전달 받음");

        assertEquals(expectedPrice, resultPrice);

    }

    @Test
    public void thenCombine_test(){

        Integer expectedPrice = 1100000 + 1300;

        CompletableFuture<Integer> futureA = productComponent.getPriceAsync("cellphone");
        CompletableFuture<Integer> futureB = productComponent.getPriceAsync("cup");

        //futureA.thenCombine(futureB, (a, b) -> a + b);
        Integer resultPrice = futureA.thenCombine(futureB, Integer::sum).join();

        assertEquals(expectedPrice, resultPrice);
    }

    @Test
    public void allOf_test(){

        Integer expectedPrice = 1100000 + 1300 + 900;

        CompletableFuture<Integer> futureA = productComponent.getPriceAsync("cellphone");
        CompletableFuture<Integer> futureB = productComponent.getPriceAsync("cup");
        CompletableFuture<Integer> futureC = productComponent.getPriceAsync("monitor");

        List<CompletableFuture<Integer>> completableFutureList = Arrays.asList(futureA, futureB, futureC);

        //Integer resultPrice = CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[3]))
        Integer resultPrice = CompletableFuture.allOf(futureA, futureB, futureC)
                .thenApply(Void -> completableFutureList.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList()))
                .join()
                .stream()
                .reduce(0, Integer::sum);

        assertEquals(expectedPrice, resultPrice);

    }
}