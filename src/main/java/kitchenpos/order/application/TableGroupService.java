package kitchenpos.order.application;

import kitchenpos.order.dao.OrderDao;
import kitchenpos.order.dao.OrderTableDao;
import kitchenpos.order.dao.TableGroupDao;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.domain.OrderTable;
import kitchenpos.order.domain.OrderTables;
import kitchenpos.order.domain.TableGroup;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TableGroupService {
    private final OrderDao orderDao;
    private final OrderTableDao orderTableDao;
    private final TableGroupDao tableGroupDao;

    public TableGroupService(final OrderDao orderDao, final OrderTableDao orderTableDao, final TableGroupDao tableGroupDao) {
        this.orderDao = orderDao;
        this.orderTableDao = orderTableDao;
        this.tableGroupDao = tableGroupDao;
    }

    @Transactional
    public TableGroup create(final TableGroup tableGroup) {
        OrderTables orderTables = new OrderTables(tableGroup.getOrderTables());

        final List<Long> orderTableIds = orderTables.extractOrderTableIds();

        final List<OrderTable> savedOrderTables = orderTableDao.findAllByIdIn(orderTableIds);

        orderTables.checkSizeWithSaved(savedOrderTables);

        for (final OrderTable savedOrderTable : savedOrderTables) {
            if (!savedOrderTable.isEmpty() || Objects.nonNull(savedOrderTable.getTableGroup())) {
                throw new IllegalArgumentException();
            }
        }

        tableGroup.setCreatedDate(LocalDateTime.now());

        final TableGroup savedTableGroup = tableGroupDao.save(tableGroup);

        for (final OrderTable savedOrderTable : savedOrderTables) {
            orderTableDao.save(new OrderTable(savedOrderTable.getTableGroup()));
        }

        savedTableGroup.setOrderTables(savedOrderTables);

        return savedTableGroup;
    }

    @Transactional
    public void ungroup(final Long tableGroupId) {
        OrderTables orderTables = new OrderTables(orderTableDao.findAllByTableGroupId(tableGroupId));

        final List<Long> orderTableIds = orderTables.extractOrderTableIds();

        if (orderDao.existsByOrderTableIdInAndOrderStatusIn(
                orderTableIds, Arrays.asList(OrderStatus.COOKING.name(), OrderStatus.MEAL.name()))) {
            throw new IllegalArgumentException();
        }

        for (final OrderTable orderTable : orderTables.getOrderTables()) {
            orderTableDao.save(new OrderTable(null));
        }
    }
}
