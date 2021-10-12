package kitchenpos.order.domain;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderLineItems {
	private final List<OrderLineItem> orderLineItems;

	public OrderLineItems(List<OrderLineItem> orderLineItems) {
		validateOrderLineTimesEmpty(orderLineItems);
		this.orderLineItems = orderLineItems;
	}

	private void validateOrderLineTimesEmpty(List<OrderLineItem> orderLineItems) {
		if (CollectionUtils.isEmpty(orderLineItems)) {
			throw new IllegalArgumentException();
		}
	}

	public void checkCountOfOrderLineItems(long countOfMenuIds) {
		if (orderLineItems.size() != countOfMenuIds) {
			throw new IllegalArgumentException();
		}
	}

	public List<Long> extractMenuIdsByOrderLineItems() {
		return orderLineItems.stream()
				.map(OrderLineItem::getMenuId)
				.collect(Collectors.toList());
	}

	public List<OrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}
}
