package kitchenpos.menu.dto;

import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.product.dto.ProductResponse;

public class MenuProductResponse {
	private final Long seq;
	private final MenuResponse menu;
	private final ProductResponse product;
	private final long quantity;

	public MenuProductResponse(Long seq, MenuResponse menu, ProductResponse product, long quantity) {
		this.seq = seq;
		this.menu = menu;
		this.product = product;
		this.quantity = quantity;
	}

	public static MenuProductResponse of(MenuProduct menuProduct) {
		return new MenuProductResponse(menuProduct.getSeq(), MenuResponse.of(menuProduct.getMenu()),
				ProductResponse.of(menuProduct.getProduct()), menuProduct.getQuantity());
	}

	public Long getSeq() {
		return seq;
	}

	public MenuResponse getMenu() {
		return menu;
	}

	public ProductResponse getProduct() {
		return product;
	}

	public long getQuantity() {
		return quantity;
	}
}
