package academy.digitallap.store.serviceproduct.repository;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import academy.digitallap.store.serviceproduct.entity.Category;
import academy.digitallap.store.serviceproduct.entity.Product;

@DataJpaTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void whenCategory() {
		Product product01 = Product.builder()
				.name("computer")
				.category(Category.builder().id(1L).build())
				.description("")
				.stock(Double.parseDouble("10"))
				.price(Double.parseDouble("1240.90"))
				.status("Created")
				.createAt(new Date())
				.build();
		
		productRepository.save(product01);
		
		List<Product> prodcuts = productRepository.findByCategory(product01.getCategory());
		
		Assertions.assertThat(prodcuts.size()).isEqualTo(3);

	}

}
