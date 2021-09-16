package kitchenpos.order.dto;

import kitchenpos.order.domain.OrderTable;

import java.util.List;
import java.util.stream.Collectors;

public class OrderTablesResponse {
	private List<OrderTableResponse> orderTables;

	public OrderTablesResponse() {
	}

	public OrderTablesResponse(List<OrderTableResponse> orderTables) {
		this.orderTables = orderTables;
	}

	public static OrderTablesResponse of(List<OrderTable> orderTables) {
		List<OrderTableResponse> orderTablesResponse = orderTables.stream()
				.map(OrderTableResponse::of)
				.collect(Collectors.toList());

		return new OrderTablesResponse(orderTablesResponse);
	}

	public List<OrderTableResponse> getOrderTables() {
		return orderTables;
	}
}
