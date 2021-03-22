package academy.digitallap.store.serviceproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.digitallap.store.serviceproduct.entity.Product;
import academy.digitallap.store.serviceproduct.service.ProductService;

@RestController
@RequestMapping()
public class HomeController {
	

	@Autowired
	private ProductService productService;

	@GetMapping({"/home", "/index" })
	public ResponseEntity<List<Product>> listProduct() {

		List<Product> products = productService.listAllProduct();

		if (products.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(products);

	}

}
