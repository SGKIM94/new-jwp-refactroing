package kitchenpos.order.application;

import kitchenpos.menu.dao.MenuDao;
import kitchenpos.order.dao.OrderDao;
import kitchenpos.order.dao.OrderLineItemDao;
import kitchenpos.order.dao.OrderTableDao;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderLineItem;
import kitchenpos.order.domain.OrderLineItems;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.domain.OrderTable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final MenuDao menuDao;
    private final OrderDao orderDao;
    private final OrderLineItemDao orderLineItemDao;
    private final OrderTableDao orderTableDao;

    public OrderService(
            final MenuDao menuDao,
            final OrderDao orderDao,
            final OrderLineItemDao orderLineItemDao,
            final OrderTableDao orderTableDao
    ) {
        this.menuDao = menuDao;
        this.orderDao = orderDao;
        this.orderLineItemDao = orderLineItemDao;
        this.orderTableDao = orderTableDao;
    }

    @Transactional
    public Order create(final Order order) {
        OrderLineItems orderLineItemss = new OrderLineItems(order.getOrderLineItems());

        final List<Long> menuIds = orderLineItemss.extractMenuIdsByOrderLineItems();

        orderLineItemss.checkCountOfOrderLineItems(menuDao.countByIdIn(menuIds));

        final OrderTable orderTable = orderTableDao.findById(order.getOrderTableId())
                .orElseThrow(IllegalArgumentException::new);

        final Order savedOrder = orderDao.save(new Order(order, orderTable.getId()));

        return saveOrderOrderLineItems(orderLineItemss, savedOrder);
    }

    private Order saveOrderOrderLineItems(OrderLineItems orderLineItems, Order savedOrder) {
        final List<OrderLineItem> savedOrderLineItems = new ArrayList<>();

        Long orderId = savedOrder.getId();

        for (final OrderLineItem orderLineItem : orderLineItems.getOrderLineItems()) {
            orderLineItem.mappingOrderId(orderId);
            savedOrderLineItems.add(orderLineItemDao.save(orderLineItem));
        }

        savedOrder.mappingOrderLineItems(savedOrderLineItems);

        return savedOrder;
    }

    public List<Order> list() {
        final List<Order> orders = orderDao.findAll();

        for (final Order order : orders) {
            order.mappingOrderLineItems(orderLineItemDao.findAllByOrderId(order.getId()));
        }

        return orders;
    }

    @Transactional
    public Order changeOrderStatus(final Long orderId, final Order order) {
        final Order savedOrder = orderDao.findById(orderId)
                .orElseThrow(IllegalArgumentException::new);

        validateCompleteState(savedOrder);

        final OrderStatus orderStatus = OrderStatus.valueOf(order.getOrderStatus());
        savedOrder.setOrderStatus(orderStatus.name());

        orderDao.save(savedOrder);

        savedOrder.mappingOrderLineItems(orderLineItemDao.findAllByOrderId(orderId));

        return savedOrder;
    }

    private void validateCompleteState(Order savedOrder) {
        if (Objects.equals(OrderStatus.COMPLETION.name(), savedOrder.getOrderStatus())) {
            throw new IllegalArgumentException();
        }
    }

    public OrderTable findOrderTableById(long orderTableId) {
        return orderTableDao.findById(orderTableId)
                .orElseThrow(IllegalArgumentException::new);
    }
}
