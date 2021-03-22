package academy.digitallap.store.serviceproduct.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import academy.digitallap.store.serviceproduct.entity.Category;
import academy.digitallap.store.serviceproduct.entity.Product;
import academy.digitallap.store.serviceproduct.repository.ProductRepository;

@Service
//@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> listAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product createProduct(Product product) {
		product.setStatus("Created");
		product.setCreateAt(new Date());
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {

		Product productValidated = getProduct(product.getId());

		if (productValidated == null) {
			return null;
		}

		productValidated.setDescription(product.getDescription());
		productValidated.setName(product.getName());
		productValidated.setPrice(product.getPrice());
		productValidated.setStock(product.getStock());
		productValidated.setCategory(product.getCategory());

		return productRepository.save(productValidated);
	}

	@Override
	public Product deleteProduct(Long id) {
		Product productValidated = getProduct(id);

		if (productValidated == null) {
			return null;
		}
		
		productValidated.setStatus("Delete");
		
		return productRepository.save(productValidated);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);//findByCategory(category);
	}

	@Override
	public Product updateStock(Long id, Double quantity) {
		Product productValidated = getProduct(id);

		if (productValidated == null) {
			return null;
		}
		
		Double stock = productValidated.getStock() + quantity;
		
		productValidated.setStock(stock);
		
		return productRepository.save(productValidated);
	}

}
