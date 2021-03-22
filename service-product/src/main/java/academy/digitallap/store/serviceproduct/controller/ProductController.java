package academy.digitallap.store.serviceproduct.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import academy.digitallap.store.serviceproduct.entity.Category;
import academy.digitallap.store.serviceproduct.entity.Product;
import academy.digitallap.store.serviceproduct.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping()
	public ResponseEntity<List<Product>> listProduct(
			@RequestParam(name = "categoriId", required = false) Long categoryId) {

		List<Product> products = new ArrayList<Product>();

		if (categoryId == null) {
			products = productService.listAllProduct();

			if (products.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} else {
			products = productService.findByCategory(Category.builder().id(categoryId).build());

			if (products.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
		}

		return ResponseEntity.ok(products);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> productById(@PathVariable(name = "id") Long id) {
		Product products = productService.getProduct(id);

		if (products == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(products);
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product, BindingResult result) {
		
		if (result.hasErrors()) {
			new ResponseStatusException(HttpStatus.BAD_GATEWAY, this.messages(result));
		}

		Product product_db = productService.createProduct(product); 

		return ResponseEntity.status(HttpStatus.CREATED).body(product_db);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
		
		product.setId(id);

		Product product_db = productService.updateProduct(product);

		if (product_db == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(product_db);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
		

		Product product_db = productService.deleteProduct(id);

		if (product_db == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(product_db);
	}

	@GetMapping(value = "/{id}/stock")
	public ResponseEntity<Product> updateStockProduct(@PathVariable(name = "id") Long id, @RequestParam(name = "quantity", required = true) Double quantity) {
		
		Product product_db = productService.updateStock(id, quantity);

		if (product_db == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(product_db);
	}
	
	public String messages(BindingResult bindingResult) {
		
		List<Map<String,  String>> messages = bindingResult.getFieldErrors()
				.stream()
				.map(err -> {
					
					Map<String, String> error = new HashMap<String, String>();
					
					error.put(err.getField(), err.getDefaultMessage());
					
					return error;
					
				}).collect(Collectors.toList());
		
		ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(messages);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
        
	}

}
