package org.springjava.springjavatest.completableFuture;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductComponent productComponent;

    @GetMapping("/product/price")
    public int getPrice(@RequestParam(name = "name") String name){

        return productComponent.getPriceAsync(name).join();
    }
}
