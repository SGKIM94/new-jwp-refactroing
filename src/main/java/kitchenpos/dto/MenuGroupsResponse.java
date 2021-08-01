package kitchenpos.dto;

import kitchenpos.domain.MenuGroup;

import java.util.List;
import java.util.stream.Collectors;

public class MenuGroupsResponse {

	private List<MenuGroupResponse> menuGroups;

	public MenuGroupsResponse() {
	}

	public MenuGroupsResponse(List<MenuGroupResponse> menuGroups) {
		this.menuGroups = menuGroups;
	}

	public static MenuGroupsResponse of(List<MenuGroup> menuGroups) {
		List<MenuGroupResponse> responses = menuGroups.stream()
				.map(MenuGroupResponse::of)
				.collect(Collectors.toList());

		return new MenuGroupsResponse(responses);
	}

	public List<MenuGroupResponse> getMenuGroups() {
		return menuGroups;
	}
}
