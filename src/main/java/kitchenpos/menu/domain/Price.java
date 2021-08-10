package kitchenpos.menu.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {
	private final BigDecimal price;

	public Price(int price) {
		this.price = BigDecimal.valueOf(price);
	}

	public void validatePrice() {
		if (Objects.isNull(price) || price.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException();
		}
	}
}
