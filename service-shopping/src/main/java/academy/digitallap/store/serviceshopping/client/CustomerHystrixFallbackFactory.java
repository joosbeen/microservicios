package academy.digitallap.store.serviceshopping.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import academy.digitallap.store.serviceshopping.model.Customer;

@Component
public class CustomerHystrixFallbackFactory implements CustomerClient {

	@Override
	public ResponseEntity<Customer> getCustomer(long id) {
		
		Customer customer = Customer.builder()
				.email("none")
				.firstName("none")
				.lastName("none")
				.photoUrl("")
				.build();
		
		return ResponseEntity.ok(customer);
		
	}

}
