package kitchenpos.menu.dto;

import java.util.List;

public class MenusResponse {
	private List<MenuResponse> menus;

	public MenusResponse(List<MenuResponse> menus) {
		this.menus = menus;
	}

	public List<MenuResponse> getMenus() {
		return menus;
	}
}
