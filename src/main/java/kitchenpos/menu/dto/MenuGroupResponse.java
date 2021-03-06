package kitchenpos.menu.dto;

import kitchenpos.menu.domain.MenuGroup;

public class MenuGroupResponse {
	private Long id;
	private String name;

	public MenuGroupResponse() {
	}

	public MenuGroupResponse(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public static MenuGroupResponse of(MenuGroup menuGroup) {
		return new MenuGroupResponse(menuGroup.getId(), menuGroup.getName());
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
}
