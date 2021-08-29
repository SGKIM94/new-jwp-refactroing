package kitchenpos.product.domain;

import kitchenpos.menu.domain.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProductTest {

	@DisplayName("품목의 가격을 수량만큼 곱한다.")
	@Test
	void multiply_test() {
		Product 고기 = new Product("고기", new Price(10000));

		Price price = 고기.multiply(new Price(5000), 3L);

		assertThat(price).isEqualTo(new Price(35000));
	}
}
