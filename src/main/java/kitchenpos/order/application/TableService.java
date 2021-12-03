package kitchenpos.order.application;

import kitchenpos.order.dao.OrderDao;
import kitchenpos.order.dao.OrderTableDao;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.order.domain.OrderTable;
import kitchenpos.order.dto.OrderTableRequest;
import kitchenpos.order.dto.OrderTableResponse;
import kitchenpos.order.dto.OrderTablesResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
public class TableService {
    public static final int MINIMUM_NUMBER_OF_GUESTS = 0;

    private final OrderDao orderDao;
    private final OrderTableDao orderTableDao;

    public TableService(final OrderDao orderDao, final OrderTableDao orderTableDao) {
        this.orderDao = orderDao;
        this.orderTableDao = orderTableDao;
    }

    @Transactional
    public OrderTableResponse create(final OrderTableRequest orderTable) {
        return OrderTableResponse.of(orderTableDao.save(orderTable.toEmptyTable()));
    }

    public OrderTablesResponse list() {
        return OrderTablesResponse.of(orderTableDao.findAll());
    }

    @Transactional
    public OrderTable changeEmpty(final Long orderTableId, final OrderTableRequest orderTable) {
        final OrderTable savedOrderTable = orderTableDao.findById(orderTableId)
                .orElseThrow(IllegalArgumentException::new);

        validateExistsByOrderTableAndOrderStatusIn(orderTableId);

        return orderTableDao.save(new OrderTable(orderTable.isEmpty()));
    }

    private void validateExistsByOrderTableAndOrderStatusIn(Long orderTableId) {
        if (orderDao.existsByOrderTableIdAndOrderStatusIn(
                orderTableId, Arrays.asList(OrderStatus.COOKING.name(), OrderStatus.MEAL.name()))) {
            throw new IllegalArgumentException();
        }
    }

    @Transactional
    public OrderTable changeNumberOfGuests(final Long orderTableId, final OrderTableRequest orderTable) {
        final int numberOfGuests = orderTable.getNumberOfGuests();

        validateNumberOfGuests(numberOfGuests);

        final OrderTable savedOrderTable = orderTableDao.findById(orderTableId)
                .orElseThrow(IllegalArgumentException::new);

        savedOrderTable.checkSavedOrderTableEmpty();

        return orderTableDao.save(new OrderTable(numberOfGuests));
    }

    private void validateNumberOfGuests(int numberOfGuests) {
        if (numberOfGuests < MINIMUM_NUMBER_OF_GUESTS) {
            throw new IllegalArgumentException();
        }
    }
}
