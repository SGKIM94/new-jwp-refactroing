package kitchenpos.menu.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

	@DisplayName("금액이 0보다 작은경우 예외처리한다")
	@Test
	void validatePriceWithNull() {
		assertThrows(IllegalArgumentException.class,
				() -> new Price(-20)
		);
	}
}