package kitchenpos.menu.domain;

import kitchenpos.product.domain.Product;

public class MenuProduct {
    private Long seq;
    private Menu menu;
    private Product product;
    private long quantity;

    public MenuProduct() {
    }

    public MenuProduct(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public MenuProduct(Menu menu, Product product, long quantity) {
        this(product, quantity);
        this.menu = menu;
    }

    public MenuProduct(Long seq, Menu menu, Product product, long quantity) {
        this(menu, product, quantity);
        this.seq = seq;
    }

    public Long getSeq() {
        return seq;
    }

    public Menu getMenu() {
        return menu;
    }

    public Product getProduct() {
        return product;
    }

    public long getQuantity() {
        return quantity;
    }
}
