package org.springjava.springjavatest.completableFuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductComponent implements ProductUseCase{

    private final ProductRepository productRepository;
    //Executor executor = Executors.newFixedThreadPool(10);
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public int getPrice(String name) {
        log.info("동기 호출 방식으로 가격 조회 시작");

        return productRepository.getPriceByName(name);
    }

    @Override
    public CompletableFuture<Integer> getPriceAsync(String name) {
        log.info("비동기 호출 방식으로 가격 조회 시작");

        return CompletableFuture.supplyAsync(() -> {
                    log.info("supplyAsync");
                    return productRepository.getPriceByName(name);
                },
                threadPoolTaskExecutor
        );
    }

    @Override
    public CompletableFuture<Integer> getDiscountPriceAsync(Integer price) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("supplyAsync");
            return (int)(price * 0.9);
        }, threadPoolTaskExecutor);
    }
}
