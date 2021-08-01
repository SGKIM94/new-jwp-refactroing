package kitchenpos.dto;

import java.util.List;

public class MenuGroupsResponse {

	private List<MenuGroupResponse> menuGroups;

	public MenuGroupsResponse() {
	}

	public MenuGroupsResponse(List<MenuGroupResponse> menuGroups) {
		this.menuGroups = menuGroups;
	}

	public List<MenuGroupResponse> getMenuGroups() {
		return menuGroups;
	}
}
