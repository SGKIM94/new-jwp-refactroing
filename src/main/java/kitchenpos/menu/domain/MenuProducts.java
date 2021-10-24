package kitchenpos.menu.domain;

import java.util.List;

public class MenuProducts {
	private List<MenuProduct> menuProducts;

	public MenuProducts() {
	}

	public MenuProducts(List<MenuProduct> menuProducts) {
		this.menuProducts = menuProducts;
	}

	public List<MenuProduct> getMenuProducts() {
		return menuProducts;
	}
}
