package kitchenpos.order.dto;

import kitchenpos.order.domain.OrderTables;
import kitchenpos.order.domain.TableGroup;

import java.util.List;

public class TableGroupRequest {
	private Long id;
	private List<Long> orderTables;

	public TableGroupRequest() {
	}

	public TableGroupRequest(Long id, List<Long> orderTables) {
		this.id = id;
		this.orderTables = orderTables;
	}

	public Long getId() {
		return id;
	}

	public List<Long> getOrderTables() {
		return orderTables;
	}

	public TableGroup toEntity(OrderTables orderTables) {
		return new TableGroup(id, orderTables);
	}
}
