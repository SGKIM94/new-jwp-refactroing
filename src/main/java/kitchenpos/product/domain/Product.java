package kitchenpos.product.domain;

import kitchenpos.menu.domain.Price;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private final String name;
    private final Price price;

    public Product(String name, Price price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = new Price(price.intValue());
    }

    public Product(Long id, String name, BigDecimal price) {
        this(name, price);
        this.id = id;
    }

    public Price multiply(Price sum, long quantity) {
        return sum.add(price.multiply(quantity));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Price getPrice() {
        return price;
    }
}
