package kitchenpos.order.domain;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderTables {
	private List<OrderTable> orderTables;

	public OrderTables(List<OrderTable> orderTables) {
		validateOrderTables(orderTables);
		this.orderTables = orderTables;
	}

	private void validateOrderTables(List<OrderTable> orderTables) {
		if (CollectionUtils.isEmpty(orderTables) || orderTables.size() < 2) {
			throw new IllegalArgumentException();
		}
	}

	public List<Long> extractOrderTableIds() {
		return orderTables.stream()
				.map(OrderTable::getId)
				.collect(Collectors.toList());
	}
}
