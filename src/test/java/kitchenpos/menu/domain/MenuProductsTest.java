package kitchenpos.menu.domain;

import kitchenpos.product.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MenuProductsTest {

	@DisplayName("개수에 따른 품목의 합을 구한다.")
	@Test
	void sumProductPriceByQuantity_test() {
		MenuProduct 새우깡 = new MenuProduct(new Product("새우깡", new Price(2000)), 3L);
		MenuProduct 고구마깡 = new MenuProduct(new Product("고구마깡", new Price(1500)), 2L);
		MenuProducts menuProducts = new MenuProducts(List.of(새우깡, 고구마깡));

		Price sum = menuProducts.sumProductPriceByQuantity();

		assertThat(sum).isEqualTo(new Price(9000));
	}
}
