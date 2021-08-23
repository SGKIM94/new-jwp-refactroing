package kitchenpos.menu.domain;

import java.math.BigDecimal;

public class Price {
	public static final int MINIMUM = 0;

	private final BigDecimal price;

	public Price(int price) {
		validatePrice(price);
		this.price = BigDecimal.valueOf(price);
	}

	private void validatePrice(int price) {
		if (price < MINIMUM) {
			throw new IllegalArgumentException("가격은 0보다 작을 수 없습니다. : " + price);
		}
	}

	public void validatePrice() {
		if (price.compareTo(BigDecimal.ZERO) < MINIMUM) {
			throw new IllegalArgumentException("가격은 0보다 작을 수 없습니다. : " + price);
		}
	}

	public void validateSum(BigDecimal sum) {
		if (price.compareTo(sum) > MINIMUM) {
			throw new IllegalArgumentException();
		}
	}

	public BigDecimal getPrice() {
		return price;
	}
}
