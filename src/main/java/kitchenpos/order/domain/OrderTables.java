package kitchenpos.order.domain;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
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

	public void checkSizeWithSaved(List<OrderTable> savedOrderTables) {
		if (orderTables.size() != savedOrderTables.size()) {
			throw new IllegalArgumentException();
		}
	}

	public void checkTableGroupsExist() {
		for (final OrderTable savedOrderTable : orderTables) {
			if (!savedOrderTable.isEmpty() || Objects.nonNull(savedOrderTable.getTableGroup())) {
				throw new IllegalArgumentException();
			}
		}
	}

	public List<OrderTable> getOrderTables() {
		return orderTables;
	}
}
