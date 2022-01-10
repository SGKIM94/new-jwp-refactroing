package kitchenpos.order.domain;

import java.time.LocalDateTime;

public class TableGroup extends BaseEntity {
    private Long id;
    private OrderTables orderTables;

    public TableGroup() {
    }

    public TableGroup(Long id, LocalDateTime createdDate) {
        super(createdDate);
        this.id = id;
    }

    public TableGroup(Long id, OrderTables orderTables) {
        this.id = id;
        this.orderTables = orderTables;
    }

    public Long getId() {
        return id;
    }

    public OrderTables getOrderTables() {
        return orderTables;
    }

    public void mappingOrderTables(final OrderTables orderTables) {
        this.orderTables = orderTables;
    }
}
