package kitchenpos.menu.domain;

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

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setMenuGroup(MenuGroup menuGroup) {
        this.menuGroup = menuGroup;
    }

    public MenuProducts getMenuProducts() {
        return menuProducts;
    }

    public void mappingMenuProducts(MenuProducts menuProducts) {
        this.menuProducts = menuProducts;
    }

    public MenuGroup getMenuGroup() {
        return menuGroup;
    }
}
