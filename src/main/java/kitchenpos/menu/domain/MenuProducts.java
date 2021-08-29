package kitchenpos.menu.domain;

import kitchenpos.product.domain.Product;

import java.math.BigDecimal;
import java.util.List;

public class MenuProducts {
	private List<MenuProduct> menuProducts;

	public MenuProducts() {
	}

	public MenuProducts(List<MenuProduct> menuProducts) {
		this.menuProducts = menuProducts;
	}

	public Price sumProductPriceByQuantity() {
		Price sum = Price.ZERO;

		for (final MenuProduct menuProduct : menuProducts) {
			Product product = menuProduct.getProduct();
			sum = product.multiply(sum, menuProduct.getQuantity());
		}

		return sum;
	}

	public List<MenuProduct> getMenuProducts() {
		return menuProducts;
	}
}
