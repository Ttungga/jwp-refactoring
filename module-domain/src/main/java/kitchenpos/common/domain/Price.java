package kitchenpos.common.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price extends Value<Price> {
	@Column(name = "price", nullable = false)
	private BigDecimal value;

	protected Price() {

	}

	public static Price from(BigDecimal value) {
		if (value == null || value.compareTo(BigDecimal.ZERO) < 0) {
			throw new IllegalArgumentException("가격은 0 이상이어야 합니다.");
		}

		Price price = new Price();
		price.value = value;
		return price;
	}

	public BigDecimal getValue() {
		return value;
	}

	public int compareTo(Price price) {
		return value.compareTo(price.value);
	}

	public Price add(Price price) {
		return from(value.add(price.value));
	}

	public Price multiply(Quantity quantity) {
		return from(value.multiply(BigDecimal.valueOf(quantity.getValue())));
	}
}