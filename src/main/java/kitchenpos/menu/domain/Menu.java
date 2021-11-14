package kitchenpos.menu.domain;

import java.util.List;

public class Menu {
    private Long id;
    private String name;
    private Price price;
    private MenuGroup menuGroup;
    private MenuProducts menuProducts;

    public Menu() {
    }

    public Menu(String name, Price price, MenuGroup menuGroup, MenuProducts menuProducts) {
        this.name = name;
        this.price = price;
        this.menuGroup = menuGroup;
        this.menuProducts = menuProducts;
    }

    public Menu(Long id, String name, Price price, MenuGroup menuGroup) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroup = menuGroup;
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

    public MenuProducts getMenuProducts() {
        return menuProducts;
    }

    public void mappingMenuProducts(List<MenuProduct> savedMenuProducts) {
        this.menuProducts = new MenuProducts(savedMenuProducts);
    }

    public MenuGroup getMenuGroup() {
        return menuGroup;
    }
}
