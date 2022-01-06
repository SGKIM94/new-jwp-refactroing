package kitchenpos.order.domain;

import java.time.LocalDateTime;
import java.util.List;

public class TableGroup extends BaseEntity {
    private Long id;
    private List<OrderTable> orderTables;

    public TableGroup() {
    }

    public TableGroup(Long id, LocalDateTime createdDate) {
        super(createdDate);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public List<OrderTable> getOrderTables() {
        return orderTables;
    }

    public void mappingOrderTables(final List<OrderTable> orderTables) {
        this.orderTables = orderTables;
    }
}
