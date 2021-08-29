package kitchenpos.menu.dto;

import kitchenpos.menu.domain.MenuProducts;

import java.util.List;
import java.util.stream.Collectors;

public class MenuProductsResponse {
	private List<MenuProductResponse> menuProducts;

	public MenuProductsResponse() {
	}

	public MenuProductsResponse(List<MenuProductResponse> menuProducts) {
		this.menuProducts = menuProducts;
	}

	public static MenuProductsResponse of(MenuProducts menuProducts) {
		List<MenuProductResponse> menuProductResponse = menuProducts.getMenuProducts().stream()
				.map(MenuProductResponse::of)
				.collect(Collectors.toList());

		return new MenuProductsResponse(menuProductResponse);
	}

	public List<MenuProductResponse> getMenuProducts() {
		return menuProducts;
	}
}
