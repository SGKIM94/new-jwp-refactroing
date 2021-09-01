package kitchenpos.menu.dto;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProducts;
import kitchenpos.menu.domain.Price;

import java.util.List;

public class MenuRequest {
	private String name;
	private Price price;
	private Long menuGroupId;
 	private List<Long> menuProductsId;

	public MenuRequest(String name, Price price, Long menuGroupId) {
		this.name = name;
		this.price = price;
		this.menuGroupId = menuGroupId;
	}

	public String getName() {
		return name;
	}

	public Price getPrice() {
		return price;
	}

	public Long getMenuGroupId() {
		return menuGroupId;
	}

	public List<Long> getMenuProductsId() {
		return menuProductsId;
	}

	public Menu toEntity(MenuGroup menuGroup, MenuProducts menuProducts) {
		return new Menu(name, price, menuGroup, menuProducts);
	}
}
