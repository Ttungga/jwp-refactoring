package kitchenpos.ui.table;

import java.net.URI;
import java.util.List;
import kitchenpos.application.table.TableService;
import kitchenpos.common.table.domain.OrderTable;
import kitchenpos.common.table.dto.OrderTableRequest;
import kitchenpos.common.table.dto.OrderTableResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TableRestController {
    private final TableService tableService;

    public TableRestController(final TableService tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/api/tables")
    public ResponseEntity<OrderTableResponse> create(@RequestBody final OrderTableRequest request) {
        final OrderTableResponse created = tableService.create(request);
        final URI uri = URI.create("/api/tables/" + created.getId());
        return ResponseEntity.created(uri)
                .body(created);
    }

    @GetMapping("/api/tables")
    public ResponseEntity<List<OrderTableResponse>> list() {
        return ResponseEntity.ok()
                .body(tableService.list());
    }

    @PutMapping("/api/tables/{id}/empty")
    public ResponseEntity<OrderTable> changeEmpty(@PathVariable final Long id,
                                                  @RequestBody final OrderTableRequest request) {
        return ResponseEntity.ok()
                .body(tableService.changeEmpty(id, request));
    }

    @PutMapping("/api/tables/{id}/number-of-guests")
    public ResponseEntity<OrderTable> changeNumberOfGuests(@PathVariable final Long id,
                                                           @RequestBody final OrderTableRequest request) {
        return ResponseEntity.ok()
                .body(tableService.changeNumberOfGuests(id, request));
    }
}