package kitchenpos.menu.dto;

import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.Price;

public class MenuResponse {
	private Long id;
	private String name;
	private Price price;
	private MenuGroupResponse menuGroup;
	private MenuProductsResponse menuProducts;

	public MenuResponse(Long id, String name, Price price, MenuGroupResponse menuGroup, MenuProductsResponse menuProducts) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.menuGroup = menuGroup;
		this.menuProducts = menuProducts;
	}

	public static MenuResponse of(Menu savedMenu) {
		return new MenuResponse(savedMenu.getId(), savedMenu.getName(), savedMenu.getPrice(),
				MenuGroupResponse.of(savedMenu.getMenuGroup()), MenuProductsResponse.of(savedMenu.getMenuProducts()));
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

	public MenuGroupResponse getMenuGroup() {
		return menuGroup;
	}

	public MenuProductsResponse getMenuProducts() {
		return menuProducts;
	}
}
