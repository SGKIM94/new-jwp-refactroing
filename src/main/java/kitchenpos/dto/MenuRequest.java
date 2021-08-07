package kitchenpos.dto;

import java.math.BigDecimal;
import java.util.List;

public class MenuRequest {
	private String name;
	private BigDecimal price;
	private Long menuGroupId;
	private List<Long> menuProducts;

	public MenuRequest(String name, BigDecimal price, Long menuGroupId, List<Long> menuProducts) {
		this.name = name;
		this.price = price;
		this.menuGroupId = menuGroupId;
		this.menuProducts = menuProducts;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Long getMenuGroupId() {
		return menuGroupId;
	}

	public List<Long> getMenuProducts() {
		return menuProducts;
	}
}
