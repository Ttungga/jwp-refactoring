package kitchenpos.tablegroup.application;

public class TableUngroupEvent {

    private final Long tableGroupId;

    public TableUngroupEvent(Long tableGroupId) {
        this.tableGroupId = tableGroupId;
    }

    public Long getTableGroupId() {
        return tableGroupId;
    }
}