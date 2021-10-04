package kitchenpos.order.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderTablesTest {

	@Test
	void 테이블_그룹이_존재하는지_검사한다() {
		OrderTables orderTables = new OrderTables(Arrays.asList(new OrderTable(new TableGroup()), new OrderTable(new TableGroup())));

		assertThrows(IllegalArgumentException.class,
				orderTables::checkTableGroupsExist
		);
	}

	@Test
	void OrderTable에서_id들을_추출한다() {
		OrderTables orderTables = new OrderTables(Arrays.asList(new OrderTable(1L, new TableGroup()), new OrderTable(2L, new TableGroup())));

		List<Long> ids = orderTables.extractOrderTableIds();

		assertThat(ids).containsExactly(1L, 2L);
	}
}