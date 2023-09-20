package kitchenpos.fixture;

import kitchenpos.domain.Product;

import java.math.BigDecimal;
import java.util.UUID;

public class ProductServiceFixtrue {

    private final UUID id = UUID.randomUUID();
    private final String Default_NAME = "기본 상품";
    private final BigDecimal Default_PRICE = BigDecimal.valueOf(1000);

    public Product create(BigDecimal price){
        return create(price,this.Default_NAME);
    }

    public Product create(String name){
        return create(Default_PRICE,name);
    }

    public Product create(BigDecimal price, String name){
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setPrice(price);
        product.setName(name);

        return product;
    }

    public Product createDefault(){
        return create(Default_PRICE,Default_NAME);
    }
}
