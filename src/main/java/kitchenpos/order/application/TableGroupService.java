package kitchenpos.order.application;

import kitchenpos.order.dao.OrderDao;
import kitchenpos.order.dao.OrderTableDao;
import kitchenpos.order.dao.TableGroupDao;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.domain.OrderTable;
import kitchenpos.order.domain.OrderTables;
import kitchenpos.order.domain.TableGroup;
import kitchenpos.order.dto.TableGroupRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    public TableGroup create(final TableGroupRequest tableGroup) {
        OrderTables orderTables = findOrderTablesByRequest(tableGroup);

        final List<Long> orderTableIds = orderTables.extractOrderTableIds();

        final List<OrderTable> savedOrderTables = orderTableDao.findAllByIdIn(orderTableIds);

        orderTables.checkSizeWithSaved(savedOrderTables);

        orderTables.checkTableGroupsExist();

        final TableGroup savedTableGroup = tableGroupDao.save(tableGroup.toEntity());

        saveOrderTables(savedOrderTables);

        savedTableGroup.mappingOrderTables(savedOrderTables);

        return savedTableGroup;
    }

    private OrderTables findOrderTablesByRequest(TableGroupRequest tableGroup) {
        return new OrderTables(tableGroup.getOrderTables().stream()
                .map(orderTableDao::findById)
                .map(Optional::get)
                .collect(Collectors.toList()));
    }

    private void saveOrderTables(List<OrderTable> savedOrderTables) {
        for (final OrderTable savedOrderTable : savedOrderTables) {
            orderTableDao.save(new OrderTable(savedOrderTable.getTableGroup()));
        }
    }

    @Transactional
    public void ungroup(final Long tableGroupId) {
        OrderTables orderTables = new OrderTables(orderTableDao.findAllByTableGroupId(tableGroupId));

        final List<Long> orderTableIds = orderTables.extractOrderTableIds();

        if (orderDao.existsByOrderTableIdInAndOrderStatusIn(
                orderTableIds, Arrays.asList(OrderStatus.COOKING.name(), OrderStatus.MEAL.name()))) {
            throw new IllegalArgumentException();
        }

        saveAllOrderTables(orderTables);
    }

    private void saveAllOrderTables(OrderTables orderTables) {
        orderTables.getOrderTables().forEach(tables -> orderTableDao.save(new OrderTable(null)));
    }
}
