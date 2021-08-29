package kitchenpos.menu.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Price {
	public static final int MINIMUM = 0;
	public static final Price ZERO = new Price(0);

	private final long price;

	public Price(long price) {
		validatePrice(price);
		this.price = price;
	}

	public Price(BigDecimal price) {
		long priceToLong = price.longValue();

		validatePrice(priceToLong);
		this.price = priceToLong;
	}

	private void validatePrice(long price) {
		if (price < MINIMUM) {
			throw new IllegalArgumentException("가격은 0보다 작을 수 없습니다. : " + price);
		}
	}

	public void validatePrice() {
		if (price < MINIMUM) {
			throw new IllegalArgumentException("가격은 0보다 작을 수 없습니다. : " + price);
		}
	}

	public long multiply(long quantity) {
		return price * quantity;
	}

	public Price add(long sum) {
		return new Price(price + sum);
	}

	public long getPrice() {
		return price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Price price1 = (Price) o;
		return price == price1.price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(price);
	}
}
