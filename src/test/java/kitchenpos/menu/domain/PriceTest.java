package kitchenpos.menu.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

	@DisplayName("금액이 0보다 작은경우 예외처리한다.")
	@Test
	void validatePriceWithNull() {
		assertThrows(IllegalArgumentException.class,
				() -> new Price(-20)
		);
	}

	@DisplayName("금액을 수량에 따라 곱한다.")
	@Test
	void multiply_test() {
		Price price = new Price(2000);

		long result = price.multiply(4L);

		assertThat(result).isEqualTo(8000);
	}

	@DisplayName("금액을 더한다.")
	@Test
	void sum_test() {
		Price price = new Price(2000);

		Price result = price.add(3000);

		assertThat(result).isEqualTo(new Price(5000));
	}
}
