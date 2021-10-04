package kitchenpos.order.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrderTablesTest {

	@Test
	void 테이블_그룹이_존재하는지_검사한다() {

		OrderTables orderTables = new OrderTables(Arrays.asList(new OrderTable(new TableGroup()), new OrderTable(new TableGroup())));

		assertThrows(IllegalArgumentException.class,
				orderTables::checkTableGroupsExist
		);
	}
}