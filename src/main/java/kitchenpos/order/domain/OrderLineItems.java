package kitchenpos.order.domain;

import java.util.List;

public class OrderLineItems {
	private List<OrderLineItem> orderLineItems;

	public OrderLineItems(List<OrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
	}

	public List<OrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}
}
