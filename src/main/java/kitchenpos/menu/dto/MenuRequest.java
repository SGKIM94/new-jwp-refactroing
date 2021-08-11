package kitchenpos.menu.dto;

import kitchenpos.menu.domain.Price;

import java.util.List;

public class MenuRequest {
	private String name;
	private Price price;
	private Long menuGroupId;
	private List<Long> MenuProductsId;

	public MenuRequest(String name, Price price, Long menuGroupId) {
		this.name = name;
		this.price = price;
		this.menuGroupId = menuGroupId;
	}

	public MenuRequest(String name, Price price, Long menuGroupId, List<Long> menuProductsId) {
		this.name = name;
		this.price = price;
		this.menuGroupId = menuGroupId;
		MenuProductsId = menuProductsId;
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
		return MenuProductsId;
	}
}
