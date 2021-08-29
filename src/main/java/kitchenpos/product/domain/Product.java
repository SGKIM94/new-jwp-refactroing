package kitchenpos.product.domain;

import kitchenpos.menu.domain.Price;

import java.math.BigDecimal;

public class Product {
    private Long id;
    private String name;
    private Price price;

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
