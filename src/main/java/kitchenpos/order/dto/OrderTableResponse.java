package kitchenpos.order.dto;

import kitchenpos.order.domain.OrderTable;
import kitchenpos.order.domain.TableGroup;

public class OrderTableResponse {
	private Long id;
	private TableGroup tableGroup;
	private int numberOfGuests;
	private boolean empty;

	public OrderTableResponse() {
	}

	public OrderTableResponse(Long id, TableGroup tableGroup, int numberOfGuests, boolean empty) {
		this.id = id;
		this.tableGroup = tableGroup;
		this.numberOfGuests = numberOfGuests;
		this.empty = empty;
	}

	public static OrderTableResponse of(OrderTable table) {
		return new OrderTableResponse(table.getId(), table.getTableGroup(), table.getNumberOfGuests(), table.isEmpty());
	}

	public Long getId() {
		return id;
	}

	public TableGroup getTableGroup() {
		return tableGroup;
	}

	public boolean isEmpty() {
		return empty;
	}
}
