package kitchenpos.dto;

public class MenuGroupResponse {

	private Long id;
	private String name;

	public MenuGroupResponse() {
	}

	public MenuGroupResponse(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}
}
