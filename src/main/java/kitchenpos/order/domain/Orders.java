package kitchenpos.order.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_table_id")
	private OrderTable orderTable;

	private String orderStatus;

	private LocalDateTime orderedTime;

	protected Orders() {
	}

	public Orders(OrderTable orderTable, String orderStatus) {
		validate(orderTable);
		this.orderTable = orderTable;
		this.orderStatus = orderStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}


	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(final String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getOrderedTime() {
		return orderedTime;
	}

	public void setOrderedTime(final LocalDateTime orderedTime) {
		this.orderedTime = orderedTime;
	}

	public OrderTable getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(OrderTable orderTable) {
		this.orderTable = orderTable;
	}

	@PrePersist
	public void prePersist() {
		this.orderedTime = LocalDateTime.now();
	}

	public void changeStatus(String orderStatus) {
		if (OrderStatus.isCompletion(orderStatus)) {
			throw new IllegalArgumentException("계산완료 주문인 경우, 상태를 변경할 수 없습니다");
		}
		this.orderStatus = orderStatus;
	}

	private void validate(OrderTable orderTable) {
		if (orderTable.isEmpty()) {
			throw new IllegalArgumentException("테이블이 비어있습니다.");
		}
	}
}