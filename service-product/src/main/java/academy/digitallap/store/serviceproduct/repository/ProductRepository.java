package academy.digitallap.store.serviceproduct.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import academy.digitallap.store.serviceproduct.entity.Category;
import academy.digitallap.store.serviceproduct.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	public List<Product> findByCategory(Category category);

}
