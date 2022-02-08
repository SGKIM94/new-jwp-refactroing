package kitchenpos.order.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private Long orderTableId;
    private String orderStatus;
    private LocalDateTime orderedTime;
    private List<OrderLineItem> orderLineItems;

    public Order() {
    }

    public Order(Order requestOrder, Long orderTableId) {
        this.id = requestOrder.getId();
        this.orderTableId = orderTableId;
        this.orderStatus = OrderStatus.COOKING.name();
        this.orderedTime = LocalDateTime.now();
        this.orderLineItems = requestOrder.getOrderLineItems();
    }

    public Order(long id, long orderTableId, String orderStatus, LocalDateTime orderTime) {
        this.id = id;
        this.orderTableId = orderTableId;
        this.orderStatus = orderStatus;
        this.orderedTime = orderTime;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderTableId() {
        return orderTableId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void mappingOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void mappingOrderLineItems(final List<OrderLineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }
}
