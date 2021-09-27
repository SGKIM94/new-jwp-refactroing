package kitchenpos.order.domain;

import java.util.List;
import java.util.stream.Collectors;

public class OrderTables {
	private List<OrderTable> orderTables;

	public OrderTables(List<OrderTable> orderTables) {
		this.orderTables = orderTables;
	}

	List<Long> extractOrderTableIds(List<OrderTable> orderTables) {
		return orderTables.stream()
				.map(OrderTable::getId)
				.collect(Collectors.toList());
	}
}
