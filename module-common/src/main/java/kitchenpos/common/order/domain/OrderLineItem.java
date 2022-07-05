package kitchenpos.common.order.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import kitchenpos.common.common.domain.Quantity;

@Entity
@Table(name = "order_line_item")
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "menu_id")
    private Long menuId;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private Long menuPrice;

    @Embedded
    private Quantity quantity;

    public OrderLineItem() {
    }

    public OrderLineItem(final Long menuId,
                         final Quantity quantity) {
        this.menuId = menuId;
        this.quantity = quantity;
    }

    public OrderLineItem(final Long seq,
                         final Order order,
                         final Long menuId,
                         final Quantity quantity) {
        this.seq = seq;
        this.order = order;
        this.menuId = menuId;
        this.quantity = quantity;
    }

    public OrderLineItem(final Long menuId,
                         final String menuName,
                         final Long menuPrice,
                         final Quantity quantity) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.quantity = quantity;
    }

    public OrderLineItem(final Long seq,
                         final Order order,
                         final Long menuId,
                         final String menuName,
                         final Long menuPrice,
                         final Quantity quantity) {
        this.seq = seq;
        this.order = order;
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.quantity = quantity;
    }

    public void relateToOrder(final Order order) {
        if (Objects.nonNull(this.order)) {
            throw new IllegalStateException("이미 주문과의 연관관계가 설정되어 있습니다.");
        }
        this.order = order;
    }

    public Long getSeq() {
        return seq;
    }

    public Order getOrder() {
        return order;
    }

    public Long getMenuId() {
        return menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public Long getMenuPrice() {
        return menuPrice;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}