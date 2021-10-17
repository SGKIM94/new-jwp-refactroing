package kitchenpos.order.domain;

import java.util.Objects;

public class OrderTable {
    private Long id;
    private TableGroup tableGroup;
    private int numberOfGuests;
    private boolean empty;

    public OrderTable() {
    }

    public OrderTable(TableGroup tableGroup, int numberOfGuests, boolean empty) {
        this.tableGroup = tableGroup;
        this.numberOfGuests = numberOfGuests;
        this.empty = empty;
    }

    public OrderTable(TableGroup tableGroup) {
        this.tableGroup = tableGroup;
        this.empty = false;
    }

    public OrderTable(Long id, TableGroup tableGroup) {
        this.id = id;
        this.tableGroup = tableGroup;
        this.empty = false;
    }

    public static OrderTable emptyTable() {
        return new OrderTable(null, 0, true);
    }

    public void validateTableGroupId() {
        if (Objects.nonNull(tableGroup)) {
            throw new IllegalArgumentException();
        }
    }

    public void checkSavedOrderTableEmpty() {
        if (this.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public TableGroup getTableGroup() {
        return tableGroup;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(final int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(final boolean empty) {
        this.empty = empty;
    }

    public Object getTableGroupId() {
        return tableGroup.getId();
    }
}
