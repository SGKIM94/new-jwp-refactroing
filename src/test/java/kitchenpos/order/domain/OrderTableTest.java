package kitchenpos.order.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTableTest {

	@DisplayName("table groupd id가 있는 경우 예외처리한다.")
	@Test
	void validateTableGroupId_test() {
		OrderTable orderTable = new OrderTable(new TableGroup(), 3, false);

		assertThrows(IllegalArgumentException.class,
				orderTable::validateTableGroupId
		);
	}
}