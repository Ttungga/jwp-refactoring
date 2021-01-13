package kitchenpos.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.math.BigDecimal;
import java.util.List;
import kitchenpos.MySpringBootTest;
import kitchenpos.domain.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@MySpringBootTest
class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@DisplayName("금액이 0원 이상인 상품을 등록한다.")
	@Test
	void create() {
		//given
		Product product = new Product(
			  "리코타치즈샐러드",
			  new BigDecimal(6_000)
		);

		//when
		Product savedProduct = productService.create(product);

		//then
		List<Product> list = productService.list();
		assertThat(list).contains(savedProduct);
	}

	@DisplayName("금액이 0원미만 상품은 등록할 수 없다.")
	@Test
	void createWithUnderZeroPrice() {
		//given
		Product product = new Product(
			  "리코타치즈샐러드",
			  new BigDecimal(-1)
		);
		//when
		상품등록이_실패함(product);

		//given
		product = new Product(
			  "리코타치즈샐러드",
			  null
		);
		//when
		상품등록이_실패함(product);
	}

	private void 상품등록이_실패함(Product product) {
		assertThatIllegalArgumentException()
			  .isThrownBy(() -> productService.create(product))
			  .withMessage("상품금액은 0원 이상이어야 합니다.");
	}
}