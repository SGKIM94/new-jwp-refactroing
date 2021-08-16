package kitchenpos.menu.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {
	private final BigDecimal price;

	public Price(int price) {
		validatePrice(price);
		this.price = BigDecimal.valueOf(price);
	}

	private void validatePrice(int price) {
		if (price < 0) {
			throw new IllegalArgumentException("가격은 0보다 작을 수 없습니다. : " + price);
		}
	}

	public void validatePrice() {
		if (price.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("가격은 0보다 작을 수 없습니다. : " + price);
		}
	}

	public void validateSum(BigDecimal sum) {
		if (price.compareTo(sum) > 0) {
			throw new IllegalArgumentException();
		}
	}

	public BigDecimal getPrice() {
		return price;
	}
}
