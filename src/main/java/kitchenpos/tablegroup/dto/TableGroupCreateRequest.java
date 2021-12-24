package kitchenpos.tablegroup.dto;

import kitchenpos.tablegroup.domain.TableGroup;

import java.util.ArrayList;
import java.util.List;

public class TableGroupCreateRequest {
    private final List<Long> orderTableIds = new ArrayList<>();

    public TableGroupCreateRequest(List<Long> orderTableIds) {
        this.orderTableIds.addAll(orderTableIds);
    }

    public static TableGroupCreateRequest of(List<Long> orderTableIds) {
        return new TableGroupCreateRequest(orderTableIds);
    }

    public List<Long> getOrderTableIds() {
        return orderTableIds;
    }

    public TableGroup toEntity() {
        return TableGroup.of(orderTableIds);
    }
}